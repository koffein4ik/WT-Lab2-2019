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
    <td>Salary</td>
    <td>Phone number</td>
    <td>Hours worked this month</td>
    <c:forEach var="driver" items="${drivers}">
    <tr>
        <td>${driver.name}</td>
        <td>${driver.surname}</td>
        <td>${driver.salary}</td>
        <td>${driver.phoneNumber}</td>
        <td>${driver.hoursWorkedThisMonth}</td>
    </tr>
    </c:forEach>
    <!-- <p>${drivers}</p> -->
</table>