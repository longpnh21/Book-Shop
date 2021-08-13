<%-- 
    Document   : addProduct
    Created on : Mar 21, 2021, 11:31:11 PM
    Author     : arceu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create A Book</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="createBook" method="POST"><br/>
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Title <input type="text" name="Title" value=""/>
            <c:if test="${not empty errors.emptyTitle}">
                <font color="red">${errors.emptyTitle}</font><br/>
            </c:if><br/>
            Author <input type="text" name="Author" value=""/><br/>
            PublishYear <input type="text" name="PublishYear" value=""/><br/>
            Description <input type="text" name="Description" value=""/><br/>
            Price <input type="text" name="Price" value=""/>
            <c:if test="${not empty errors.invalidPrice}">
                <font color="red">${errors.invalidPrice}</font><br/>
            </c:if><br/>
            Quantity <input type="text" name="Quantity" value=""/>
            <c:if test="${not empty errors.invalidQuantity}">
                <font color="red">${errors.invalidQuantity}</font><br/>
            </c:if><br/>
            <input type="submit" name="btAction" value="Create" /><br/>
        </form>
    </body>
</html>
