<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.01.2021
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Spring 5 Web MVC Example</title></head>
<body>
<h1>Manage drytooling holds shop</h1>
</body>
<%--<form name='all' action="/property/values/all" method='Get'>--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td colspan='2'><input name="submit" type="submit" value="values" /></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--</form>--%>

<form name='all' action="/manage/products/all" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="products" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<form name='all' action="/manage/categories/all" method='Get'>
    <table>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="categories" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

</html>
