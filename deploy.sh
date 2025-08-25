set -e

APP_NAME="ssulost-server"
BLUE_CONTAINER="blue-container"
GREEN_CONTAINER="green-container"

# 0. Docker Login
docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"

# 현재 실행중인 컨테이너 확인
if docker ps --format '{{.Names}}' | grep -q "$BLUE_CONTAINER"; then
  CURRENT="blue"
  IDLE="green"
  IDLE_PORT=8081
else
  CURRENT="green"
  IDLE="blue"
  IDLE_PORT=8080
fi

echo "현재 실행중: $CURRENT → 교체 대상: $IDLE"

# 1. 새 이미지 pull
docker pull $DOCKER_USERNAME/$APP_NAME:latest

# 2. idle 컨테이너 실행 (새 이미지)
docker compose up -d $IDLE

# 3. idle 컨테이너 헬스체크 대기
echo "Waiting for $IDLE-container health..."
timeout=120
count=0
until [ "$(docker inspect -f '{{.State.Health.Status}}' ${IDLE}-container)" = "healthy" ] || [ $count -ge $timeout ]; do
  sleep 5
  count=$((count+5))
done

if [ $count -ge $timeout ]; then
  echo "헬스체크 실패 ❌"
  # 실패 시 새 컨테이너 정리, 기존 서비스 유지(롤백)
  docker stop ${IDLE}-container
  docker rm ${IDLE}-container
  echo "롤백 실행: 이전 서비스 유지 ⏪"
  exit 1
fi
echo "$IDLE-container is healthy ✅"

# 4. Nginx 트래픽 전환
echo "Reloading Nginx..."
docker compose up -d nginx

# 5. 기존 컨테이너 종료
docker stop ${CURRENT}-container
docker rm ${CURRENT}-container

echo "배포 완료! 현재 서비스: $IDLE"
