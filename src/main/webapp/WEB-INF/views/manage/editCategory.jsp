<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.01.2021
  Time: 20:52
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
    <title>Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
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
        .dropdown-input label {

            display: block;

            padding: 3px 20px 0 20px;

            clear: both;

            font-weight: 400;

            line-height: 1.42857143;

            color: #333;

            white-space: nowrap;

        }
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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
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

<body>
<h1>Edit category</h1>
<%--<form:form method="post" modelAttribute="categoryDto" action="/manage/categories/edit/${categoryDto.id}">--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td><strong>Name</strong></td>--%>
<%--            <td><input type="text" name="name" value="${categoryDto.name}"/></td>--%>
<%--        </tr>--%>
<%--    </table>--%>

<%--    <tr>--%>
<%--        <td><input type="submit" value="Update"></td>--%>
<%--    </tr>--%>
<%--</form:form>--%>
<div class="tab-content py-4">
    <div class="tab-pane active" id="profile">
        <div class="container py-3">
            <div class="row">
                <div class="mx-auto col-sm-6">

                    <form:form class="form" role="form" autocomplete="off" name="edit"
                               action="/manage/categories/edit/${categoryDto.id}" method="POST"
                               modelAttribute="addressDto">
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Name</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" name="name"
                                       value="${categoryDto.name}">
                            </div>
                        </div>

<%--                        <c:forEach items="${properties}" var="property">--%>
<%--                            <li class="dropdown-input"><label><input type="checkbox"> ${property.name}</label></li>--%>
<%--                        </c:forEach>--%>

                        <%--                        <div class="row">--%>
                        <%--                            <div class="col-sm-4 col-xs-12">--%>
                        <%--                                <p class="font-weight-bold">Properties</p>--%>
                        <%--                                <div data-toggle="buttons">--%>

                        <%--                                    <c:forEach items="${properties}" var="property">--%>
                        <%--                                        <label class="btn btn-block btn-success active">--%>
                        <%--                                            <input type="checkbox" name="property" checked autocomplete="off"> ${property.name}--%>
                        <%--                                        </label>--%>

                        <%--                                    </c:forEach>--%>

                        <%--                                </div>--%>
                        <%--                            </div>--%>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label"></label>
                            <div class="col-lg-9">
                                <input type="reset" class="btn btn-secondary" value="Cancel">
                                    <%--                                                    <input type="button" class="btn btn-primary" value="Save Changes">--%>
                                <button class="w-100 btn btn-lg btn-primary" type="submit">Save Changes
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>






<div class="tab-content py-4">
    <div class="tab-pane active" id="profile2">
        <div class="container py-3">
            <div class="row">
                <div class="mx-auto col-sm-6">

                    <form:form class="form" role="form" autocomplete="off" name="edit"
                               action="/manage/categories/edit/${categoryDto.id}" method="POST"
                               modelAttribute="categoryDto">
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Name</label>
                            <div class="col-lg-9">
                                <input class="form-control" type="text" name="name"
                                       value="${categoryDto.name}">
                            </div>
                        </div>
<%--                        <div class="dropdown">--%>

<%--                            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">--%>

<%--                                Properties <span class="caret"></span>--%>

<%--                            </button>--%>

<%--                            <ul class="dropdown-menu">--%>

<%--                                <c:forEach items="${properties}" var="property">--%>
<%--                                    <li class="dropdown-input"><label><input type="checkbox"> ${property.name}</label></li>--%>
<%--                                </c:forEach>--%>

<%--                            </ul>--%>

<%--                        </div>--%>

                        <c:forEach items="${properties}" var="property">
                            <li class="dropdown-input"><label><input type="checkbox"> ${property.name}</label></li>
                        </c:forEach>

<%--                        <div class="row">--%>
<%--                            <div class="col-sm-4 col-xs-12">--%>
<%--                                <p class="font-weight-bold">Properties</p>--%>
<%--                                <div data-toggle="buttons">--%>

<%--                                    <c:forEach items="${properties}" var="property">--%>
<%--                                        <label class="btn btn-block btn-success active">--%>
<%--                                            <input type="checkbox" name="property" checked autocomplete="off"> ${property.name}--%>
<%--                                        </label>--%>

<%--                                    </c:forEach>--%>

<%--                                </div>--%>
<%--                            </div>--%>

<%--                            <div class="col-sm-12 text-right">--%>
<%--                                <button class="btn btn-lg" type="submit">Submit</button>--%>
<%--                                <div id="formValues"></div>--%>
<%--                            </div>--%>



                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label"></label>
                            <div class="col-lg-9">
                                <input type="reset" class="btn btn-secondary" value="Cancel">
                                    <%--                                                    <input type="button" class="btn btn-primary" value="Save Changes">--%>
                                <button class="w-100 btn btn-lg btn-primary" type="submit">Update category
                                </button>
                            </div>
                        </div>

<%--                        <div class="row">--%>
<%--                            <div class="col-sm-4 col-xs-12">--%>
<%--                                <p class="font-weight-bold">Properties</p>--%>
<%--                                <div data-toggle="buttons">--%>

<%--                                    <c:forEach items="${properties}" var="property">--%>
<%--                                        <label class="btn btn-block btn-success active">--%>
<%--                                            <input type="checkbox" name="oil" checked autocomplete="off"> ${property.name}--%>
<%--                                        </label>--%>

<%--                                    </c:forEach>--%>

<%--                                </div>--%>
<%--                            </div>--%>

<%--                            <div class="col-sm-12 text-right">--%>
<%--                                <button class="btn btn-lg" type="submit">Submit</button>--%>
<%--                                <div id="formValues"></div>--%>
<%--                            </div>--%>

<%--                        </div>--%>
                    </form:form>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<form class="container">--%>
<%--    <div class="row">--%>
<%--        <div class="col-sm-4 col-xs-12">--%>
<%--            <p class="font-weight-bold">Properties</p>--%>
<%--            <div data-toggle="buttons">--%>

<%--                <c:forEach items="${properties}" var="property">--%>
<%--                    <label class="btn btn-block btn-success active">--%>
<%--                        <input type="checkbox" name="oil" checked autocomplete="off"> ${property.name}--%>
<%--                    </label>--%>

<%--                </c:forEach>--%>
<%--                --%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="col-sm-12 text-right">--%>
<%--            <button class="btn btn-lg" type="submit">Submit</button>--%>
<%--            <div id="formValues"></div>--%>
<%--        </div>--%>

<%--    </div>--%>
<%--</form>--%>




</body>
</html>

