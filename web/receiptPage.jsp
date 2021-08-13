<%-- 
    Document   : receiptPage
    Created on : Mar 7, 2021, 5:57:15 PM
    Author     : arceu
--%>

<%@page import="java.util.Map"%>
<%@page import="longpnh.book.BookDTO"%>
<%@page import="longpnh.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirming your purchase ! K - Store</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.ACCOUNT}">
            <font color="red">Welcome, ${sessionScope.ACCOUNT.fullname}</font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <c:if test="${not empty requestScope.CART}">
            <h1>Checkout Complete !!! See you again soon !!!</h1>
            <p>MR. ${requestScope.NAME}</p>
            <p>Deliver at ${requestScope.ADDRESS}</p>
            <h2>Your receipt include</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="sum" value="0" />
                    <c:forEach items="${requestScope.CART.items}" var="items" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${items.key.title}</td>
                            <td>${items.value}</td>
                            <td>${items.value*items.key.price}</td>
                            <c:set var="sum" value="${sum = sum + items.value*items.key.price}" />
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>Total</td>
                        <td>${sum}</td>
                    </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty requestScope.CART}">
            <font color="red">There is something wrong with your checkout ! Please try again !</font><br/>
        </c:if>
        <a href="onlineBookStore.jsp">Continue viewing other products</a>
    </body>
</html>