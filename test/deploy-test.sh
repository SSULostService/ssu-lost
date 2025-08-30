set -e

APP_NAME="ssulost-server"
GREEN="green"
CONTAINER="-container"

# 버전 정보 가져오기
DEPLOY_VERSION=$(tr -d '\r' < version)

PREV_VERSION=$(docker inspect --format='{{index .Config.Image}}' "$GREEN$CONTAINER" 2>/dev/null | awk -F: '{print $2}')

export APP_VERSION="$DEPLOY_VERSION"

# DOCKER 로그인
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

# 이미지 가져오기

docker pull "$DOCKER_USERNAME/$APP_NAME:$DEPLOY_VERSION"

# 새 버전 컨테이너 실행

docker stop "$GREEN$CONTAINER" || true
docker rm -f "$GREEN$CONTAINER" || true
docker compose up -d "$GREEN"

# Health check
timeout=120
count=0
until [ "$(docker inspect -f '{{.State.Health.Status}}' "$GREEN$CONTAINER")" = "healthy" ] || [ $count -ge $timeout ]; do
  sleep 5
  count=$((count+5))
done

if [ "$(docker inspect -f '{{.State.Health.Status}}' "$GREEN$CONTAINER")" != "healthy" ]; then
    echo "헬스 체크 실패 → 롤백"

  docker stop "$GREEN$CONTAINER" || true
  docker rm "$GREEN$CONTAINER" || true

  APP_VERSION="$PREV_VERSION" docker compose up -d "$GREEN"
  exit 1
fi

echo "헬스 체크 성공"

docker image prune -f
