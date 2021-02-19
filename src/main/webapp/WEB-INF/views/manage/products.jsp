<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.12.2020
  Time: 14:45
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
</head>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
        <a class="btn btn-outline-primary" href="http://localhost:8080/manage">Manage</a>
    </nav>
    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>
<body>
<div class="container py-3">
    <h2>Add product</h2>

    <div class="row">
        <div class="col-sm-6">
            <form:form action="create" method="post" modelAttribute="productDto">
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
                            <option value="${category.name}">
                                    ${category.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label"></label>
                    <div class="col-lg-9">
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Add
                        </button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

    <h2>All products</h2>


    <div class="tab-content py-4">
        <div class="row mb-3" id="prodDiv${product.id}">
            <div class="col-md-2 themed-grid-col">
                Name
            </div>
            <div class="col-md-2 themed-grid-col">
                Image
            </div>
            <div class="col-md-2 themed-grid-col">Price</div>
            <div class="col-md-1 themed-grid-col">Category</div>
            <div class="col-md-2 themed-grid-col">
                Edit
            </div>
        </div>
        <c:forEach items="${products}" var="product">

            <div class="row  mb-3" id="prodDiv${product.id}">
                <div class="col-md-2 themed-grid-col">
                    <form name='addToCart' action="/customer/products/${product.id}" method='Get'>
                        <input name="submit" type="submit" value="${product.name}"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
                <div class="col-md-2 themed-grid-col">
                    <a href="/customer/products/${product.id}"><img class="card-img-top"
                                                                    src="data:image/png;base64,${product.stringImage()}"
                                                                    alt=""></a>
                </div>
                <div class="col-md-2 themed-grid-col">${product.price}</div>
                <div class="col-md-1 themed-grid-col">${product.category.name}</div>
                <div class="col-md-2 themed-grid-col">
                    <form name='edit' action="/manage/products/edit/${product.id}" method='Get'>
                        <input name="submit" type="submit" value="Edit"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>

            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>


