<%-- 
    Document   : createNewAccount
    Created on : Mar 9, 2021, 12:33:53 PM
    Author     : arceu
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="createAccount" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 30 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">${errors.usernameLengthError}</font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6 - 30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">${errors.passwordLengthError}</font><br/>
            </c:if>
            Confirm*  <input type="password" name="txtConfirm" value=""/>(6 - 30 chars)<br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">${errors.confirmNotMatch}</font><br/>
            </c:if>
            Fullname* <input type="text" name="txtFullname" value="${param.txtFullname}" />(6 - 30 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">${errors.fullnameLengthError}</font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form><br/>
        <c:if test="${not empty errors.usernameIsExisted}">
            <font color="red">${errors.usernameIsExisted}</font><br/>
        </c:if>
    </body>
</html>
