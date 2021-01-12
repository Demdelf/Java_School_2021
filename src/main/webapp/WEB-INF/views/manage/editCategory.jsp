<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.01.2021
  Time: 20:52
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
    <title>Category</title>
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
<form:form method="post" modelAttribute="categoryDto" action="/manage/categories/edit/${categoryDto.id}">
    <table>
        <tr>
            <td><strong>Name</strong></td>
            <td><input type="text" name="name" value="${categoryDto.name}"/></td>
        </tr>
    </table>

    <tr>
        <td><input type="submit" value="Update"></td>
    </tr>
</form:form>


</body>
</html>

