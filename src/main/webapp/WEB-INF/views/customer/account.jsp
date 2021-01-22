<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.01.2021
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


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
    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
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
                    <a href="http://localhost:8080/customer/orders" data-target="#messages" data-toggle="tab" class="nav-link">Orders</a>
                </li>
                <li class="nav-item">
                    <a href="" data-target="#edit" data-toggle="tab" class="nav-link">Edit</a>
                </li>
            </ul>
            <div class="tab-content py-4">
                <div class="tab-pane active" id="profile">
                    <h5 class="mb-3">User Profile</h5>
                    <div class="row">
                        <div class="col-md-6">
                            <h6>About</h6>
                            <p>
                                Web Designer, UI/UX Engineer
                            </p>
                            <h6>Hobbies</h6>
                            <p>
                                Indie music, skiing and hiking. I love the great outdoors.
                            </p>
                        </div>

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
                    </div>
                    <!--/row-->
                </div>

            </div>
        </div>
<%--        <div class="col-lg-4 order-lg-1 text-center">--%>
<%--            <img src="//placehold.it/150" class="mx-auto img-fluid img-circle d-block" alt="avatar">--%>
<%--            <h6 class="mt-2">Upload a different photo</h6>--%>
<%--            <label class="custom-file">--%>
<%--                <input type="file" id="file" class="custom-file-input">--%>
<%--                <span class="custom-file-control">Choose file</span>--%>
<%--            </label>--%>
<%--        </div>--%>
    </div>
</div>

<div class="container py-3">
    <div class="row">
        <div class="mx-auto col-sm-6">
            <!-- form user info -->
            <div class="card">
                <div class="card-header">
                    <h4 class="mb-0">User Information</h4>
                </div>
                <div class="card-body">
                    <form class="form" role="form" autocomplete="off">
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">First name</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" value="Jane">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Last name</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" value="Bishop">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Email</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="email" value="email@gmail.com">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Time Zone</label>
                            <div class="col-lg-9">
                                <select id="user_time_zone" class="form-control" size="0">
                                    <option value="Hawaii">(GMT-10:00) Hawaii</option>
                                    <option value="Alaska">(GMT-09:00) Alaska</option>
                                    <option value="Pacific Time (US &amp; Canada)">(GMT-08:00) Pacific Time (US &amp; Canada)</option>
                                    <option value="Arizona">(GMT-07:00) Arizona</option>
                                    <option value="Mountain Time (US &amp; Canada)">(GMT-07:00) Mountain Time (US &amp; Canada)</option>
                                    <option value="Central Time (US &amp; Canada)" selected="selected">(GMT-06:00) Central Time (US &amp; Canada)</option>
                                    <option value="Eastern Time (US &amp; Canada)">(GMT-05:00) Eastern Time (US &amp; Canada)</option>
                                    <option value="Indiana (East)">(GMT-05:00) Indiana (East)</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Password</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="password" value="11111122333">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Confirm</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="password" value="11111122333">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label"></label>
                            <div class="col-lg-9">
                                <input type="reset" class="btn btn-secondary" value="Cancel">
                                <input type="button" class="btn btn-primary" value="Save Changes">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- /form user info -->
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

</body>
</html>
