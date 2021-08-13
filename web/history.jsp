<%-- 
    Document   : history
    Created on : Mar 21, 2021, 9:07:32 PM
    Author     : arceu
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.ACCOUNT}" >
            <font color="red">Welcome, ${sessionScope.ACCOUNT.fullname}</font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <h1>Your Purchase History</h1>
        <c:set var="historyList" value="${requestScope.HISTORYLIST}"/>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>orderID</th>
                    <th>custName</th>
                    <th>customerAddress</th>
                    <th>createDate</th>
                    <th>title</th>
                    <th>quantity</th>
                    <th>price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${historyList}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${dto.orderID}</td>
                        <td>${dto.custName}</td>
                        <td>${dto.customerAddress}</td>
                        <td>${dto.createDate}</td>
                        <td>${dto.title}</td>
                        <td>${dto.quantity}</td>
                        <td>${dto.price}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="welcome.jsp">Back To Welcome Page</a>
        <a href="onlineBookStore.jsp">Buy Book Here</a><br/>
    </body>
</html>
