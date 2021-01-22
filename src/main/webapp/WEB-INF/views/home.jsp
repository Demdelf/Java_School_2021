<html>
<head><title>Spring 5 Web MVC Example</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


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
<h1>Drytooling holds shop</h1>
<h2>Holds for World Cup and for garage</h2>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

</body>
<form name='catalog' action="/customer" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Catalog" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<form name='catalog' action="/manage" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Manage" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<form name='catalog' action="/login" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Login" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<form name='catalog' action="/reg" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Registration" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<form name='catalog' action="/account" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Account" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

</html>
