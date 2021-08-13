<%-- 
    Document   : onlineBookStore
    Created on : Mar 6, 2021, 12:45:53 AM
    Author     : arceu
--%>

<%@page import="java.util.List"%>
<%@page import="longpnh.book.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>K -  Store</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.ACCOUNT}">
            <font color="red"> Welcome, ${sessionScope.ACCOUNT.fullname}</font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <h1>Search book</h1>
        <form action="cart">
            <input name="txtSearchValue" value="${param.txtSearchValue}"/>
            <input type="submit" value="Search book" name="btAction" />
        </form>
        <c:set var="searchResult" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchResult}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>  
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Price</th>
                            <th>Add to cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="book" varStatus="counter">
                        <form action="cart">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${book.title}
                                    <input type="hidden" name="cbo" value="${book.ID}" />
                                </td>
                                <td>
                                    ${book.price}
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchResult}"/>
                                    <input type="submit" value="Add book to your cart" name="btAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <form action="cart">
                <input type="submit" value="View your cart" name="btAction" />   
            </form>
        </c:if>
        <c:if test="${empty result}">
            <h2>No record is matched!!!</h2>  
        </c:if>
    </c:if>
</body>
</html>