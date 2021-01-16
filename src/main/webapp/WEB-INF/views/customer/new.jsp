

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Products</title>
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
<h1>Registration</h1>
<form:form action="new" method="post" modelAttribute="userRegDto">
	<table>
		<tr>
			<td>Email</td>
			<td>
				<form:input path="email" /> <br />
				<form:errors path="email" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td>Password</td>
			<td>
				<form:input path="password" /> <br />
				<form:errors path="password" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Submit"></td>
			<td></td>
		</tr>

	</table>
</form:form>


</body>
</html>
