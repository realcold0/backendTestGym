# 학원의 신 백엔드 테스트

## 헬스장 서비스

### 실행 방법

1. spring boot를 한번 실행하여 빌드를 먼저 진행한다.
2. 터미널에

```
docker-compose up -d
```

를 실행한다.

3. 만약 실행 오류가 아래와 같이 뜬다면

```
    ARG JAR_FILE=build/libs/backendTestGym-0.0.1-SNAPSHOT.jar
  
    >>> COPY ${JAR_FILE} app.jar
  
    EXPOSE 8080
```

4. 다음 명령어를 실행시킨다.

```
./gradlew build
```

5. 종료시

```
docker-compose down
```