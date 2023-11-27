<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: minsu
  Date: 2023/11/26
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학생 조회</title>
    <link rel="stylesheet" href="/style.css">
    <style>
        table {
            width: 800px;
            border-collapse: collapse;
            border: 1px #ccc solid;
        }

        table tr > td, th {
            padding: 5px;
            border: 1px #ccc solid;
        }
    </style>

</head>
<body>
<table>
    <tbody>
    <!-- todo view 구현 -->
    <c:set var="st" value="${student}"/>
    <tr>
        <td>아이디</td>
        <td>${st.id}</td>
    </tr>
    <tr>
        <td>이름</td>
        <td>${st.name}</td>
    </tr>
    <tr>
        <td>성별</td>
        <td>${st.gender}</td>
    </tr>
    <tr>
        <td>나이</td>
        <td> ${st.age}</td>
    </tr>
    <tr>
        <td>등록일</td>
        <td> ${st.createdAt}</td>
    </tr>


    </tbody>

</table>

<ul>
    <li><a href="studentManager.do">리스트</a></li>
    <li>
        <!-- todo ${update_link} 설정 c:url -->
        <c:url var="update_link" value="/student/update.do">
            <c:param name="id" value="${st.id}"/>
        </c:url>
        <a href="${update_link}">수정</a>
    </li>

    <li>
        <!-- todo 삭제 버튼 구현, method=post -->
        <form method="post" action="/student/delete.do">
            <input type="hidden" name="id" value="${st.id}">
            <button type="submit">삭제</button>
        </form>
    </li>
</ul>
</body>
</html>
