<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 5/17/2023
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="controller.*" %>
<%@ page import="model.Collection" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Everlane</title>
    <%--    icon--%>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

    <%--    fonts--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@200&display=swap" rel="stylesheet">


    <%--    css--%>
    <link rel="stylesheet" href="header/header1.css">
    <link href="header/pop-up/pop-up.css" rel="stylesheet">
    <link rel="stylesheet" href="homepage/season-collection1.css">
    <link rel="stylesheet" href="homepage/category.css">
    <link rel="stylesheet" href="homepage/best-seller-slider1.css">
    <link rel="stylesheet" href="homepage/sale-banner.css">
    <link rel="stylesheet" href="homepage/story-container1.css">
    <link rel="stylesheet" href="footer/footer.css">

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="a.template/assets/img/favicon/favicon.png"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<%
    Collection collection = (Collection) request.getAttribute("collection");
    Promotion promotion = (Promotion) request.getAttribute("promotion");

    CategoryDAO c = new CategoryDAO();
    List<Category> cateList = c.getAllCategory();

%>
<div class="pop_up_container">
    <div class="pop_up" style="background: ${promotion.getBackground_color()}">
        <i class='bx bx-x' id="pop_up_x"></i>
        <h1>
            ${promotion.getPromotionName()}: Now up to ${promotion.getDiscountRate()}% off
        </h1>
        <button value="collectionID" name="collectionID">
            <a href="${pageContext.request.contextPath}/productList-servlet?collectionID=${collection.getCollectionID()}&categoryID=3&color_ID=all">
                SHOP NOW
            </a>
        </button>
    </div>
</div>
    <section class="Season_Collection_Banner" id="Season_Collection_Banner">
        <img src="${collection.getCollectionImg()}"
                     alt="">
        <div class="Season_Collection_Banner_text">
            <h1>${collection.getCollectionName()}</h1>
            <p>${collection.getCollection_description()}</p>
            <button value="collectionID" name="collectionID">
                <a href="${pageContext.request.contextPath}/productList-servlet?collectionID=${collection.getCollectionID()}&categoryID=3&color_ID=all">
                    SHOP THE COLLECTION
                </a>
            </button>
        </div>
    </section>
<section class="Category_Container">
    <h1>Shop by Category</h1>
    <div class="Category_List_Container">
        <c:forEach var="cate" items="${cateList}" varStatus="status">
            <c:if test="${status.index < 8}">
                <div class="Category">
                    <img src="${cate.getCategoryImg()}" alt="">
                    <div class="Category_text">
                        <button value="cateID"><a
                                href="${pageContext.request.contextPath}/productList-servlet?categoryID=${cate.getCategoryID()}&color_ID=all"> ${cate.getCategoryName()}</a>
                        </button>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</section>

<section class="Category_Container">
    <div class="Category_List_Container">
        <div class="Category2">
            <img src="https://media.everlane.com/image/upload/c_scale,dpr_1.0,f_auto,q_auto,w_auto/c_limit,w_900/v1/i/73436bb3_0cdc.png">
            <div class="Category_text">
                <h2>New Arrivals</h2>
                <button>
                    <a href="${pageContext.request.contextPath}/productList-servlet?categoryID=1&color_ID=all&mod=bottom">Shop Now</a>
                </button>
            </div>
        </div>
        <div class="Category2">
            <img src="https://media.everlane.com/image/upload/c_scale,dpr_1.0,f_auto,q_auto,w_auto/c_limit,w_900/v1/i/73436bb3_0cdc.png">
            <div class="Category_text">
                <h2>Best Sellers</h2>
                <button>Buy</button>
            </div>
        </div>
    </div>
    <h1>Best-Sellers: Wear Now, Love Forever</h1>
</section>

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

<section class="Sales_Banner" style="background: ${promotion.getBackground_color()}">
    <h1>
        ${promotion.getPromotionName()}: Now up to ${promotion.getDiscountRate()}% off
    </h1>
    <button>SHOP NOW</button>
</section>

<section class="Stories_Container">
<c:forEach var="story" items="${storyList}" varStatus="a">
    <c:if test="${a.index < 2}">
    <a style="color: black" href="StoryCusServlet?input=${story.getStory_ID()}" class="Story">
        <h1>${story.getTitle()}</h1>
        <button>Learn More</button>
        <div class="Storyimg">
            <img src="${story.getThumbnail()}">
        </div>
    </a>
    </c:if>
</c:forEach>
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

    // pop up
    xSale = document.querySelector("#pop_up_x");
    ySale = document.querySelector(".pop_up_container");
    xSale.onclick = function () {
        ySale.style.display = "none";
    }
</script>
</body>
</html>
