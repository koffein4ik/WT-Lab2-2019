<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<p>Parser: ${parsertype}</p>
<table>
    <td>Name</td>
    <td>Surname</td>
    <td>Nickname</td>
    <td>Salary</td>
    <td>Phone number</td>
    <c:forEach var="admin" items="${admins}">
    <tr>
        <td>${admin.name}</td>
        <td>${admin.surname}</td>
        <td>${admin.nickname}</td>
        <td>${admin.salary}</td>
        <td>${admin.phoneNumber}</td>
    </tr>
    </c:forEach>
    <!-- <p>${admins}</p> -->
</table>
<!-- <p>${admins}</p> -->