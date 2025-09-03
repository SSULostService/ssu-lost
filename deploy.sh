set -e

APP_NAME="ssulost-server"
BLUE="blue"
GREEN="green"

# DOCKER 로그인
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

# 이미지 가져오기
docker pull "$DOCKER_USERNAME/$APP_NAME"

# 새 버전 컨테이너 실행

for SERVICE in $BLUE $GREEN
do
  docker stop "$SERVICE" || true
  docker rm -f "$SERVICE" || true
  docker compose up -d "$SERVICE"

  timeout=120
  count=0
  until [ "$(docker inspect -f '{{.State.Health.Status}}' "$SERVICE")" = "healthy" ] || [ $count -ge $timeout ]; do
    sleep 5
    count=$((count+5))
  done

  if [ "$(docker inspect -f '{{.State.Health.Status}}' "$SERVICE")" != "healthy" ]; then
      echo "헬스 체크 실패"

    docker stop "$SERVICE" || true
    docker rm "$SERVICE" || true

    exit 1
  fi

  echo "$SERVICE 컨테이너 헬스 체크 성공"

done

# Nginx 트래픽 전환
docker compose up -d nginx

docker image prune -f
