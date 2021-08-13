<%-- 
    Document   : welcome
    Created on : Mar 20, 2021, 11:25:16 AM
    Author     : arceu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.ACCOUNT}">
            <font color="red">
            Welcome, ${sessionScope.ACCOUNT.fullname}
            </font><br/>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
            <c:if test="${not empty requestScope.SUCCESS}">
            <font color="red">
            Welcome, ${requestScope.SUCCESS}
            </font><br/>
            </c:if>
            <c:if test="${sessionScope.ACCOUNT.role}">
                <a href="search.jsp">Search Account</a><br/>
                <a href="addProduct.html">Add book</a><br/>
            </c:if>
            <a href="onlineBookStore.jsp">Buy Book Here</a><br/>
            <a href="cart?btAction=View your cart">View your cart</a><br/>
            <a href="history">Your history purchase</a>
        </c:if>
    </body>
</html>
