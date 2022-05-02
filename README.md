# 영화 리뷰 사이트

## 소개
Spring Security, OAuth 2.0 로그인과 JPA를 사용한 영화 리뷰 사이트

## 목적
* Spring Boot에 대한 전반적인 설정과 작동원리를 파악
* JavaScript의 Promise와 async, await을 사용해서 비동기의 개념을 이해하고 적용
* MVC 패턴을 통해서 Model, View, Controller의 개념 정리
* OAuth 2.0 로그인의 작동 원리를 이해하고 적용(Google, Naver)
* JPA로 연관관계 매핑, 지연 로딩, 영속성 전이를 이해하고 적용
* 원본 프로젝트를 Spring Security, OAuth 2.0 로그인과 JPA를 사용해서 리팩토링

## 개발 환경
* IDE
  * Spring Tool Suite 4.11.1.RELEASE
* Framework
  * Spring Boot 2.5.6
  * Spring Security
  * Bootstrap 4.5.2 
* ORM
  * JPA
* Language
  * HTML5/CSS3
  * JavaScript
  * Java
* Template
  * Thymeleaf 3.0.12.RELEASE
* OS
  * Windows 10 64-bit
* DB
  * MySQL DB

## 제작 기간
2021.10.29 - 2021.11.27

## 리팩토링 기간
2022.04.19 - 2022.04.29

## 개발 인원
1명

## 기능
1. 회원가입
   * 아이디, 비밀번호, 이메일 유효성 검사
   * 아이디 중복검사

2. 로그인
   * 일반 로그인과 구글, 네이버 로그인
   
3. 회원정보 조회
   * 회원정보 수정, 회원탈퇴, 회원이 작성한 리뷰목록
   * 회원수정의 경우 일반 로그인과 OAuth 2.0 로그인 구분
   * 회원탈퇴 시 작성한 모든 리뷰, 모든 댓글 삭제

4. 영화
   * 영화 검색 기능(네이버 영화 API)
   * 사진, 제목, 배우, 줄거리 표시

5. 리뷰
   * 별점은 0점부터 5점까지이며 부동 소수점 입력가능(e.g. 1.2, 4.6)
   * 글자수의 최댓값(5,000자)을 지정하고 하단에 남아있는 글자수를 표시
   * 글을 작성한 회원만 수정/삭제 가능
   * 제목/내용 기준으로 리뷰 검색 기능

6. 댓글
   * 로그인한 회원만 댓글 작성 가능
   * 댓글을 작성한 회원만 수정/삭제 가능

## 스크린샷

### 회원가입
![회원가입](https://user-images.githubusercontent.com/79137839/166198369-e5630fbf-1119-4bba-9ccc-b6cfb2e54cf4.PNG)

### 로그인
![로그인](https://user-images.githubusercontent.com/79137839/166198383-554587fe-d353-42e6-ac80-ecf6444812d2.PNG)
![일반 vs  OAuth 2 0](https://user-images.githubusercontent.com/79137839/166198478-b888d72e-88c1-4b3c-afda-10ad07483098.PNG)

### 회원정보
![회원정보](https://user-images.githubusercontent.com/79137839/166198426-10ccbfe4-fa0e-4a18-8b82-b8680eea77ec.PNG)

### 회원수정
![일반 회원수정](https://user-images.githubusercontent.com/79137839/166198501-390d5049-08a7-4564-ae91-83449ceed14f.PNG)
![OAuth 2 0 회원수정](https://user-images.githubusercontent.com/79137839/166198518-a0dd699e-4e4f-4c6f-a45c-f95cff112859.jpg)

### 영화
![영화검색1](https://user-images.githubusercontent.com/79137839/166198575-2a18c5d3-74a3-4a24-b112-54f9844a5ec6.PNG)
![영화검색2](https://user-images.githubusercontent.com/79137839/166198583-a3f67090-96f1-4942-aac2-cb23448fbb86.PNG)

### 리뷰작성
![리뷰작성1](https://user-images.githubusercontent.com/79137839/166198618-0fced4e9-a34a-4a60-82b7-bf5e3e4527e2.PNG)
![리뷰작성2](https://user-images.githubusercontent.com/79137839/166198628-d4a95901-e09f-4090-a373-e8e740d97b05.PNG)

### 리뷰읽기
![리뷰표시](https://user-images.githubusercontent.com/79137839/166198807-dbb4e59e-eb61-493d-9b57-21429c1b6f27.jpg)
![리뷰읽기](https://user-images.githubusercontent.com/79137839/166198651-69140fde-0922-499a-96d2-616d05780a6d.PNG)

### 댓글
![댓글작성1](https://user-images.githubusercontent.com/79137839/166198702-9a684235-cf49-4be0-8fbc-ed6779e0e805.PNG)
![댓글작성2](https://user-images.githubusercontent.com/79137839/166198707-f7f7c089-c02f-4943-b840-b47b6356d0cc.PNG)
![댓글수정](https://user-images.githubusercontent.com/79137839/166198741-d6b294f8-f697-4a91-9d11-4017ffcb51e5.jpg)
![비로그인 댓글](https://user-images.githubusercontent.com/79137839/166198752-5cf02fbe-730e-4168-877f-639b500fb9bb.jpg)





