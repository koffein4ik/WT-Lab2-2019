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
    <td>Cargo capacity</td>
    <c:forEach var="truck" items="${trucks}">
    <tr>
        <td>${truck.brand}</td>
        <td>${truck.model}</td>
        <td>${truck.color}</td>
        <td>${truck.number}</td>
        <td>${truck.paymentPerMinute}</td>
        <td>${truck.cargoCapacity}</td>
    </tr>
    </c:forEach>
</table>