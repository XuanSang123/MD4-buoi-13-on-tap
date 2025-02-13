<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maixu
  Date: 9/12/2024
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>danh sach khach hang</h1>
<a href="/views/add-customer.jsp">them moi khach hang</a>
<table border="10" cellspacing="10" cellpadding="10">
    <thead>
    <tr>
        <th>STT</th>
        <th>Name</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cus" items="${customers}" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${cus.name}</td>
            <td><a href="/customers?action=DETAIL&id=${cus.id}">Details</a></td>
            <td><a href="/customers?action=EDIT&id=${cus.id}">Edit</a></td>
            <td><a href="/customers?action=DELETE&id=${cus.id}">Delete</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
