#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/app

echo "> 현재 구동 중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl action | grep python | awk '{print $1}')

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 파이썬 앱 배포"

PY_NAME=$(ls -tr $REPOSITORY/main.py | tail -n 1)

echo "> PY Name: $PY_NAME"

echo "> $PY_NAME 에 실행권한 추가"

chmod +x $PY_NAME

echo "> $PY_NAME 실행"

nohup python $PY_NAME > $REPOSITORY/nohup.out 2>&1 &