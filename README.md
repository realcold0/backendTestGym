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

### ERD CLOUD

![GYMbackend](https://github.com/realcold0/backendTestGym/assets/65608503/910ba40b-9586-4008-ac6c-87e8b43da79b)

### api 기능

### 기본 api 주소

    /api/v1

### USER

| 주소                 | 요청   | 기능          |
|--------------------|------|-------------|
| /user/login        | POST | 로그인 api     |
| /user/register     | POST | 회원가입        | 
| /user/register-gym | POST | 회원의 헬스장 등록  |
| /user/my-review    | GET  | 내가 쓴 헬스장 리뷰 |
| /user/gym          | GET  | 내가 등록한 헬스장  |

### GYM

| 주소          | 요청   | 기능                 |
|-------------|------|--------------------|
| /gym        | GET  | 헬스장 리스트(운동갯수, 이름순) |
| /gym/rental | GET  | 헬스장의 대여 물품 정보      |
| /gym/equip  | GET  | 헬스장의 기구  정보        | 
| /gym/review | POST | 헬스장 리뷰 등록          |

### 관리자

| 주소                     | 요청     | 기능               |
|------------------------|--------|------------------|
| /admin/gym             | POST   | 헬스장의 등록          |
| /admin/gym             | PUT    | 헬스장의 정보 수정       |
| /admin/gym             | DELETE | 헬스장 삭제           |
| /admin/gym/equip       | POST   | 헬스장의 기구 등록       |
| /admin/gym/equip       | DELETE | 헬스장의 기구 삭제       |
| /admin/gym/equip-many  | POST   | 여러 헬스장에 운동기구 등록  |
| /admin/equip           | DELETE | 기존 운동기구 삭제       |
| /admin/equip           | POST   | 운동기구 추가          |
| /admin/rental          | POST   | 헬스장의 대여 물품 등록    |
| /admin/rental          | DELETE | 헬스장의 대여 물품 삭제    |
| /admin/gym/rental-many | POST   | 여러 헬스장에 대여 물품 등록 |

### 개발 요구사항

- 조회 성능 최적화 N+1 문제를 해결한다.
- 컨트롤러에서 validation을 활용해서 의도하지 않은 값이 들어온 경우 다른 로직 처리
- 관리자가 특정 데이터 삭제시 soft delete, hard delete구현
- 테스트 코드 작성
- docker활용해서 이미지 생성, DB는 MariaDB -> docker compose로 구현 완료 