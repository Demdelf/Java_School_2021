<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.01.2021
  Time: 13:32
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
    <title>Cart</title>
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
<h1>Cart</h1>

<form name='clear' action="/cart/clear" method='Post'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Clear cart" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<form name='create' action="/order" method='Post'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Create order" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

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
        <td><strong>Quantity</strong></td>
    </tr>
    <c:forEach items="${cart.products}" var="product">
        <tr>
            <td>
                <form name='addToCart' action="/customer/products/${product.key.id}" method='Get'>
                    <input name="submit" type="submit" value="${product.key.name}" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </td>
            <td>${product.key.price}</td>
            <td>${product.key.category}</td>
            <td>${product.value}</td>
            <td>
                <form name='addToCart' action="/cart/add/${product.key.id}" method='Post'>
                    <input name="submit" type="submit" value="+" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </td>
            <td>
                <form name='addToCart' action="/cart/sub/${product.key.id}" method='Post'>
                    <input name="submit" type="submit" value="-" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </td>
            <td>
                <form name='addToCart' action="/cart/delete/${product.key.id}" method='Post'>
                    <input name="submit" type="submit" value="delete" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
