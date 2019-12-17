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
    <td>Trips completed</td>
    <td>Money on balance</td>
    <td>Phone number</td>
    <c:forEach var="user" items="${users}">
    <tr>
        <td>${user.name}</td>
        <td>${user.surname}</td>
        <td>${user.nickname}</td>
        <td>${user.tripsCompleted}</td>
        <td>${user.moneyOnBalance}</td>
        <td>${user.phoneNumber}</td>
    </tr>
    </c:forEach>
</table>
<!-- <p>${users}</p> -->