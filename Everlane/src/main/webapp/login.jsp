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
            <form action="login-servlet" method="post">
                <input type="email" id="email" name="email" value="${newemail}" placeholder="Email Address">
                <br>${EmailErr}<br>
                <input type="password" id="password" name="password" value="${newpass}" placeholder="Password">
                <br>${PassErr}<br>
                <input type="submit" value="Login">
                <br>
                ${Message} ${success}
            </form>
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/LoginGoogle/LoginGoogleHandler&response_type=code
		   &client_id=377412618992-d6fdoanaf6nm1ivg4atd28c4aqffvnqo.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
            <a href="register.jsp">Don’t have an account? Sign up</a>
            <a href="dashboardManager.jsp">Forgot your password</a>
            <a href="/user-account-detail-servlet">user account</a>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
