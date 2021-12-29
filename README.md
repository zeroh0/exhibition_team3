# 전시 / 쇼핑몰 JSP 프로젝트
###### exhibition_team3

- JAVA: 1.8
- SERVER: TOMCAT 9.0
- DB: MySQL, ORACLE  

<br>

### 경로 수정

**controller**

- exhbn.command.ExhbnAddAction
- exhbn.command.ExhbnUpdateAction

``` java
/* 경로 변수 값 변경 */
String realFolder = “/Users/zeroh0/Desktop/upload”;
```

<br>

**view**

- exhbn/exhbnList.jsp
- exhbn/exhbnDetail.jsp

``` html
<!— img태그의 src의 경로를 변경 —>
<img src="/upload/${exh.image}">
```