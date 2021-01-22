<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<html>--%>
<%--<body onload='document.loginForm.username.focus();'>--%>
<%--    <h1>Spring Security 5 - Login Form</h1>--%>
<%-- --%>
<%--    <c:if test="${not empty errorMessge}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>--%>
<%-- --%>
<%--    <form name='login' action="/login" method='POST'>--%>
<%--        <table>--%>
<%--            <tr>--%>
<%--                <td>UserName:</td>--%>
<%--                <td><input type='text' name='username' value=''></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Password:</td>--%>
<%--                <td><input type='password' name='password' /></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td colspan='2'><input name="submit" type="submit" value="submit" /></td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--    </form>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <title>Log in</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <!-- Custom styles for this template -->
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
        body {
            height: 100%;
        }

        body {
            /*display: flex;*/
            align-items: center;
            /*padding-top: 40px;*/
            /*padding-bottom: 40px;*/
            /*background-color: #f5f5f5;*/
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
        .form-signin .checkbox {
            font-weight: 400;
        }
        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
        .container {
            max-width: 960px;
        }

        .pricing-header {
            max-width: 700px;
        }
    </style>

    <!-- Bootstrap core CSS -->

</head>

<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="h5 my-0 me-md-auto fw-normal text-dark" href="http://localhost:8080/">Russianholds</a>
    <nav class="my-2 my-md-0 me-md-3">
        <%--        <a class="p-2 text-dark" href="#">Features</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Enterprise</a>--%>
        <%--        <a class="p-2 text-dark" href="#">Support</a>--%>
        <%--        <td>--%>
        <%--            <form name='get' action="/cart" method='Get'>--%>
        <%--                <input name="submit" type="submit" value="CART" />--%>
        <%--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
        <%--            </form>--%>
        <%--        </td>--%>
        <%--        <td><strong>${cart.quantity}</strong></td>--%>
        <a class="p-2 text-dark" href="http://localhost:8080/cart">Cart ${cart.quantity}</a>
        <a class="p-2 text-dark" href="http://localhost:8080/customer">Catalog</a>
    </nav>
    <a class="btn btn-outline-primary" href="http://localhost:8080/account">Account</a>
</header>

<body class="text-center">

<main class="form-signin">
    <form name='login' action="/login" method='POST'>

        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
        <label for="username" class="visually-hidden">Email address</label>
        <input type="email" id="username" name='username' class="form-control" placeholder="Email address" required autofocus>
        <label for="password" class="visually-hidden">Password</label>
        <input type="password" id="password" name='password' class="form-control" placeholder="Password" required>
        <%--        <div class="checkbox mb-3">--%>
        <%--            <label>--%>
        <%--                <input type="checkbox" value="remember-me"> Remember me--%>
        <%--            </label>--%>
        <%--        </div>--%>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Log in</button>

    </form>
    <div class="etc-login-form">
        <p>new user? <a href="/reg">create new account</a></p>
    </div>
</main>



</body>
</html>
