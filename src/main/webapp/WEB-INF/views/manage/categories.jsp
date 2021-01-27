<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.01.2021
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Categories</title>
    <!-- Bootstrap core CSS -->
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
</head>

<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <%--        <a class="p-2 text-dark" href="#">Features</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Enterprise</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Support</a>--%>
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
            <a class="btn btn-outline-primary" href="http://localhost:8080/manage">Manage</a>
    </nav>
    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>
<body>
<%--<h1>Add category</h1>--%>
<%--<form:form action="create" method="post" modelAttribute="categoryDto">--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td>Name</td>--%>
<%--            <td>--%>
<%--                <form:input path="name" /> <br />--%>
<%--                <form:errors path="name" cssClass="error" />--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr>--%>
<%--            <td></td>--%>
<%--            <td><input type="submit" value="Submit"></td>--%>
<%--            <td></td>--%>
<%--        </tr>--%>

<%--        <table>--%>
<%--            <tr>--%>
<%--                <td><strong>Name</strong></td>--%>
<%--            </tr>--%>
<%--            <c:forEach items="${categories}" var="category">--%>
<%--                <tr>--%>
<%--                    <td>${category.name}</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>
<%--    </table>--%>
<%--</form:form>--%>

<div class="container">
    <div class="row my-2">
        <div class="order-lg-2">
            <div class="tab-content py-4">

                <c:forEach items="${categories}" var="category">

                    <div class="row  mb-3">
                        <div class="col-md-1 themed-grid-col">${category.id}</div>
                        <div class="col-md-2 themed-grid-col">${category.name}</div>

                        <div class="col-md-2 themed-grid-col">
                            <form name='get' action="/manage/categories/edit/${category.id}" method='Get'>
                                <input name="submit" type="submit" value="Manage"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a class="btn btn-outline-primary" href="http://localhost:8080/manage/categories/add">Add category</a>
        </div>
    </div>
</div>


</body>
</html>



