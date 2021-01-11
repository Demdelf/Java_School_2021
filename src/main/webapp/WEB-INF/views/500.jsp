<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2021
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<html>
<head><title>404</title></head>
<body>
<h1>Ups!</h1>
<h2>We can't found what you are looking for.</h2>

<form name='home' action="/" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Home" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>
