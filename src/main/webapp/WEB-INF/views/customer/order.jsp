<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.01.2021
  Time: 14:23
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
    <title>Order</title>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>

<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <%--        <a class="p-2 text-dark" href="#">Features</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Enterprise</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Support</a>--%>
            <td>
                <form name='get' action="/cart" method='Get'>
                    <input name="submit" type="submit" value="CART" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </td>
            <td><strong>${cart.quantity}</strong></td>
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
    </nav>
    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>

<body>
<h1>Order</h1>

<form name='clear' action="/order/delete" method='Post'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Delete order" /></td>
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

    <form:form action="update/${orderDto.id}" method="post" modelAttribute="updateOrderDto">
        <table>
            <tr>
                <td>Payment Method</td>
                <td>
                    <select id="dropdownPM" name="paymentMethod">
                        <c:forEach var="paymentMethod" items="${paymentMethods}">
                            <option value="<c:out value='${paymentMethod.name}' />"
                                    <c:if test="${param.selectValue == paymentMethod.name})"> selected </c:if>  >
                                <c:out value="${paymentMethod.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" value="Save"></td>
                <td></td>
            </tr>

        </table>
    <table>
    </form:form>



    <tr>
        <td>Products</td>
    </tr>

    <tr>
        <td><strong>Name</strong></td>
        <td><strong>Price</strong></td>
        <td><strong>Category</strong></td>
        <td><strong>Quantity</strong></td>
    </tr>
    <c:forEach items="${orderDto.orderProducts}" var="product">
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
<%--            <td>--%>
<%--                <form name='addToCart' action="/cart/add/${product.key.id}" method='Post'>--%>
<%--                    <input name="submit" type="submit" value="+" />--%>
<%--                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--                </form>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <form name='addToCart' action="/cart/sub/${product.key.id}" method='Post'>--%>
<%--                    <input name="submit" type="submit" value="-" />--%>
<%--                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--                </form>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <form name='addToCart' action="/cart/delete/${product.key.id}" method='Post'>--%>
<%--                    <input name="submit" type="submit" value="delete" />--%>
<%--                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--                </form>--%>
<%--            </td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
