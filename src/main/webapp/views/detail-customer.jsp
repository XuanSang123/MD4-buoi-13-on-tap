<%--
  Created by IntelliJ IDEA.
  User: maixu
  Date: 9/12/2024
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 >thong tin khach hang</h1>
<a href="/customers?action=GETALL">quay lai</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${cus.id}</td>
        <td>${cus.name}</td>
        <td>${cus.address}</td>
        <td>${cus.email}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
