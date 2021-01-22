<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.01.2021
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Orders history</title>


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


<body>
<div class="order_wrapper expandable_control_header block_data__gtm-js block_data__pageevents-js active" id="order_detail_V3523538"
     data-gtm-location="Список заказов" data-pageevents-location="Список заказов" data-is-order-configuration="">

    <c:forEach items="${orderDtoList}" var="order">
    <table class="order order_header"><tbody>
    <tr>
        <td class="order_details">
        ${order.id}
        </td>
        <td class="order_brif_info">
            <span class="pseudo expandable_control for_order_V3523538">кол-во товаров: ${order.getGoodsCount()}</span>
        </td>
        <td class="order_status-cell">
            <div class="order_status step_5">
                <div class="status_bar"></div>
                <span class="status_name">${order.deliveryStatus}</span>
            </div>
        </td>
        <td class="delivery">${order.deliveryMethod}</td>
        <td class="price">
            <span class="price">
                <ins class="num">${order.getGoodsFullCost()}</ins>
                <ins class="rub">USD</ins>
            </span>
        </td>
        <td>
            <form name='get' action="/customer/orders/${order.id}" method='Get'>
                <input name="submit" type="submit" value="View" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </td>
    </tr>
    </tbody>
    </table>

    </c:forEach>
    <div class="expandable_content order_V3523538" style="">
        <table class="order order_content">
            <tbody>
            <tr>
                <td class="order_details"></td>
                <td class="product_name" colspan="3"></td>
            </tr>
            </tbody>
        </table>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

</div>
</body>
</html>
