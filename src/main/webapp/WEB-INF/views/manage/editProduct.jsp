<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.01.2021
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Product</title>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <a class="p-2 text-dark" href="/customer">Catalog</a>
        <a class="btn btn-outline-primary" href="http://localhost:8080/manage">Manage</a>
    </nav>
    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>
<body>
<h1>Edit product</h1>

<div class="tab-content py-4">
    <div class="tab-pane active" id="profile">
        <div class="container py-3">
            <div class="row">
                <a href="/customer/products/${productDto.id}">
                    <img class="img-fluid rounded mx-auto d-block" alt="Responsive image" src="/products-images/${productDto.id}">
                </a>
                <div class="mx-auto col-sm-6">

                    <form:form class="form" role="form" autocomplete="off" name="edit"
                               method="post" modelAttribute="productDto"
                               action="/manage/products/edit/${productDto.id}"
                               enctype="multipart/form-data">
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Name</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" name="name"
                                       value="${productDto.name}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Price</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" name="price"
                                       value="${productDto.price}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="category" class="col-lg-3 col-form-label form-control-label">Category</label>
                            <select class="form-select col-lg-9" name="category" id="category" required>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.name}" ${productDto.category == category.name ? 'selected' : ''}>
                                            ${category.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="row g-2">
                            <div class="col-sm-6">
                                <label class="col-lg-3 col-form-label form-control-label">Property</label>
                                <c:forEach var="property" items="${productDto.properties}">
                                    <div class="col-lg-9">
                                        <label class="col-lg-3 col-form-label form-control-label">${property.value}</label>
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="col-sm-6">
                                <label class="col-lg-3 col-form-label form-control-label">Value</label>
                                <c:forEach var="propertyValues" items="${productDto.propertyValues}">
                                    <div class="col-lg-9">
                                        <input class="form-control" type="text"
                                               name="propertyValues['${propertyValues.key}']"
                                               value="${propertyValues.value}"/>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Image: </label>
                            <div class="col-lg-9">
                                <input type="file" name="image" accept="image/png, image/jpeg, image/jpg" value="/products-images/${productDto.id}"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label"></label>
                            <div class="col-lg-9">
                                <input type="reset" class="btn btn-secondary" value="Cancel">
                                <button class="w-100 btn btn-lg btn-primary" type="submit">Save Changes
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>
