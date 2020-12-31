<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.12.2020
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Property Values</title>
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
<h1>Input Form</h1>
<form:form action="create" method="post" modelAttribute="productDto">
    <table>
        <tr>
            <td>Name</td>
            <td>
                <form:input path="name" /> <br />
                <form:errors path="name" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td>Price</td>
            <td>
                <form:input path="price" /> <br />
                <form:errors path="price" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td>Category</td>
            <td>
                <select id="dropdown">
                    <c:forEach var="category" items="${categories}">
                        <option value="<c:out value='${category}' />"
                                <c:if test="${param.selectValue == category})"> selected </c:if>  >
                            <c:out value="${category.name}" />
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">Submit</button></td>
        </tr>
    </table>
</form:form>

<h2>Product List</h2>
<table>
    <tr>
        <td><strong>Name</strong></td>
        <td><strong>Price</strong></td>
        <td><strong>Category</strong></td>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.category}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

