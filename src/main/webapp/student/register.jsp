<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: minsu
  Date: 2023/11/26
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<c:choose>
    <c:when test="${empty student}">
        <c:set var="action" value="/student/register.do"/>
    </c:when>
    <c:otherwise>
        <c:set var="action" value="/student/update.do"/>
    </c:otherwise>
</c:choose>
<form method="post" action="${action}">
    <table>
        <tbody>
        <tr>
            <th>ID</th>
            <td><input type="text" name="id" value="${student.id}" required/></td>
        </tr>
        <tr>
            <th>이름</th>
            <td><input type="text" name="name" value="${student.name}" required/></td>
        </tr>
        <tr>
            <th>성별</th>
            <td>
                <input type="checkbox" name="gender" value="M" ${student.gender eq 'M' ? 'checked':''} />남
                <input type="checkbox" name="gender" value="F" ${student.gender eq 'F' ? 'checked':''} />여
            </td>
            /
        </tr>
        <tr>
            <th>나이</th>
            <td><input type="text" name="age" value="${student.age}" required/></td>
        </tr>
        </tbody>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>

    </p>

</form>
</body>
</html>
