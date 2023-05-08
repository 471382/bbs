# Bulletin Board System (BBS)

BBS는 Spring Framework를 활용한 간단한 게시판 웹 애플리케이션입니다. 

이 애플리케이션은 개인 학습 및 이력서 포트폴리오 용도로 만들어졌습니다.

## 개발 환경
+ Spring Framework 3.9.11.RELEASE
+ Oracle Database 11g Express Edition

## 기능
+ 게시글 CRUD (등록, 조회, 수정, 삭제)
+ 파일 업로드/다운로드 (첨부파일)

## 실행 방법
1. git clone 명령어를 이용해 프로젝트를 로컬 저장소에 복제합니다.
```bash
git clone https://github.com/471382/bbs.git
```
2. STS에서 File - Import - Existing Maven Projects를 선택합니다.
3. 프로젝트 경로를 선택하고 Finish 버튼을 클릭합니다.
4. src/main/resources/application.properties 파일에서 데이터베이스 설정을 수정합니다.
5. STS에서 Run As - Spring Boot App을 선택하여 애플리케이션을 실행합니다.
## 참고
+ 프론트엔드는 부트스트랩을 사용하였습니다.
+ 파일 업로드/다운로드는 서버의 로컬 경로를 이용하였습니다.
+ 프로젝트에서 사용하는 DB 테이블은 스크립트를 이용해 생성합니다. ( src/main/resources/sql/create-tables.sql )
