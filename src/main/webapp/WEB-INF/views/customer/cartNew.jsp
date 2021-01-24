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
</head>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <%--        <a class="p-2 text-dark" href="#">Features</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Enterprise</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Support</a>--%>

        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
        <a class="btn btn-success btn-sm ml-3" href="cart.html">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart"
                 viewBox="0 0 16 16">
                <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
            <i class="fa fa-shopping-cart bi bi-cart"></i> Cart
            <span class="badge badge-light">${cart.quantity}</span>
        </a>
    </nav>
    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>

<body>
<!--Section: Block Content-->
<%--<section>--%>

<%--    <!--Grid row-->--%>
<%--    <div class="row">--%>

<%--        <!--Grid column-->--%>
<%--        <div class="col-lg-8">--%>

<%--            <!-- Card -->--%>
<%--            <div class="card wish-list mb-3">--%>
<%--                <div class="card-body">--%>

<%--                    <h5 class="mb-4">Cart (<span>2</span> items)</h5>--%>

<%--                    <div class="row mb-4">--%>
<%--                        <div class="col-md-5 col-lg-3 col-xl-3">--%>
<%--                            <div class="view zoom overlay z-depth-1 rounded mb-3 mb-md-0">--%>
<%--                                <img class="img-fluid w-100"--%>
<%--                                     src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/Vertical/12a.jpg"--%>
<%--                                     alt="Sample">--%>
<%--                                <a href="#!">--%>
<%--                                    <div class="mask waves-effect waves-light">--%>
<%--                                        <img class="img-fluid w-100"--%>
<%--                                             src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/Vertical/12.jpg">--%>
<%--                                        <div class="mask rgba-black-slight waves-effect waves-light"></div>--%>
<%--                                    </div>--%>
<%--                                </a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-7 col-lg-9 col-xl-9">--%>
<%--                            <div>--%>
<%--                                <div class="d-flex justify-content-between">--%>
<%--                                    <div>--%>
<%--                                        <h5>Blue denim shirt</h5>--%>
<%--                                        <p class="mb-3 text-muted text-uppercase small">Shirt - blue</p>--%>
<%--                                        <p class="mb-2 text-muted text-uppercase small">Color: blue</p>--%>
<%--                                        <p class="mb-3 text-muted text-uppercase small">Size: M</p>--%>
<%--                                    </div>--%>
<%--                                    <div>--%>
<%--                                        <div class="def-number-input number-input safari_only mb-0 w-100">--%>
<%--                                            <button onclick="this.parentNode.querySelector('input[type=number]').stepDown()"--%>
<%--                                                    class="minus"></button>--%>
<%--                                            <input class="quantity" min="0" name="quantity" value="1" type="number">--%>
<%--                                            <button onclick="this.parentNode.querySelector('input[type=number]').stepUp()"--%>
<%--                                                    class="plus"></button>--%>
<%--                                        </div>--%>
<%--                                        <small id="passwordHelpBlock" class="form-text text-muted text-center">--%>
<%--                                            (Note, 1 piece)--%>
<%--                                        </small>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="d-flex justify-content-between align-items-center">--%>
<%--                                    <div>--%>
<%--                                        <a href="#!" type="button"--%>
<%--                                           class="card-link-secondary small text-uppercase mr-3"><i--%>
<%--                                                class="fas fa-trash-alt mr-1"></i> Remove item </a>--%>
<%--                                        <a href="#!" type="button" class="card-link-secondary small text-uppercase"><i--%>
<%--                                                class="fas fa-heart mr-1"></i> Move to wish list </a>--%>
<%--                                    </div>--%>
<%--                                    <p class="mb-0"><span><strong>$17.99</strong></span></p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <hr class="mb-4">--%>
<%--                    <div class="row mb-4">--%>
<%--                        <div class="col-md-5 col-lg-3 col-xl-3">--%>
<%--                            <div class="view zoom overlay z-depth-1 rounded mb-3 mb-md-0">--%>
<%--                                <img class="img-fluid w-100"--%>
<%--                                     src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/Vertical/13a.jpg"--%>
<%--                                     alt="Sample">--%>
<%--                                <a href="#!">--%>
<%--                                    <div class="mask waves-effect waves-light">--%>
<%--                                        <img class="img-fluid w-100"--%>
<%--                                             src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/Vertical/13.jpg">--%>
<%--                                        <div class="mask rgba-black-slight waves-effect waves-light"></div>--%>
<%--                                    </div>--%>
<%--                                </a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-7 col-lg-9 col-xl-9">--%>
<%--                            <div>--%>
<%--                                <div class="d-flex justify-content-between">--%>
<%--                                    <div>--%>
<%--                                        <h5>Red hoodie</h5>--%>
<%--                                        <p class="mb-3 text-muted text-uppercase small">Shirt - red</p>--%>
<%--                                        <p class="mb-2 text-muted text-uppercase small">Color: red</p>--%>
<%--                                        <p class="mb-3 text-muted text-uppercase small">Size: M</p>--%>
<%--                                    </div>--%>
<%--                                    <div>--%>
<%--                                        <div class="def-number-input number-input safari_only mb-0 w-100">--%>
<%--                                            <button onclick="this.parentNode.querySelector('input[type=number]').stepDown()"--%>
<%--                                                    class="minus"></button>--%>
<%--                                            <input class="quantity" min="0" name="quantity" value="1" type="number">--%>
<%--                                            <button onclick="this.parentNode.querySelector('input[type=number]').stepUp()"--%>
<%--                                                    class="plus"></button>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="d-flex justify-content-between align-items-center">--%>
<%--                                    <div>--%>
<%--                                        <a href="#!" type="button"--%>
<%--                                           class="card-link-secondary small text-uppercase mr-3"><i--%>
<%--                                                class="fas fa-trash-alt mr-1"></i> Remove item </a>--%>
<%--                                        <a href="#!" type="button" class="card-link-secondary small text-uppercase"><i--%>
<%--                                                class="fas fa-heart mr-1"></i> Move to wish list </a>--%>
<%--                                    </div>--%>
<%--                                    <p class="mb-0"><span><strong>$35.99</strong></span></p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <p class="text-primary mb-0"><i class="fas fa-info-circle mr-1"></i> Do not delay the purchase,--%>
<%--                        adding--%>
<%--                        items to your cart does not mean booking them.</p>--%>

<%--                </div>--%>
<%--            </div>--%>
<%--            <!-- Card -->--%>

<%--            <!-- Card -->--%>
<%--            <div class="card mb-3">--%>
<%--                <div class="card-body">--%>

<%--                    <h5 class="mb-4">Expected shipping delivery</h5>--%>

<%--                    <p class="mb-0"> Thu., 12.03. - Mon., 16.03.</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <!-- Card -->--%>

<%--            <!-- Card -->--%>
<%--            <div class="card mb-3">--%>
<%--                <div class="card-body">--%>

<%--                    <h5 class="mb-4">We accept</h5>--%>

<%--                    <img class="mr-2" width="45px"--%>
<%--                         src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"--%>
<%--                         alt="Visa">--%>
<%--                    <img class="mr-2" width="45px"--%>
<%--                         src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"--%>
<%--                         alt="American Express">--%>
<%--                    <img class="mr-2" width="45px"--%>
<%--                         src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"--%>
<%--                         alt="Mastercard">--%>
<%--                    <img class="mr-2" width="45px"--%>
<%--                         src="https://z9t4u9f6.stackpathcdn.com/wp-content/plugins/woocommerce/includes/gateways/paypal/assets/images/paypal.png"--%>
<%--                         alt="PayPal acceptance mark">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <!-- Card -->--%>

<%--        </div>--%>
<%--        <!--Grid column-->--%>

<%--        <!--Grid column-->--%>
<%--        <div class="col-lg-4">--%>

<%--            <!-- Card -->--%>
<%--            <div class="card mb-3">--%>
<%--                <div class="card-body">--%>

<%--                    <h5 class="mb-3">The total amount of</h5>--%>

<%--                    <ul class="list-group list-group-flush">--%>
<%--                        <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">--%>
<%--                            Temporary amount--%>
<%--                            <span>$25.98</span>--%>
<%--                        </li>--%>
<%--                        <li class="list-group-item d-flex justify-content-between align-items-center px-0">--%>
<%--                            Shipping--%>
<%--                            <span>Gratis</span>--%>
<%--                        </li>--%>
<%--                        <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">--%>
<%--                            <div>--%>
<%--                                <strong>The total amount of</strong>--%>
<%--                                <strong>--%>
<%--                                    <p class="mb-0">(including VAT)</p>--%>
<%--                                </strong>--%>
<%--                            </div>--%>
<%--                            <span><strong>$53.98</strong></span>--%>
<%--                        </li>--%>
<%--                    </ul>--%>

<%--                    <button type="button" class="btn btn-primary btn-block waves-effect waves-light">go to checkout--%>
<%--                    </button>--%>

<%--                </div>--%>
<%--            </div>--%>
<%--            <!-- Card -->--%>

<%--            <!-- Card -->--%>
<%--            <div class="card mb-3">--%>
<%--                <div class="card-body">--%>

<%--                    <a class="dark-grey-text d-flex justify-content-between" data-toggle="collapse"--%>
<%--                       href="#collapseExample1"--%>
<%--                       aria-expanded="false" aria-controls="collapseExample1">--%>
<%--                        Add a discount code (optional)--%>
<%--                        <span><i class="fas fa-chevron-down pt-1"></i></span>--%>
<%--                    </a>--%>

<%--                    <div class="collapse" id="collapseExample1">--%>
<%--                        <div class="mt-3">--%>
<%--                            <div class="md-form md-outline mb-0">--%>
<%--                                <input type="text" id="discount-code1" class="form-control font-weight-light"--%>
<%--                                       placeholder="Enter discount code">--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <!-- Card -->--%>

<%--        </div>--%>
<%--        <!--Grid column-->--%>

<%--    </div>--%>
<%--    <!--Grid row-->--%>

<%--</section>--%>
<!--Section: Block Content-->

<div class="tab-content py-4">

    <c:forEach items="${addressDtoList}" var="address">

        <div class="row  mb-3">
            <div class="col-md-2 themed-grid-col">${address.country}</div>
            <div class="col-md-2 themed-grid-col">${address.city}</div>
            <div class="col-md-1 themed-grid-col">${address.postcode}</div>
            <div class="col-md-2 themed-grid-col">${address.street}</div>
            <div class="col-md-1 themed-grid-col">${address.house}</div>
            <div class="col-md-1 themed-grid-col">${address.apartment}</div>
            <div class="col-md-1 themed-grid-col">
                <form name='get' action="/account/addresses/edit/${address.id}" method='Get'>
                    <input name="submit" type="submit" value="Edit"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </c:forEach>
    <a class="btn btn-outline-primary" href="http://localhost:8080/account/addresses/create">Add</a>
</div>

<%--<table>--%>
<%--    <tr>--%>
<%--        <td>--%>
<%--            <form name='get' action="/cart" method='Get'>--%>
<%--                <input name="submit" type="submit" value="CART"/>--%>
<%--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--            </form>--%>
<%--        </td>--%>
<%--        <td><strong>${cart.quantity}</strong></td>--%>
<%--    </tr>--%>
<%--</table>--%>

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
<%--    <c:forEach items="${products}" var="product">--%>
<%--        <tr>--%>
<%--            <td>--%>
<%--                <form name='addToCart' action="/customer/products/${product.id}" method='Get'>--%>
<%--                    <input name="submit" type="submit" value="${product.name}"/>--%>
<%--                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--                </form>--%>
<%--            </td>--%>
<%--            <td>${product.price}</td>--%>
<%--            <td>${product.category.name}</td>--%>
<%--            <td>--%>
<%--                <form name='addToCart' action="/cart/add/${product.id}" method='Post'>--%>
<%--                    <input name="submit" type="submit" value="add to cart"/>--%>
<%--                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--                </form>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

</body>
</html>

