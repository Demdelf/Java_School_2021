<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2021
  Time: 23:55
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
<h1>${categoryDto.name}</h1>

<table>
    <tr>
        <td>
            <form name='get' action="/cart" method='Get'>
                <input name="submit" type="submit" value="CART" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </td>
        <td><strong>${cart.quantity}</strong></td>
    </tr>
</table>

<form name='create' action="/customer" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="To catalog" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<table>
    <tr>
        <td><strong>Name</strong></td>
        <td><strong>Price</strong></td>
        <td><strong>Category</strong></td>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>
                <form name='addToCart' action="/customer/products/${product.id}" method='Get'>
                    <input name="submit" type="submit" value="${product.name}" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </td>
            <td>${product.price}</td>
            <td>${product.category.name}</td>
            <td>
                <form name='addToCart' action="/cart/add/${product.id}" method='Post'>
                    <input name="submit" type="submit" value="add to cart" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
