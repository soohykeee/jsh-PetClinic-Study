개발 환경

Java 17
Springboot 3.0.6
Gradle (Build Tool)
Intellij
DB : MySQL + NoSQL (Redis)
Commit 규칙

기능 별로 개발을 하고 커밋을 한다.

푸쉬 하나당 branch 하나 생성. ex) jsh/user, jsh/board .. [이름이니셜/개발분야]

commit 메세지: {type} : 기능

{type}

feat : 새로운 기능 추가, 첫 커밋 (기존의 없던 새로운 기능을 개발하여 추가해줄 때 Feat 사용)
fix : 장애/에러 수정 (approve 후에, 코드 리뷰를 통해 코드의 수정이 필요할 때, Fix를 통해 commit 반영할 때 사용)
docs : 문서 수정에 대한 커밋 (주석 수정 또는 코드에 상관없는 문서 수정의 경우 사용)
style : 코드 스타일 혹은 포맷 등에 관한 커밋 (css, front, format 등 사용 우리는 거의 사용할일 x)
refactor : 코드 리팩토링에 대한 커밋 (단순 코드 수정이나, 클린 코드 적용, 기존의 기능에서 업그레이드 해줄 때 사용

jsh/user -> 첫 user관련 기능 추가인경우
feat : create user service
feat : update user service
feat : delete user service
feat : get user service
또는 feat : Add User Service CRUD (한영 상관 X)
이미 board의 CRUD가 commit되어있는 경우, 코드의 수정이 필요할 때
refactor : board의 create service에서 조건문 추가
또는 코드 리뷰를 통해 코드의 수정이 필요하다 했을 경우 fix : board의 delete service에서 exception 처리 추가
