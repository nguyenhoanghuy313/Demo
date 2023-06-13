<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 6/1/2023
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="header/header.css">
    <link rel="stylesheet" href="account/authentication/authentication.css">
    <link rel="stylesheet" href="footer/ad-container.css">
    <link rel="stylesheet" href="footer/footer.css">

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="a.template/assets/img/favicon/favicon.png" />
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="Authentication_Container">
    <div class="Authentication_Inner_Container">
        <div class="Login_Container">
            <h1>Good to see you</h1>
            <h2>Please log in to your account</h2>
            <form action="#">
                <input type="email" id="email" name="email" value="" placeholder="Email Address"><br>
                <input type="password" id="password" name="password" value="" placeholder="Password"><br><br>
                <input type="submit" value="Login">
            </form>
            <a href="register.jsp">Don’t have an account? Sign up</a>
            <a href="adminpage/dashboard.jsp">Reset password</a>

        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
