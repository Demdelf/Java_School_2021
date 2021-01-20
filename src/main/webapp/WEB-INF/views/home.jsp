<html>
<head><title>Spring 5 Web MVC Example</title></head>
<body>
<h1>Drytooling holds shop</h1>
<h2>Holds for World Cup and for garage</h2>
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

<form name='catalog' action="/account" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Account" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

</html>
