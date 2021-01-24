<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.01.2021
  Time: 18:40
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
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.79.0">
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
        .themed-grid-col {
            padding-top: .75rem;
            padding-bottom: .75rem;
            background-color: rgba(86, 61, 124, .15);
            border: 1px solid rgba(86, 61, 124, .2);
        }

        .themed-container {
            padding: .75rem;
            margin-bottom: 1.5rem;
            background-color: rgba(0, 123, 255, .15);
            border: 1px solid rgba(0, 123, 255, .2);
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="resources/css/form-validation.css" rel="stylesheet">
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
                    <a href="" data-target="#profile" data-toggle="tab" class="nav-link active">Profile</a>
                </li>
                <li class="nav-item">
                    <a href="http://localhost:8080/customer/orders" data-target="#messages" data-toggle="tab"
                       class="nav-link">Orders</a>
                </li>
                <li class="nav-item">
                    <a href="http://localhost:8080/account/edit" data-target="#edit" data-toggle="tab" class="nav-link">Edit</a>
                </li>
            </ul>
            <div class="tab-content py-4">
                <div class="tab-pane active" id="profile">
                    <h5 class="mb-3">User Profile</h5>
                    <div class="row">
<%--                        <div class="col-md-6">--%>
<%--                            <h6>About</h6>--%>
<%--                            <p>--%>
<%--                                Web Designer, UI/UX Engineer--%>
<%--                            </p>--%>
<%--                            <h6>Hobbies</h6>--%>
<%--                            <p>--%>
<%--                                Indie music, skiing and hiking. I love the great outdoors.--%>
<%--                            </p>--%>
<%--                        </div>--%>

                        <div class="col-md-12">
                            <h5 class="mt-2"><span class="fa fa-clock-o ion-clock float-right"></span> Info</h5>
                            <table class="table table-sm table-hover table-striped">
                                <tbody>
                                <tr>
                                    <td>
                                        <strong>Email</strong> ${accountDto.email}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>FirstName</strong> ${accountDto.firstName}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>LastName</strong> ${accountDto.lastName}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>BirthDay</strong> ${accountDto.birthday}
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                        <h5 class="mt-2"><span class="fa fa-clock-o ion-clock float-right"></span> Addresses</h5>
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



                    </div>
                    <!--/row-->
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
