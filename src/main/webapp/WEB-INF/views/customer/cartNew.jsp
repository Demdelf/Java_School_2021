<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.01.2021
  Time: 15:47
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
    <title>Products</title>
    <style type="text/css">
        .themed-grid-col {
            padding-top: .75rem;
            padding-bottom: .75rem;
            background-color: rgba(86, 61, 124, .15);
            border: 1px solid rgba(86, 61, 124, .2);
        }

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

        .bloc_left_price {
            color: #c01508;
            text-align: center;
            font-weight: bold;
            font-size: 150%;
        }

        .category_block li:hover {
            background-color: #007bff;
        }

        .category_block li:hover a {
            color: #ffffff;
        }

        .category_block li a {
            color: #343a40;
        }

        .add_to_cart_block .price {
            color: #c01508;
            text-align: center;
            font-weight: bold;
            font-size: 200%;
            margin-bottom: 0;
        }

        .add_to_cart_block .price_discounted {
            color: #343a40;
            text-align: center;
            text-decoration: line-through;
            font-size: 140%;
        }

        .product_rassurance {
            padding: 10px;
            margin-top: 15px;
            background: #ffffff;
            border: 1px solid #6c757d;
            color: #6c757d;
        }

        .product_rassurance .list-inline {
            margin-bottom: 0;
            text-transform: uppercase;
            text-align: center;
        }

        .product_rassurance .list-inline li:hover {
            color: #343a40;
        }

        .reviews_product .fa-star {
            color: gold;
        }

        .pagination {
            margin-top: 20px;
        }

        footer {
            background: #343a40;
            padding: 40px;
        }

        footer a {
            color: #f8f9fa !important
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <script>
        var q = ${cart.quantity};
        var prodMap = new Map();
        <c:forEach var="prod" items="${cart.products}">
        var pid = ${prod.key.id};
        var pv = ${prod.value};
        prodMap.set(pid, pv);
        </c:forEach>

        function checkEmptyCart() {
            if (q === 0) {
                document.getElementById("creatOrderBut").setAttribute('disabled', 'disabled');
                document.getElementById("creatOrderBut").innerHTML = "Empty cart";
            }
        }

        function addProd(id) {
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/cart/add/" + id, true);
            xhttp.send();
            q = q + 1;
            var pq = prodMap.get(id);
            prodMap.set(id, pq + 1);
            document.getElementById("cartQ").innerHTML = q;
            document.getElementById("prodValue" + id).innerHTML = pq + 1;
        }

        function subProd(id) {
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/cart/sub/" + id, true);
            xhttp.send();
            q = q - 1;
            var pq = prodMap.get(id);
            prodMap.set(id, pq - 1);
            document.getElementById("cartQ").innerHTML = q;
            if (pq === 1) {
                var element = document.getElementById("prodDiv" + id);
                element.parentNode.removeChild(element);
            } else {
                document.getElementById("prodValue" + id).innerHTML = pq - 1;
            }
            checkEmptyCart()
        }

        function delProd(id) {
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/cart/delete/" + id, true);
            xhttp.send();
            q = q - prodMap.get(id);
            document.getElementById("cartQ").innerHTML = q;
            var element = document.getElementById("prodDiv" + id);
            element.parentNode.removeChild(element);
            checkEmptyCart();
        }
    </script>
</head>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
        <a class="btn btn-success btn-sm ml-3" href="cart.html">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart"
                 viewBox="0 0 16 16">
                <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
            <i class="fa fa-shopping-cart bi bi-cart"></i> Cart
            <span class="badge badge-light" id="cartQ">${cart.quantity}</span>
        </a>
    </nav>
    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>

<body>

<div class="tab-content py-4">
    <c:forEach items="${cart.products}" var="product">

        <div class="row  mb-3" id="prodDiv${product.key.id}">
            <div class="col-md-2 themed-grid-col">
                <form name='addToCart' action="/customer/products/${product.key.id}" method='Get'>
                    <input name="submit" type="submit" value="${product.key.name}"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
            <div class="col-md-2 themed-grid-col">
                <a href="/customer/products/${product.key.id}"><img class="card-img-top"
                                                                    src="/products-images/${product.key.id}"
                                                                    alt=""></a>
            </div>
            <div class="col-md-2 themed-grid-col">${product.key.price}</div>
            <div class="col-md-1 themed-grid-col">${product.key.category}</div>
            <div class="col-md-2 themed-grid-col" id="prodValue${product.key.id}">${product.value}</div>
            <div class="col-md-2 themed-grid-col">
                <td>
                    <div class="card-footer">
                        <input type="button" value="+" onClick="addProd(${product.key.id})">
                    </div>
                </td>
                <td>
                    <div class="card-footer">
                        <input type="button" value="-" onClick="subProd(${product.key.id})">
                    </div>
                </td>
                <td>
                    <div class="card-footer">
                        <input type="button" value="delete" onClick="delProd(${product.key.id})">
                    </div>
                </td>
            </div>

        </div>
    </c:forEach>
    <div class="row  mb-3">
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><strong>Total</strong></td>
            <td class="text-right"><strong>${cart.sum}</strong></td>
        </tr>
    </div>
</div>

<div class="col mb-2">
    <div class="row">
        <div class="col-sm-12  col-md-6">
            <%--            <button class="btn btn-block btn-light">Continue Shopping</button>--%>
        </div>
        <div class="col-sm-12 col-md-6 text-right">
            <form name='create' action="/customer/orders/create" method='Get'>
                <button class="btn btn-lg btn-block btn-success text-uppercase" type="submit" id="creatOrderBut">
                    Create order
                </button>
            </form>
        </div>
    </div>
</div>
<script>checkEmptyCart();</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

</body>
</html>

