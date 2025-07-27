# 패키지 전략 개요
각각의 하위 도메인마다 컨텍스트 패키지를 만들었습니다.
## 컨텍스트별 패키지
컨텍스트마다 반복되는 패키지 구조를 그림으로 나타냈습니다.<br>
<img width="450" height="538" alt="image" src="https://github.com/user-attachments/assets/c1d118ec-01e6-49c2-b277-a760a1042cca" />

# 테스트 개요
테스트 및 로컬 개발 환경에서 `docker-compose`를 활용해 MySQL 등 의존 서비스를 자동으로 실행합니다. 또한, **GitHub Actions를 사용한 CI 파이프라인**이 설정되어 있습니다.

## 주요 기능 및 설정

### 1. Spring Boot + Docker Compose 통합

- `spring-boot-docker-compose` 의존성 추가  
  → 테스트 또는 로컬 실행 시 `docker-compose.yml`에 정의된 의존 컨테이너(MySQL 등)가 자동 실행됩니다.

### 2. 단위 테스트 작성
