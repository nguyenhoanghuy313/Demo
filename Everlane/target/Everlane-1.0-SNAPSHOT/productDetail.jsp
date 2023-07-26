<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 5/27/2023
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="controller.*" %>
<%@ page import="entity.Color" %>
<%@ page import="entity.Size" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%
        Product pi = (Product) request.getAttribute("pi");
        List<Color> colors = (List<Color>) request.getAttribute("colors");
        List<Size> sizes = (List<Size>) request.getAttribute("sizes");
    %>
    <%--    icon--%>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

    <link rel="stylesheet" href="header/header1.css">
    <link rel="stylesheet" href="productpage/productdetail/product-detail1.css">
    <link rel="stylesheet" href="homepage/best-seller-slider1.css">
    <link rel="stylesheet" href="footer/ad-container.css">
    <link rel="stylesheet" href="footer/footer.css">
    <%--    <title>Product Detail</title>--%>
    <title><%=pi.getProductName()%>
    </title>
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>
</head>
<body onpageshow="showCart()">
<jsp:include page="header.jsp"/>
<%--<form action="addToCart" method="post">--%>
<section class="Product_Detail_Container">
    <div class="Product_Image_Container">
        <img src="webImage/productImg/<%= pi.getThumbnail()%>">
        <img src="webImage/productImg/<%= pi.getProduct_img_1()%>">
        <img src="webImage/productImg/<%= pi.getProduct_img_2()%>">
        <img src="webImage/productImg/<%= pi.getProduct_img_3()%>">
    </div>
    <div class="Product_Detail">
        <div class="Product_Name">
            <div class="Name_And_Price">
                <h1><%= pi.getProductName()%>
                </h1>
                <div class="Price">
                    <%
                        if(pi.getDiscount() != 0){
                    %>
                    <p><%= pi.getPrice()%></p>
                    <p><%= pi.getDiscount()%>
                    </p>
                    <%
                        }else {
                    %>
                    <p style="text-decoration: none"><%= pi.getPrice()%>
                    </p>
                    <%}%>
                </div>
            </div>
        </div>
        <form action="addToCart" method="POST">
            <div class="Product_Color">
                <h1>Color</h1>
                <div class="Color">
                    <%
                        for (Color color : colors) {
                    %>
                    <a  style="color: black" href="${pageContext.request.contextPath}/productDetail-servlet?ProductID=<%= pi.getProductID()%>&color_Name=<%= color.getColor_Name()%>">
                        <div style="background-color: <%= color.getColor_Name()%>"></div>
                        <span style="color: black"><%= color.getColor_Name()%></span>
                    </a>
                    <% } %>
                </div>
            </div>

            <div class="Product_Size">
                <h1>Size</h1>
                <div class="Size">
                    <input type="hidden" name="ProductID" value="<%= pi.getProductID() %>">
                    <input type="hidden" name="color_Name" value="<%= pi.getColor_Name() %>">
                    <%--                    <input type="hidden" name="productName" value="<%= pi.getProductName() %>">--%>
                    <div class="radiocheck_container">
                        <% for (Size size : sizes) { %>
                        <div class="radiocheck" for="size_name">
                            <input type="radio" id="size_name" name="size_name" value="<%= size.getSize_Name() %>" required>
                            <p id="radio_name"><%= size.getSize_Name() %>
                            </p>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
            <h1 style="color:red; font-family: 'Nunito Sans', sans-serif; font-size: 14px">${CartMessError}</h1>
            <h1 style="color:green;font-family: 'Nunito Sans', sans-serif;  font-size: 14px">${ShippMess}</h1>
            <button color="white" type="submit" onclick="showCart()">Add To Cart
            </button>
        </form>


        <%--        href="${pageContext.request.contextPath}/addToCart?ProductID=<%=pi.getProductID()%>&VariationID=<%=pi.getVariationID()%>"--%>
        <div class="Product_Description">
            <h1>Description</h1>
            <p>Made of 100% cotton poplin with a subtle sheen, the Smock Dress has a comfortable, breathable feel with
                an effortlessly polished look.</p>
        </div>
    </div>
</section>
<%--</form>--%>
<jsp:include page="footer.jsp"/>
</body>
</html>
