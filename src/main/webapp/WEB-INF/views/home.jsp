<html>
<head><title>Spring 5 Web MVC Example</title></head>
<body>
<h1>HowToDoInJava.com</h1>
<h2>Spring 5 Web MVC DispatcherServlet Example</h2>
</body>
<form name='all' action="/property/values/all" method='Get'>
    <table>

        <tr>
            <td colspan='2'><input name="submit" type="submit" value="values" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</html>
