<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.01.2021
  Time: 20:35
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
    <title>Categories</title>
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
<h1>Add category</h1>
<form:form action="create" method="post" modelAttribute="categoryDto">
    <table>
        <tr>
            <td>Name</td>
            <td>
                <form:input path="name" /> <br />
                <form:errors path="name" cssClass="error" />
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Submit"></td>
            <td></td>
        </tr>

        <table>
            <tr>
                <td><strong>Name</strong></td>
            </tr>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.name}</td>
                </tr>
            </c:forEach>
        </table>
    </table>
</form:form>


</body>
</html>



