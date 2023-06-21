<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 5/30/2023
  Time: 8:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="header/header.css">
    <link rel="stylesheet" href="account/authentication/authentication.css">
    <link rel="stylesheet" href="footer/ad-container.css">
    <link rel="stylesheet" href="footer/footer.css">
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="a.template/assets/img/favicon/favicon.png" />
</head>
<body>
<%--Header--%>
<jsp:include page="header.jsp"/>

<%--Body--%>
<%--Authentication Container--%>
<section class="Authentication_Container">
    <div class="Authentication_Inner_Container">
        <div class="Login_Container">
            <h1>Create a password</h1>
            <h2>Complete your sign up to
                receive your discount.*</h2>
            <form action="register-servlet" method="post">
                <input type="text" id="email" name="username" value="" placeholder="User Name"><br>
                <input type="text" id="email" name="firstname" value="" placeholder="First Name"><br>
                <input type="text" id="email" name="lastname" value="" placeholder="Last Name"><br>
                <input type="date" id="email" name="dob" value="" placeholder="Date of birth"><br>
                <select name="gender">
                    <option value="" selected>Chose your gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select><br>
                <input type="email" id="email" name="email" value="" placeholder="Email Address"><br>
                <input type="password" id="password" name="password" value="" placeholder="Password"><br><br>
                <input type="password" id="repass" name="repass" value="" placeholder="Repeat Password"><br>${error}<br>
                <input type="submit" value="Unlock Your Discount">
            </form>
            <a href="login.jsp">Already have an account? Sign in</a>
        </div>
    </div>
</section>

<%--footer--%>
<jsp:include page="footer.jsp"/>
</body>
</html>
