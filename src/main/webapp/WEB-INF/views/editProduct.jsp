<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.01.2021
  Time: 20:49
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
<form:form action="editOne" method="post" modelAttribute="productDto">
    <table>
        <tr>
            <td><strong>Name</strong></td>
            <td><strong>Price</strong></td>
            <td><strong>Category</strong></td>
        </tr>
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.category.name}</td>
        </tr>
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

        <c:forEach items="${properties}" var="property">
            <tr>
                <td> property.name </td>
                <td>
                    <form:input path="type" /> <br />
                    <form:errors path="type" cssClass="error" />
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td><input type="submit" value="Submit"></td>
        </tr>
            <%--        <tr>--%>
            <%--            <td colspan="2"><button type="submit">Submit</button></td>--%>
            <%--        </tr>--%>
    </table>
</form:form>


</body>
</html>
