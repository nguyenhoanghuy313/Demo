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
    <title>Forgot Password</title>
    <link rel="stylesheet" href="header/header1.css">
    <link rel="stylesheet" href="account/authentication/authentication1.css">
    <link rel="stylesheet" href="footer/ad-container.css">
    <link rel="stylesheet" href="footer/footer.css">

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="Authentication_Container">
    <div class="Authentication_Inner_Container">
        <div class="Login_Container">
            <h1>Forgot your password ???</h1>
            <h2>Please enter your email</h2>
            <form action="ForgotPassServlet?mod=1" method="post">
                <input type="email" id="email" name="email" placeholder="Email Address">
                <input type="hidden" name="subject"  value="Your new Everlane password">
                <input type="hidden" name="content" value="This is your new password">
                <input type="submit" value="Send">
                <h2 style="color: red">
                    ${Message}
                </h2>
                <h2 style="color: green;">
                    ${success}
                </h2>
            </form>
            <a href="login.jsp">Go back to Login</a>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
