# ecommerce-api
이커머스 페이지 만들어보기

###
## 기술 스택
- BackEnd
    - Java : JDK 17
        - 채택 이유
            - 아직 1.8을 쓰는 프로젝트도 많지만 17 오버 JDK를 사용하는 프로젝트도 많기에 이를 습득하기 위해 사용을 채택했다.
            - Spring boot 2 버전대는 17까지 지원하지만 3버전대 이상에서는 최소 JDK 스펙이 17 버전이기에 채택했다.
    - Spring boot : 2.7.18 (GA) 최종 릴리스 버전
        - 3 버전대 채택 이유
            - 최소 JDK 17버전 이상 지원
            - JDK 17과 호환성이 높아 17버전의 모든 기술 지원, springframework 6 버전대 기반의 최신 동향 기술
            - 2 버전대의 Security 설정, javax -> jakarta 등 3버전에서 변경된 사항을 <br> 이번 토이 프로젝트를 통해 익숙해지기 위해서 채택을 했다.
            - 3.1.x 버전에대 GA된 최종 버전을 채택, 여러 프로젝트에서 사용되고 있는 안정성이 검증된 버전을 사용하기위해 채택했다.
    - 빌드 관리 도구: Gradle
        - 채택 이유
            - xml 보다 구성에 있어 간결하고 유연한 구성 / 설정에 따라 xml 방식보다 빌드 속도가 높다는 이점