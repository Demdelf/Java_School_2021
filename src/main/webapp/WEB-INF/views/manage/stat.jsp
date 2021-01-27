<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.01.2021
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Statistic</title>


    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <style>
        .themed-grid-col {
            padding-top: .75rem;
            padding-bottom: .75rem;
            background-color: rgba(86, 61, 124, .15);
            /*border: 1px solid rgba(86, 61, 124, .2);*/
        }

        .themed-container {
            padding: .75rem;
            margin-bottom: 1.5rem;
            background-color: rgba(0, 123, 255, .15);
            border: 1px solid rgba(0, 123, 255, .2);
        }

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="../css/form-validation.css" type="text/css">
</head>


<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <%--        <a class="p-2 text-dark" href="#">Features</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Enterprise</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Support</a>--%>
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
            <a class="btn btn-outline-primary" href="http://localhost:8080/manage">Manage</a>
    </nav>
    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>


<body>




<div class="container">
    <div class="row my-2">
        <div class="order-lg-2">
            Best products
                <div class="tab-content py-4">
                    <c:forEach items="${stat.bestProducts}" var="product">

                        <div class="row  mb-3">
                            <div class="col-md-2 themed-grid-col">
                                <form name='addToCart' action="/customer/products/${product.key.id}" method='Get'>
                                    <input name="submit" type="submit" value="${product.key.name}"/>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </div>
                            <div class="col-md-1 themed-grid-col">${product.key.category}</div>
                            <div class="col-md-2 themed-grid-col">${product.value}</div>
                        </div>
                    </c:forEach>

                </div>
        </div>
    </div>

    <div class="row my-2">
        <div class="order-lg-2">
            Best customers
            <div class="tab-content py-4">
                <c:forEach items="${stat.bestCustomers}" var="customers">

                    <div class="row  mb-3">
                        <div class="col-md-2 themed-grid-col">${customers.key.email}</div>
                        <div class="col-md-1 themed-grid-col">${customers.key.firstName}</div>
                        <div class="col-md-1 themed-grid-col">${customers.key.lastName}</div>
                        <div class="col-md-2 themed-grid-col">${customers.value}</div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>

    <div class="row my-2">
        <div class="order-lg-2">
            Revenue
            <div class="tab-content py-4">
                ${stat.revenue}
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>

</div>
</body>
</html>

