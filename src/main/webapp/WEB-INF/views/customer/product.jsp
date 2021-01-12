<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2021
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Product</title>
    <style type="text/css">
        .error {
            color: red;
        }

        table {
            width: 50%;
            border-collapse: collapse;
            border-spacing: 0px;
        }

        table td {
            border: 1px solid #565454;
            padding: 20px;
        }
    </style>
</head>
<body>
<h1>${productDto.name}</h1>
<table>
    <tr>
        <td><strong>Price</strong></td>
        <td><strong>Category</strong></td>
    </tr>
        <tr>
            <td>${productDto.price}</td>
            <td>${productDto.category}</td>
        </tr>
</table>

<h3>Product Details </h3>

<table>
    <tr>
        <c:forEach var="property" items="${productDto.properties}">

            <td>${property.value}</td>

        </c:forEach>
    </tr>
    <tr>
        <c:forEach var="propertyValue" items="${productDto.propertyValues}">
            <td>${propertyValue.value}</td>

        </c:forEach>
    </tr>
</table>
</body>
</html>
