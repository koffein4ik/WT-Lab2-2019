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
    <td>Brand</td>
    <td>Model</td>
    <td>Color</td>
    <td>Number</td>
    <td>Payment per minute</td>
    <td>Passenger capacity</td>
    <c:forEach var="car" items="${cars}">
    <tr>
        <td>${car.brand}</td>
        <td>${car.model}</td>
        <td>${car.color}</td>
        <td>${car.number}</td>
        <td>${car.paymentPerMinute}</td>
        <td>${car.passengerCapacity}</td>
    </tr>
    </c:forEach>
</table>