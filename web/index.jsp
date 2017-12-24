<%-- 
    Document   : index
    Created on : 29-Nov-2017, 20:18:13
    Author     : james
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Shopping Cart DB</title>
    </head>
    <body>
        <h1>Product List</h1>
        <table border="1">
            <tr>
                <th>Description</th>
                <th class="right">Price</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><c:out value='${product.description}' /></td>
                    <td class="right">${product.priceCurrencyFormat}</td>
                    <td><form action="cart" method="POST">
                            <input type="hidden" name="code" 
                                   value="${product.code}">
                            <input type="submit" 
                                   value="Add To Cart">
                        </form></td>        
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
