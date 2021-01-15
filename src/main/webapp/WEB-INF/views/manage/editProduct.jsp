<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.01.2021
  Time: 20:49
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
<h1>Edit product</h1>
<form:form method="post" modelAttribute="productDto" action="/manage/products/edit/${productDto.id}">
    <table>
        <tr>
            <td><strong>Name</strong></td>
            <td><input type="text" name="name" value="${productDto.name}"/></td>
        </tr>
        <tr>
            <td><strong>Price</strong></td>
            <td><input type="text" name="price" value="${productDto.price}"/></td>
        </tr>

<%--        <tr>--%>
<%--            <td><strong>Category</strong></td>--%>
<%--            <td>${productDto.category}</td>--%>
<%--        </tr>--%>

        <tr>
            <td>Category</td>
            <td>
                <select id="dropdown" name="category">
                    <c:forEach var="category" items="${categories}">
                        <option value="<c:out value='${category.name}' />"
                                <c:if test="${param.selectValue == category.name})"> selected </c:if>  >
                            <c:out value="${category.name}" />
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <table>
            <tr>
                <th>Property</th>
                <th>Value</th>
            </tr>
            <tr>
                <c:forEach var="property" items="${productDto.properties}">

                    <td>${property.value}</td>

                </c:forEach>
                <c:forEach var="propertyValues" items="${productDto.propertyValues}">

                    <td><input type="text" name="propertyValues['${propertyValues.key}']" value="${propertyValues.value}"/></td>

                </c:forEach>
            </tr>
        </table>
    </table>

    <tr>
        <td><input type="submit" value="Update"></td>
    </tr>
</form:form>


</body>
</html>
