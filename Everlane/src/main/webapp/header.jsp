<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 5/17/2023
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

<%--    icon--%>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

    <%--    font--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@300&display=swap" rel="stylesheet">

    <%--    css--%>
    <link rel="stylesheet" href="header/header.css"/>

</head>
<body>
    <header>
        <%--        web--%>
        <a class="bx bx-menu" id="menu-icon"></a>
        <ul class="navbar">
            <li><a href="#">Women</a></li>
            <li><a href="#">About</a></li>
            <li><a href="storyList.jsp">Stories</a></li>
        </ul>
        <a href="${pageContext.request.contextPath}/category-servlet" class="logo"><img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/Everlane_logo.png"></a>

        <div class="main">
            <a href="#" class="search"><i class="ri-search-line"></i></a>
            <a href="login.jsp" class="user"><i class="ri-user-3-line"></i></a>
            <a href="#" class="cart"><i class="ri-shopping-cart-2-line"></i></a>
        </div>

    <%--        mobile--%>
        <div class="menu_container">
            <div class="menu_container1">
                <a href="#">Best-Sellers</a>
                <a href="#">Everworld Stories</a>
            </div>
            <div class="menu_container2">
                <a href="">What's New</a>
                <a href="">Apparel</a>
                <a href="">Denim</a>
                <a href="">Shoes,Bags & Accessories</a>
                <a href="">Sales</a>
                <a href="">About</a>
            </div>
            <div class="menu_container3">
                <a href="">Account</a>
                <a href="">Log Out</a>
            </div>
        </div>
    </header>
    <div class="navbar2_container">
        <ul class="navbar2">
            <li><a href="">What's New</a></li>
            <li><a href="">Best Sellers</a></li>
            <li><a href="">Apparel</a></li>
            <li><a href="">Denim</a></li>
            <li><a href="">Shoes, Bags & Accessories</a></li>
            <li><a href="" style="color: red">Sale</a></li>
        </ul>
    </div>
    <script>
        x = document.querySelector("#menu-icon");
        y = document.querySelector(".menu_container");
        x.onclick = function (){
            x.classList.toggle("bx-x");
            y.classList.toggle("open");
        }
    </script>

</body>
</html>
