<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2021
  Time: 20:43
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
        bd-placeholder-img {
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
    <script>
        var q = ${cart.quantity};
        var pid = ${productDto.id};
        var ps = ${productDto.stock};

        var cartProdMap = new Map();

        function setCartProdMap() {
            // alert("setCartProdMap");
            <c:forEach var="prod" items="${cart.products}">
            var cpid = ${prod.key.id};
            var cpv = ${prod.value};
            cartProdMap.set(cpid, cpv);
            </c:forEach>
        }

        setCartProdMap();

        function checkProductStock() {
            // alert("checkProductStock compare with cart");
            if (cartProdMap.size > 0) {
                var inc = cartProdMap.get(pid)
                if (inc) {
                    ps = ps - inc;
                    <%--alert("${pid} already in cart: ${inc}" + pid.toString());--%>
                }
            }
        }

        checkProductStock();

        function checkStock(id) {
            // alert("check stock");
            if (ps <= 0) {
                <%--alert(" no more on stock: ${id}" + id.toString());--%>
                document.getElementById("addToCart${productDto.id}").setAttribute('disabled', 'disabled');
                document.getElementById("addToCart${productDto.id}").innerHTML = "No more on stock";
            }
        }

        function addProd(id) {
            // alert("add prod")
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/cart/add/" + id, true);
            xhttp.send();
            q = q + 1
            document.getElementById("cartQ").innerHTML = q;
            ps = ps - 1;
            checkStock(id);
        }

        function checkStockFirst() {
            var s = ${productDto.stock};
            if (s === 0) {
                document.getElementById("addToCart${productDto.id}").setAttribute('disabled', 'disabled');
                document.getElementById("addToCart${productDto.id}").innerHTML = "out of stock";
            }
        }
    </script>
    <script type="text/html" src="../resources/js/filter.js"></script>
</head>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
        <a class="btn btn-success btn-sm ml-3" href="http://localhost:8080/cart">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart"
                 viewBox="0 0 16 16">
                <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
            <i class="fa fa-shopping-cart bi bi-cart"></i> Cart
            <span class="badge badge-light" id="cartQ">${cart.quantity}</span>
        </a>
        <a class="btn btn-outline-primary" href="http://localhost:8080/manage">Manage</a>
    </nav>

    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div class="list-group">
                <c:forEach items="${categories}" var="category">
                    <a href="/customer/categories/${category.id}" class="list-group-item">${category.name}</a>
                </c:forEach>
            </div>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">
            <h1>${categoryDto.name}</h1>

            <div class="row">
                <div class="col-8">
                    <div class="card mt-4">
                        <img class="card-img-top" src="data:image/png;base64,${productDto.stringImage}"
                             alt="">
                        <div class="card-body">
                            <h4 class="card-title">
                                ${productDto.name}
                            </h4>
                            <h5>${productDto.price} $</h5>
                            <h6>Weight: ${productDto.weight}</h6>
                            <h6>Volume: ${productDto.volume}</h6>
                            <h6>On stock: ${productDto.stock}</h6>
                            <%--                                <div class="mx-auto col-sm-6">--%>
                            <c:forEach var="property" items="${properties}" varStatus="status">
                                <h6> ${property}: ${propertyValues[status.index]}</h6>
                            </c:forEach>
                            <%--                                </div>--%>


                        </div>
                        <div class="card-footer">
                            <button class="btn btn-success btn-sm ml-3" type="submit"
                                    onClick="addProd(${productDto.id}, ${cart.quantity})"
                                    id="addToCart${productDto.id}">
                                add to cart
                            </button>
                            <%--                            <input type="button" value="add to cart" onClick="addProd(${productDto.id}, ${cart.quantity})">--%>
                        </div>
                    </div>
                </div>


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
<script>
    checkProductStock();
    checkStock(${productDto.id})
    checkStockFirst();
</script>
<script type="text/html" src="../resources/js/filter.js"></script>
</body>
</html>

