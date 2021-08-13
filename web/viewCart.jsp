<%-- 
    Document   : viewCart
    Created on : Mar 5, 2021, 10:00:56 AM
    Author     : arceu
--%>

<%@page import="longpnh.book.BookDTO"%>
<%@page import="java.util.Map"%>
<%@page import="longpnh.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>K - Store</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.ACCOUNT}">
            <font color="red">Welcome, ${sessionScope.ACCOUNT.fullname}</font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart && not empty cart.items}">
            <form action="removeBook">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cart.items}" var="items" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${items.key.title}
                                </td>
                                <td>
                                    ${items.value}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${items.key.ID}" />
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3" >
                                <a href="onlineBookStore.jsp">Add More Book To Your Cart</a>
                            </td>
                            <td>
                                <input type="submit" value="Remove Selected Books" name="btAction"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <form action="checkout">
                Name <input name="txtName" value="" type="text"/>
                <c:if test="${not empty requestScope.ERROR_NAME}">
                    <font color="red">
                    ${requestScope.ERROR_NAME}
                    </font>
                </c:if>
                <br/>
                Address <input type="text" name="txtAddress" value="" />
                <input type="submit" value="checkout" name="btAction" />
                <c:if test="${not empty requestScope.ERROR_ADDRESS}">
                    <font color="red">
                    ${requestScope.ERROR_ADDRESS}
                    </font>
                </c:if>
                <br/>
            </form>
        </c:if>
        <c:if test="${empty cart || empty cart.items}">
            Your cart has been lost
        </c:if>
        <a href="onlineBookStore.jsp">Buy Book Here</a>
    </body>
</html>
