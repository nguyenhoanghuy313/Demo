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
    <link rel="stylesheet" href="productpage/productdetail/product-detail.css">
    <link rel="stylesheet" href="homepage/best-seller-slider1.css">
    <link rel="stylesheet" href="footer/ad-container.css">
    <link rel="stylesheet" href="footer/footer.css">
    <%--    <title>Product Detail</title>--%>
    <title><%=pi.getProductName()%>
    </title>
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="a.template/assets/img/favicon/favicon.png"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<%--<form action="addToCart" method="post">--%>
<section class="Product_Detail_Container">
    <div class="Product_Image_Container">
        <img src=<%= pi.getThumbnail()%>>
        <img src=<%= pi.getProduct_img_1()%>>
        <img src=<%= pi.getProduct_img_2()%>>
        <img src=<%= pi.getProduct_img_3()%>>
    </div>
    <div class="Product_Detail">
        <div class="Product_Name">
            <p class="Category_Tag"><%= pi.getCategoryName()%>
            </p>
            <div class="Name_And_Price">
                <h1><%= pi.getProductName()%>
                </h1>
                <%--                                <h2>variationID: <%=pi.getVariationID()%></h2>--%>
                <%--                                <h2>prodID: <%=pi.getProductID()%></h2>--%>
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
                    <p><%= pi.getPrice()%>
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
                    <a href="${pageContext.request.contextPath}/productDetail-servlet?ProductID=<%= pi.getProductID()%>&color_Name=<%= color.getColor_Name()%>">
                        <div style="background-color: <%= color.getColor_Name()%>"></div>
                        <span><%= color.getColor_Name()%></span>
                    </a>
                    <% } %>
                </div>
            </div>

            <div class="Product_Size">
                <h1>Size</h1>
                <div class="Size">
                    <input type="hidden" name="ProductID" value="<%= pi.getProductID() %>">
                    <input type="hidden" name="color_Name" value="<%= pi.getColor_Name() %>">
                    <input type="hidden" name="VariationID" value="<%= pi.getVariationID() %>">
                    <%--                    <input type="hidden" name="productName" value="<%= pi.getProductName() %>">--%>
                    <% for (Size size : sizes) { %>
                    <input type="radio" id="size_name" name="size_name" value="<%= size.getSize_Name() %>">
                    <label for="size_name"><%= size.getSize_Name() %>
                    </label><br>
                    <%--                    <button type="submit" name="size_name" value="<%= size.getSize_Name() %>"><%= size.getSize_Name() %>--%>
                    <%--                    </button>--%>
                    <% } %>
                </div>
            </div>
            <button color="white" type="submit">
<%--                    href="addToCart?ProductID=<%=pi.getProductID()%>&VariationID=<%=pi.getVariationID()%>">--%>
                Add
                To Bag
            </button>
        </form>


        <%--        href="${pageContext.request.contextPath}/addToCart?ProductID=<%=pi.getProductID()%>&VariationID=<%=pi.getVariationID()%>"--%>
        <div class="Product_Description">
            <h1>Description</h1>
            <p>Made of 100% cotton poplin with a subtle sheen, the Smock Dress has a comfortable, breathable feel with
                an effortlessly polished look.</p>
            <%--            <p> ${CartMess}</p>--%>
            <%--            <form action="addToCart" method="GET">--%>
            <%--                <h2>--%>
            <%--                    ${CartMess}--%>
            <%--                </h2>--%>
            <%--            </form>--%>
        </div>
    </div>
</section>
<%--</form>--%>
<section class="Best_Seller_Slider">
    <div class="wrapper">
        <i id="left" class='bx bx-chevron-left'></i>
        <div class="carousel">

            <c:forEach var="p" items="${data}">
                <a href="${pageContext.request.contextPath}/productDetail-servlet?ProductID=${p.getProductID()}">
                    <img src=${p.getProductImg()}>
                    <div class="Product_Name">
                        <div class="Best_Seller_Text">
                            <h1>${p.getProductName()}</h1>
                            <h1>${p.getPrice()}</h1>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
        <i id="right" class='bx bx-chevron-right'></i>
    </div>
</section>

<jsp:include page="footer.jsp"/>
<script>
    const carousel = document.querySelector(".carousel"),
        firstImg = carousel.querySelectorAll("img")[0],
        arrowIcons = document.querySelectorAll(".wrapper i");

    arrowIcons.forEach(icon => {
        icon.addEventListener("click", () => {
            let firstImgWidth = firstImg.clientWidth + 14; // getting first img width & adding 14 margin value
            // if clicked icon is left, reduce width value from the carousel scroll left else add to it
            carousel.scrollLeft += icon.id == "left" ? -firstImgWidth : firstImgWidth;
        });
    });
</script>
</body>
</html>
