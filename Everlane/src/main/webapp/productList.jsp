<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 5/20/2023
  Time: 9:52 PM
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
    <title>Product List</title>
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="a.template/assets/img/favicon/favicon.png" />
    <%--    icon--%>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="header/header1.css">
    <link rel="stylesheet" href="footer/footer.css">
    <link rel="stylesheet" href="footer/ad-container.css">
    <link rel="stylesheet" href="productpage/productlist/filter-container1.css">
    <link rel="stylesheet" href="productpage/productlist/product-list-container1.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
%>
<section class="Product_List_Container">
    <div class="Product_List_InnerContainer">
        <div class="Filter_Container">
            <a class='bx bx-x' id="x_filter-icon"></a>
            <div class="Category_Container">
                <h1>Category</h1>
                <c:forEach var="cate" items="${cateList}" varStatus="status">
                    <c:if test="${status.index < 8}">
                        <a href="${pageContext.request.contextPath}/productList-servlet?categoryID=${cate.getCategoryID()}" class="Category_Option">
                            <div class="checkbox"></div>
                            <span>${cate.getCategoryName()}</span>
                        </a>
                    </c:if>
                </c:forEach>
            </div>
            <div class="Color_Container">
                <h1>Color</h1>
                <div class="Color">
                    <a>
                        <div></div>
                        <span>Blac</span>
                    </a>
                    <a>
                        <div></div>
                        <span>Black</span>
                    </a>
                    <a>
                        <div></div>
                        <span>Black</span>
                    </a>
                    <a>
                        <div></div>
                        <span>Black</span>
                    </a>
                    <a>
                        <div></div>
                        <span>Black</span>
                    </a>
                    <a>
                        <div></div>
                        <span>Black</span>
                    </a>
                    <a>
                        <div></div>
                        <span>Black</span>
                    </a>
                    <a>
                        <div></div>
                        <span>Black</span>
                    </a>
                </div>

            </div>
            <div class="Size_Container">
                <h1>Size</h1>
                <div class="Size">
                    <a>XXS</a>
                    <a>XXS</a>
                    <a>XXS</a>
                    <a>XXS</a>
                    <a>XXS</a>
                    <a>XXS</a>
                    <a>XXS</a>
                </div>
            </div>
        </div>
        <div class="List_Container">
            <div class="Product_List_Headline">
                <h1>Women's Dresses & Jumpsuits</h1>
            </div>
            <a class="bx bx-menu" id="menu_Filter-icon">Filter</a>
            <div class="Product_List">
                <%
                    if(!productList.isEmpty()){
                %>
                <c:forEach var="p" items="${productList}">
                    <div class="Product">
                        <img src=${p.getProductImg()}>
                        <div class="Product_Text">
                            <div class="Product_Name">
                                <a href="${pageContext.request.contextPath}/productDetail-servlet?ProductID=${p.getProductID()}">${p.getProductName()}</a>
                                <p>${p.getPrice()}</p>
                            </div>
                            <p>${p.getColor()}</p>
                        </div>
                    </div>
                </c:forEach>
                <% } else {%>
                <h2>No products found</h2>
                <% } %>
            </div>

        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script>
    //cart
    z1 = document.querySelector("#menu_Filter-icon");
    z2 = document.querySelector("#x_filter-icon")
    z3 = document.querySelector(".Filter_Container");
    z1.onclick = function (){
        z3.classList.toggle("openFilter");
    }
    z2.onclick = function (){
        z3.classList.toggle("openFilter");
    }

</script>
</body>
</html>
