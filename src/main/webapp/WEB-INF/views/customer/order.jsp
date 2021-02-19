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
<h1>Order</h1>
<div class="container">
    <div class="tab-content py-4">
        <c:forEach items="${orderDto.orderProducts}" var="product">

            <div class="row  mb-3">
                <div class="col-md-2 themed-grid-col">
                    <form name='addToCart' action="/customer/products/${product.key.id}" method='Get'>
                        <input name="submit" type="submit" value="${product.key.name}"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
                <div class="col-md-2 themed-grid-col">
                    <img class="card-img-top" src="data:image/png;base64,${product.key.stringImage}"
                         alt="">
                </div>
                <div class="col-md-2 themed-grid-col">${product.key.price}</div>
                <div class="col-md-1 themed-grid-col">${product.key.category}</div>
                <div class="col-md-2 themed-grid-col">${product.value}</div>

            </div>
        </c:forEach>
        <div class="row  mb-3">
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><strong>Total</strong></td>
                <td class="text-right"><strong>$ ${orderDto.fullCost}</strong></td>
            </tr>
        </div>
    </div>

    <div class="tab-content py-4">
        <div class="tab-pane active" id="profile">
            <div class="container py-3">
                <div class="row">
                    <div class="mx-auto col-sm-6">

                        <form:form class="form" role="form" autocomplete="off" name="edit"
                                   action="/customer/orders/create" method="POST"
                                   modelAttribute="orderDto">

                            <div class="form-group row">
                                <label class="col-lg-3 col-form-label form-control-label">Customer address</label>
                                <div class="col-lg-9">
                                    <select id="customer_address" class="form-control" size="0" name="addressId">
                                        <c:forEach items="${addresses}" var="address">
                                            <option value="${address.id}">${address.toString()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <a class="btn btn-outline-primary"
                               href="http://localhost:8080/account/addresses/create">Add</a>
                            <div class="form-group row">
                                <label class="col-lg-3 col-form-label form-control-label">Payment method</label>
                                <div class="col-lg-9">
                                    <select id="payment_method" class="form-control" size="0" name="paymentMethod">
                                        <c:forEach items="${paymentMethods}" var="pm">
                                            <option value="${pm.name}">${pm.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-lg-3 col-form-label form-control-label">Delivery method</label>
                                <div class="col-lg-9">
                                    <select id="delivery_method" class="form-control" size="0" name="deliveryMethod">
                                        <c:forEach items="${deliveryMethods}" var="dm">
                                            <option value="${dm.name}">${dm.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-lg-3 col-form-label form-control-label"></label>
                                <div class="col-lg-9">
                                    <input type="reset" class="btn btn-secondary" value="Cancel">
                                        <%--                                                    <input type="button" class="btn btn-primary" value="Save Changes">--%>
                                    <button class="w-100 btn btn-lg btn-primary" type="submit">Save order
                                    </button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
