<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.01.2021
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <title>Russianholds</title>

    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">--%>


    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <style>
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

        /*.container {*/
        /*    max-width: 960px;*/
        /*}*/
    </style>

</head>


<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <%--        <a class="p-2 text-dark" href="#">Features</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Enterprise</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Support</a>--%>
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
    </nav>
    <a class="btn btn-danger" href="http://localhost:8080/logout">Logout</a>
</header>

<body class="bg-light">

<div class="container">
    <div class="row my-2">
        <div class="col-lg-8 order-lg-2">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a href="http://localhost:8080/account" data-target="#profile" data-toggle="tab" class="nav-link">Profile</a>
                </li>
                <li class="nav-item">
                    <a href="http://localhost:8080/customer/orders" data-target="#messages" data-toggle="tab"
                       class="nav-link">Orders</a>
                </li>
                <li class="nav-item">
                    <a href="" data-target="#edit" data-toggle="tab" class="nav-link active">Create address</a>
                </li>
            </ul>
            <div class="tab-content py-4">
                <div class="tab-pane active" id="profile">
                    <div class="container py-3">
                        <div class="row">
                            <div class="mx-auto col-sm-6">

                                <form:form class="form" role="form" autocomplete="off" name="create"
                                           action="/account/addresses/create" method="POST"
                                           modelAttribute="addressDto">
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">Country</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="text" name="country"
                                                   value="${addressDto.country}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">City</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="text" name="city"
                                                   value="${addressDto.city}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">Postcode</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="text" name="postcode"
                                                   value="${addressDto.postcode}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">Street</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="text" name="street"
                                                   value="${addressDto.street}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">House</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="text" name="house"
                                                   value="${addressDto.house}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">Apartment</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="text" name="apartment"
                                                   value="${addressDto.apartment}">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label"></label>
                                        <div class="col-lg-9">
                                            <input type="reset" class="btn btn-secondary" value="Cancel">
                                                <%--                                                    <input type="button" class="btn btn-primary" value="Save Changes">--%>
                                            <button class="w-100 btn btn-lg btn-primary" type="submit">Save address
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
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

</body>
</html>


