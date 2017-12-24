<%-- 
    Document   : checkout
    Created on : 29-Nov-2017, 21:22:55
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/main.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Checkout Page</h1>
        <form action="CustomerServlet" method="post">
            <table cellspacing="8" border="0">
                <tr>
                    <td><label>Login Name</label></td>
                    <td><input type="text" name="loginname" value="${customer.loginname}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>Login Password</label></td>
                    <td><input type="password" name="password" value="${customer.password}" required></td>
                    <td><p class="required">*</p></td><br>    
                </tr>
                <tr>
                    <td><label>&nbsp;</label></td>
                    <td><input type="submit" value="login" name="submit"></td>
                </tr>
            </table>
        </form>
        <p style="color: red">${message}</p>           
        <p>Enter your name and contact information</p>

        <form action="CustomerServlet" method=post>
            <table cellspacing="8" border="0">

                <tr>
                    <td><label>First Name</label></td>
                    <td><input type="text" name="firstName"  maxlength=20 
                               value="${customer.firstName}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>Last Name</label></td>
                    <td><input type="text" name="lastName" value="${customer.lastName}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>Address Line 1</label></td>
                    <td><input type="text" name="address1" value="${customer.address1}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>Address Line 2</label></td>
                    <td><input type="text" name="address2" value="${customer.address2}"></td><br>
                </tr>
                <tr>
                    <td><label>City</label></td>
                    <td><input type="text" name="city" value="${customer.city}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>State</label></td>
                    <td><input type="text" name="state" value="${customer.state}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>Credit Card Type</label></td>
                    <td><input type="text" name="creditcardtype" value="${customer.creditcardtype}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>Credit Card Number</label></td>
                    <td><input type="text" name="creditcardnumber" value="${customer.creditcardnumber}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>Credit Card Expiry</label></td>
                    <td><input type="text" name="ccexpirydate" value="${customer.ccexpirydate}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>Email Address</label></td>
                    <td><input type="email" name="email" value="${customer.email}" required></td>
                    <td><p class="required">*</p></td><br> 
                </tr>
                <tr>
                    <td><label>Mobile</label></td>
                    <td><input type="text" name="mobile" value="${customer.mobile}" required></td>
                    <td><p class="required">*</p></td><br>    
                </tr>
                <tr>
                    <td><label>Login Name</label></td>
                    <td><input type="text" name="loginname" value="${customer.loginname}" required></td>
                    <td><p class="required">*</p></td><br>
                </tr>
                <tr>
                    <td><label>Login Password</label></td>
                    <td><input type="password" name="password" value="${customer.password}" required></td>
                    <td><p class="required">*</p></td><br>    
                </tr>
                <tr>
                    <td><label>&nbsp;</label></td>
                    <td><input type="submit" value="continue" name="submit"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
