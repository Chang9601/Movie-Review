# 영화 리뷰 사이트

## 소개
스프링 부트를 사용한 영화 리뷰 사이트

## 목적
* 스프링 부트에 대한 전반적인 설정과 작동원리를 파악한다.
* 자바스크립트의 Ajax를 통해서 클라이언트와 서버의 비동기 통신을 이해하고 CRUD 메서드를 구현한다.
* 외부 API를 사용해서 API 사용 숙달도를 높인다.
* MVC 패턴을 통해서 Model, View, Controller의 개념을 다시 한번 정리한다.

## 개발 환경
* IDE
  * Spring Tool Suite 4.11.1.RELEASE
* Framework
  * Spring Boot 2.5.6
  * MyBatis 2.2.0
  * Bootstrap 4.5.2 
* Language
  * HTML5/CSS3
  * JavaScript
  * Ajax
  * jQuery
* Template
  * Thymeleaf 3.0.12.RELEASE
* OS
  * Windows 10 64-bit
* DB
  * MySQL DB

## 제작 기간
2021.10.29 - 2021.11.27

## 개발 인원
1명

## 기능
1. 회원가입, 로그인
   * 아이디, 비밀번호, 이메일 유효성 검사
   * 아이디 중복검사     

2. 회원정보 조회
   * 회원정보 수정, 회원탈퇴, 회원이 작성한 리뷰목록
   * 회원탈퇴 시 작성한 모든 리뷰삭제

3. 영화
   * 사이트 내 영화검색/네이버 영화 API를 활용한 외부 영화검색(네이버 링크 제공)
   * 사진, 제목, 배우, 줄거리 표시(jsoup과 네이버 영화 API의 링크 활용)
   * 회원의 평점을 계산한 평균 평점(리뷰가 없을 경우 표시 X)과 리뷰쓰기 링크

4. 리뷰
   * 별점은 0점부터 5점까지이며 부동 소수점 입력가능(e.g. 1.2, 4.6)
   * 리뷰 중복검사를 통해서 회원 당 1개의 리뷰만 작성가능
   * 글자수의 최댓값을 지정하고 하단에 남아있는 글자수를 표시
   * 글을 작성한 회원만 수정/삭제 가능, 다른 회원은 리뷰 추천가능
   * 리뷰추천은 한 리뷰에 한 번만 가능

5. 관리자
   * 회원목록 페이지를 통해서 회원정보 수정, 회원삭제, 회원 리뷰목록
   * 회원삭제 시 회원이 작성한 모든 리뷰삭제

## 사진

### 회원가입
![회원가입](https://user-images.githubusercontent.com/79137839/152134371-3785121d-a820-477e-8977-2555ad8cdac1.PNG)

### 회원정보
![회원정보](https://user-images.githubusercontent.com/79137839/152134506-6c1b83c3-cccd-4ba6-96bc-3bcf124f9f0f.PNG)

### 영화
![영화1](https://user-images.githubusercontent.com/79137839/152134528-4173d3f4-afb2-4b99-a4b8-0d7a78be09ca.PNG)
![영화2](https://user-images.githubusercontent.com/79137839/152134539-3cbd9b0b-ecad-4c54-8faf-24f8a022e57e.PNG)

### 리뷰
![리뷰1](https://user-images.githubusercontent.com/79137839/152134571-65f2523b-0ab9-492f-b405-1607c45b071d.PNG)
![리뷰2](https://user-images.githubusercontent.com/79137839/152134575-f9568629-335a-46a6-9007-d1f45788d0ef.PNG)
![리뷰3](https://user-images.githubusercontent.com/79137839/152134577-b57c889a-f047-4291-ac4a-26b7f057e624.PNG)
![리뷰4](https://user-images.githubusercontent.com/79137839/152134580-64ea1f3c-c847-46cf-9491-7ac903b4e529.PNG)
![리뷰5](https://user-images.githubusercontent.com/79137839/152134584-958715ec-cbf1-4fea-8bc9-deb63de0a1b2.PNG)

### 관리자
![관리자](https://user-images.githubusercontent.com/79137839/152134974-eef92fce-6999-42e6-bd1c-251a5bfac258.PNG)

