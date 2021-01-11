<html>
<head><title>Spring 5 Web MVC Example</title></head>
<body>
<h1>Drytooling holds shop</h1>
<h2>Holds for World Cup and for garage</h2>
</body>
<form name='all' action="/property/values/all" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="values" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<form name='all' action="/products/all" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="products" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<form name='all' action="/categories/all" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="categories" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

</html>
