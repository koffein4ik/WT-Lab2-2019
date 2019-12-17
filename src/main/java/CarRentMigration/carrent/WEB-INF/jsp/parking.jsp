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
    <td>Location</td>
    <td>Car capacity</td>
    <td>Truck capacity</td>
    <td>Parking id</td>
    <c:forEach var="parking" items="${parkings}">
    <tr>
        <td>${parking.location}</td>
        <td>${parking.carCapacity}</td>
        <td>${parking.truckCapacity}</td>
        <td>${parking.parkingId}</td>
    </tr>
    </c:forEach>
</table>