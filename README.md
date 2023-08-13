## 프로젝트 설명

- 게시판, 게시글, 댓글 CRUD 구현

## 개발 환경
- 언어 : java (17)
- database : h2
- framework : spring boot (3.1.2)

## 요구 사항
- 게시판, 게시글, 댓글 등록을 구현해주세요.
- 게시판, 게시글, 댓글 조회를 구현해주세요.
- 게시판, 게시글, 댓글상세 조회를 구현해주세요.
- 게시판, 게시글, 댓글 수정을 구현해주세요.
- 게시판, 게시글, 댓글 삭제을 구현해주세요.

# 소스 설명

프로젝트 전체적인 구조는 MVC 패턴으로 구성되어 있습니다.
데이터를 가저오기 위한 방법으롤 JPA를 사용하였습니다. JPA를 사용한 이유는 쿼리가 비교적 간단하여 메소드 호출 형식으로 데이터를 추출하는 것이 유용하다고 판단되어 사용했습니다.

- Controller
  - Controller는 Data를 반환하기 때문에 RestContrller 어노테이션을 사용하였습니다. 그리고 생성자 주입을 자동으로 설정해 주는 requiredargsconstructor 어노테이션을 사용했습니다. 
  - CRUD 기능에 따라 @GetMapping, @PostMapping, @PutMapping, @DeleteMapping 을 사용했으며 @PathVariable과 @RequestBody를 통해 요청 데이터를 전달 받았습니다. 그리고 상세 조회 기능에서는 페이징 기능이 필요하기 때문에 params을 통해 데이터를 전달 받았습니다. 

- Service 
  - Service에서는 Controller로 부터 전달 받는 파라미터를 통해 Respository에서 원하는 데이터를 가져오는 로직을 수행하는 곳입니다.
  - 모든 메소드에는 트랜잭션 처리를 하였으며 조회 메소드는 @Transactional(readOnly = true) 통해  DB에서 데이터를 읽기만 한다는 것을 명확하게 확인할 수 있어 가독성을 향상시켰습니다. 

- Repository
  - Java 진영에서 ORM(Object-Relational Mapping) 기술 표준으로 사용하는 인터페이스를 만들었습니다.

- Domain
  - 서버가 실행 될 때 테이블이 자동 생성될 수 있도록 설정하였습니다.
  - ID는 @GeneratedValue(strategy = GenerationType.IDENTITY) 통해 값이 자동으로 넣도록 하였고, 몇몇 변수는 @Column(nullable = false)를 통해 null 값 허용을 제한했습니다.
  - 외래키 컬럼에는 EAGER(즉시로딩)을 사용하여 데이터를 가져올 때 하나의 객체만 가져오는 것이 아닌 참조 객체들의 데이터까지 전부 읽어오는 방식을 사용했습니다. 그리고  @OnDelete(action = OnDeleteAction.CASCADE)를 사용하여 참조한 데이터가 삭제되었을 때 연쇄적으로 삭제 되도록 하였습니다.

## API
- 미리 설정된 Writer(글쓴이) 초기값

| WRITER_ID | DISPLAY_NAME |
|-----------|--------------|
| 1         | 홍길동          |
| 2         | 메일           |
| 3         | 플러그          |
| 4         | 글쓴이          |
| 5         | 신선호          |
|           |              |
- API 링크 : https://www.notion.so/f37df07bec9b416c9006d7621091f195?v=484bbd8ee12a4fa88702b96cd29a3ef0&pvs=4
