<%--<%@page import="longpnh.registration.RegistrationDTO"%>
<%@page import="java.util.List"%> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Search Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <c:if test="${not empty sessionScope.ACCOUNT}" >
            <font color="red">Welcome, ${sessionScope.ACCOUNT.fullname}</font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <h1>Search Page</h1>
        <form action="search">
            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${empty result}">
                <h2>No record found</h2>
            </c:if>
            <c:if test="${not empty result}" >
                Abasdasd
                <c:if test="${not empty requestScope.ERROR}">
                    <font color="red">
                    ${requestScope.ERROR}
                    </font>
                </c:if>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last name</th>
                            <th>Admin</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="updateAccount" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullname}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}"/>
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                                <c:url var="dellLink" value="deleteAccount" >
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="pk" value="${dto.username}"/>
                                    <c:param name="lastSearchValue" value="${searchValue}" />
                                </c:url>
                                <td>
                                    <a href="${dellLink}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <a href="onlineBookStore.jsp">Buy Book Here</a>
</body>
</html>