<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2021
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--         pageEncoding="ISO-8859-1" %>--%>
<%--<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
<%--    <title>Product</title>--%>
<%--    <style type="text/css">--%>
<%--        .error {--%>
<%--            color: red;--%>
<%--        }--%>

<%--        table {--%>
<%--            width: 50%;--%>
<%--            border-collapse: collapse;--%>
<%--            border-spacing: 0px;--%>
<%--        }--%>

<%--        table td {--%>
<%--            border: 1px solid #565454;--%>
<%--            padding: 20px;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>${categoryDto.name}</h1>--%>

<%--<table>--%>
<%--    <tr>--%>
<%--        <td>--%>
<%--            <form name='get' action="/cart" method='Get'>--%>
<%--                <input name="submit" type="submit" value="CART" />--%>
<%--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--            </form>--%>
<%--        </td>--%>
<%--        <td><strong>${cart.quantity}</strong></td>--%>
<%--    </tr>--%>
<%--</table>--%>

<%--<form name='create' action="/customer" method='Get'>--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td colspan='2'><input name="submit" type="submit" value="To catalog" /></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--</form>--%>

<%--<table>--%>
<%--    <tr>--%>
<%--        <td><strong>Name</strong></td>--%>
<%--        <td><strong>Price</strong></td>--%>
<%--        <td><strong>Category</strong></td>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${products}" var="product">--%>
<%--        <tr>--%>
<%--            <td>--%>
<%--                <form name='addToCart' action="/customer/products/${product.id}" method='Get'>--%>
<%--                    <input name="submit" type="submit" value="${product.name}" />--%>
<%--                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--                </form>--%>
<%--            </td>--%>
<%--            <td>${product.price}</td>--%>
<%--            <td>${product.category.name}</td>--%>
<%--            <td>--%>
<%--                <form name='addToCart' action="/cart/add/${product.id}" method='Post'>--%>
<%--                    <input name="submit" type="submit" value="add to cart" />--%>
<%--                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--                </form>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
<%--</body>--%>
<%--</html>--%>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2021
  Time: 18:04
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

        #slider-container {
            max-width: 100%;
            width: 250px;
            margin-left: 0px;
        }

        ul {
            list-style: none;
        }

        .filter-project {
            max-width: 600px;
            background-color: #f2f2f2;
            padding: 20px;
        }

        .amount-box {
            width: 100%;
            max-width: 400px;
        }

        .amount-box input[type=text], .amount-box input[type=tel] {
            display: block;
            border: 1px solid #ccc;
            padding: 8px 10px;
            width: 100%;
            font-size: 16px;
            color: #272727;
            -webkit-appearance: none;
            border-radius: 0;
        }

        button {
            width: 100%;
            font-size: 19px !important;
            border-radius: 2px;
            padding: 8px 20px;
        }

    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script type="text/html" src="../resources/js/filter.js"></script>
</head>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <%--        <a class="p-2 text-dark" href="#">Features</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Enterprise</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Support</a>--%>
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
        <a class="btn btn-success btn-sm ml-3" href="http://localhost:8080/cart">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart"
                 viewBox="0 0 16 16">
                <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
            <i class="fa fa-shopping-cart bi bi-cart"></i> Cart
            <span class="badge badge-light">${cart.quantity}</span>
        </a>
        <a class="btn btn-outline-primary" href="http://localhost:8080/manage">Manage</a>
    </nav>

    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>

<body>
<%--<table>--%>
<%--    <tr>--%>
<%--        <c:forEach items="${categories}" var="category">--%>
<%--            <td>--%>
<%--                <form name='get' action="/customer/categories/${category.id}" method='Get'>--%>
<%--                    <input name="submit" type="submit" value="${category.name}"/>--%>
<%--                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--                </form>--%>
<%--            </td>--%>
<%--        </c:forEach>--%>
<%--    </tr>--%>
<%--</table>--%>

<%--<h1>Our finest selection</h1>--%>
<%--<table>--%>
<%--    <tr>--%>
<%--        <td><strong>Name</strong></td>--%>
<%--        <td><strong>Price</strong></td>--%>
<%--        <td><strong>Category</strong></td>--%>
<%--    </tr>--%>

<%--</table>--%>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <div class="list-group">
                <c:forEach items="${categories}" var="category">
                    <a href="/customer/categories/${category.id}" class="list-group-item">${category.name}</a>
                    <%--                    <td>--%>
                    <%--                        <form name='get' action="/customer/categories/${category.id}" method='Get'>--%>
                    <%--                            <input name="submit" type="submit" value="${category.name}" />--%>
                    <%--                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
                    <%--                        </form>--%>
                    <%--                    </td>--%>
                </c:forEach>
            </div>

            <div class="container filter-project">
                <div class="row">
                    <div class="col-md-12">
                        <form class=" ">
                            <h4 class=" ">Filter by price</h4>
                            <div class="row">
                                <div class="col-md-7">
                                    <ul>
                                        <li>
                                            <div class="form-group clearfix">
                                                <label>Current price range
                                                </label>
                                                <div id="slider-container"></div>
                                            </div>
                                        </li>
                                        <li class="clearfix">
                                            <div class="form-group clearfix">
                                                <div class="amount-box">
                                                    <div class="col-sm-6">
                                                        <label for="amount-from">From: </label>
                                                        <input type="text" id="amount-from"
                                                               onkeypress="return isNumberKey(event)" value="696314">
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <label for="amount-to">To: </label>
                                                        <input type="tel" id="amount-to"
                                                               onkeypress="return isNumberKey(event)" value="33000000">
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row row-filter">
                                <div class="row-custom ea-button-wrap-car clearfix">
                                    <div class="col-sm-6 col-md-6">
                                        <button type="reset" class="btn btn-info btn-filter ng-scope" ng-click="reset()"
                                                translate="btnReset">Reset
                                        </button>
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <button type="submit" class="btn btn-filter btn-bidnow ng-scope"
                                                ng-click="filter()" translate="lblFilter">Filter
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>


        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">
            <h1>${categoryDto.name} category products </h1>

            <%--            <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">--%>
            <%--                <ol class="carousel-indicators">--%>
            <%--                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>--%>
            <%--                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>--%>
            <%--                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>--%>
            <%--                </ol>--%>
            <%--                <div class="carousel-inner" role="listbox">--%>
            <%--                    <div class="carousel-item active">--%>
            <%--                        <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="First slide">--%>
            <%--                    </div>--%>
            <%--                    <div class="carousel-item">--%>
            <%--                        <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="Second slide">--%>
            <%--                    </div>--%>
            <%--                    <div class="carousel-item">--%>
            <%--                        <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="Third slide">--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">--%>
            <%--                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
            <%--                    <span class="sr-only">Previous</span>--%>
            <%--                </a>--%>
            <%--                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">--%>
            <%--                    <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
            <%--                    <span class="sr-only">Next</span>--%>
            <%--                </a>--%>
            <%--            </div>--%>

            <div class="row">
                <c:forEach items="${products}" var="product">
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card h-100">
                            <a href="/customer/products/${product.id}"><img class="card-img-top"
                                                                            src="http://russianholds.com/59-thickbox_default/i003-casus.jpg"
                                                                            alt=""></a>
                            <div class="card-body">
                                <h4 class="card-title">
                                    <a href="/customer/products/${product.id}">${product.name}</a>
                                </h4>
                                <h5>${product.price} $</h5>
                                    <%--                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam--%>
                                    <%--                                    aspernatur!</p>--%>
                            </div>
                            <div class="card-footer">
                                <form name='addToCart' action="/cart/add/${product.id}" method='Post'>
                                    <input name="submit" type="submit" value="add to cart"/>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </div>
                        </div>
                    </div>

                    <%--                    <tr>--%>
                    <%--                        <td>--%>
                    <%--                            <form name='addToCart' action="" method='Get'>--%>
                    <%--                                <input name="submit" type="submit" value="${product.name}"/>--%>
                    <%--                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                    <%--                            </form>--%>
                    <%--                        </td>--%>
                    <%--                        <td>${product.price}</td>--%>
                    <%--                        <td>${product.category.name}</td>--%>
                    <%--                        <td>--%>
                    <%--                            <form name='addToCart' action="/cart/add/${product.id}" method='Post'>--%>
                    <%--                                <input name="submit" type="submit" value="add to cart"/>--%>
                    <%--                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                    <%--                            </form>--%>
                    <%--                        </td>--%>
                    <%--                    </tr>--%>
                </c:forEach>

            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Russianholds 2020</p>
    </div>
    <!-- /.container -->
</footer>

<%--<!-- Bootstrap core JavaScript -->--%>
<%--<script src="vendor/jquery/jquery.min.js"></script>--%>
<%--<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>--%>


<script type="text/html" src="../resources/js/filter.js"></script>
</body>
</html>
