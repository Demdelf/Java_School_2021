<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2021
  Time: 18:04
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <%--        <a class="p-2 text-dark" href="#">Features</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Enterprise</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Support</a>--%>
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
            <a class="btn btn-success btn-sm ml-3" href="cart.html">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </svg>
                <i class="fa fa-shopping-cart bi bi-cart"></i> Cart
                <span class="badge badge-light">${cart.quantity}</span>
            </a>
    </nav>

    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>

<body>

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

<table>
    <tr>
<c:forEach items="${categories}" var="category">
    <td>
    <form name='get' action="/customer/categories/${category.id}" method='Get'>
        <input name="submit" type="submit" value="${category.name}" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    </td>
</c:forEach>
    </tr>
</table>

<h1>Our finest selection</h1>
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
