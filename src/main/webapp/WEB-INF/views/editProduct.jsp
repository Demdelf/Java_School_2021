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
<form:form method="post" modelAttribute="productDto" action="/products/edit/${productDto.id}">
    <table>
        <tr>
            <td><strong>Name</strong></td>
            <td>${productDto.name}</td>
        </tr>
        <tr>
            <td><strong>Price</strong></td>
            <td>${productDto.price}</td>
        </tr>

        <tr>
            <td><strong>Category</strong></td>
            <td>${productDto.category}</td>
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

<%--        <c:forEach items="${properties}" var="property">--%>
<%--            <tr>--%>
<%--                <td>property.name</td>--%>
<%--                <td>--%>
<%--                    <form:input path="propertyValues" /> <br />--%>
<%--                    <form:errors path="name" cssClass="error" />--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

<%--        <c:forEach items="${properties}" var="property">--%>
<%--            <tr>--%>
<%--                <td>${property.name}</td>--%>
<%--                <td>--%>
<%--                    <form:input path="${propertyValues.value}" /> <br />--%>
<%--&lt;%&ndash;                <td><input type="text" name="" value="${configParams.value}" /></td>&ndash;%&gt;--%>
<%--                    <form:errors path="propertyValues" cssClass="error" />--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

        <c:forEach var="p" items="${map}">
            <!--  KEY: ${configParams.key}  - VALUE: ${configParams.value} -->

            <tr>
                <td>
                    <c:out value="${map.key}" />
                </td>
                <td><input type="text" name="" value="${map.value}" /></td>

            </tr>
        </c:forEach>


            <%--        <tr>--%>
            <%--            <td colspan="2"><button type="submit">Submit</button></td>--%>
            <%--        </tr>--%>
    </table>

    <tr>
        <td><input type="submit" value="Submit"></td>
    </tr>
</form:form>


</body>
</html>
