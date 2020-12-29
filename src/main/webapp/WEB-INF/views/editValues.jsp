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
<form:form action="create" method="post" modelAttribute="value">
    <table>
        <tr>
            <td>Name</td>
            <td>
                <form:input path="name" /> <br />
                <form:errors path="name" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td>Value</td>
            <td>
                <form:input path="value" /> <br />
                <form:errors path="value" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">Submit</button></td>
        </tr>
    </table>
</form:form>

<h2>Values List</h2>
<table>
    <tr>
        <td><strong>Name</strong></td>
        <td><strong>Value</strong></td>
    </tr>
    <c:forEach items="${values}" var="value">
        <tr>
            <td>${value.name}</td>
            <td>${value.value}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

