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
<%@ page import="entity.Collection" %>
<%@ page import="entity.Category" %>
<%@ page import="entity.Color" %>
<%@ page import="entity.Size" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Product List</title>
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>
    <%--    icon--%>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="header/header1.css">
    <link rel="stylesheet" href="footer/footer.css">
    <link rel="stylesheet" href="footer/ad-container.css">
    <link rel="stylesheet" href="productpage/productlist/filter-container.css">
    <link rel="stylesheet" href="productpage/productlist/product-list-container1.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<%
    CollectionDAO col = new CollectionDAO();
    Category category = (Category) request.getAttribute("category");
    List<Product> productList = (List<Product>) request.getAttribute("productList");
    List<Color> colorList = (List<Color>) request.getAttribute("colorList");
    Collection collection = col.getCollectionsByDate();
    List<Size> sizeList = (List<Size>) request.getAttribute("sizeList");
%>
<section class="Product_List_Container">
    <div class="Product_List_InnerContainer">
        <div class="Filter_Container">
            <a class='bx bx-x' id="x_filter-icon"></a>
            <div class="Category_Container">
                <h1>Category</h1>
                <c:forEach var="cate" items="${cateList}" varStatus="status">
                    <c:if test="${status.index < 8}">
                        <%
                            if (request.getParameter("collectionID") == null) {
                        %>
                        <%
                            if(request.getParameter("mod")!=null) {
                        %>
                        <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=${cate.getCategoryID()}&color_ID=all&mod=bottom"
                           class="Category_Option">
                            <span>${cate.getCategoryName()}</span>
                        </a>
                        <%
                        } else {
                        %>
                        <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=${cate.getCategoryID()}&color_ID=all"
                           class="Category_Option">
                            <span>${cate.getCategoryName()}</span>
                        </a>
                        <%}%>
                        <%
                        } else {
                        %>
                        <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=<%=collection.getCollectionID()%>&color_ID=all&collectionID=${cate.getCategoryID()}"
                           class="Category_Option">
                            <span>${cate.getCategoryName()}</span>
                        </a>
                        <%}%>
                    </c:if>
                </c:forEach>
            </div>
            <div class="Color_Container">
                <%
                    if(category.getCategoryID() != 9){
                %>
                <h1>Color</h1>
                <div class="Color">
                    <%
                        for (Color color : colorList) {
                    %>
                    <%
                        if (request.getParameter("collectionID") == null) {
                    %>
                    <%
                        if(request.getParameter("mod")!=null) {
                    %>
                    <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=<%=category.getCategoryID()%>&color_ID=<%= color.getColor_ID()%>&mod=bottom">
                        <div style="background-color: <%= color.getColor_Name()%>"></div>
                        <span><%= color.getColor_Name()%></span>
                    </a>
                    <%
                    }else {
                    %>
                    <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=<%=category.getCategoryID()%>&color_ID=<%= color.getColor_ID()%>>">
                        <div style="background-color: <%= color.getColor_Name()%>"></div>
                        <span><%= color.getColor_Name()%></span>
                    </a>
                    <%}%>
                    <%
                    }else {
                    %>
                    <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=<%=category.getCategoryID()%>&color_ID=<%= color.getColor_ID()%>&collectionID=<%=collection.getCollectionID()%>>">
                        <div style="background-color: <%= color.getColor_Name()%>"></div>
                        <span><%= color.getColor_Name()%></span>
                    </a>
                    <%}%>
                    <%}%>
                </div>
                <%}%>
            </div>
        </div>
        <div class="List_Container">
            <div class="Product_List_Headline">
                <h1><%=category.getCategoryName()%></h1>
            </div>

            <a class="bx bx-menu" id="menu_Filter-icon">Filter</a>
            <div class="Product_List">
                <%
                    if (productList.isEmpty()) {
                %>
                <h2 style="font-family: 'Nunito Sans', sans-serif;">No products found</h2>
                <%
                } else {
                %>
                <%
                    for (Product x : productList) {
                %>
                <div class="Product">
                    <img src="webImage/productImg/<%= x.getThumbnail()%>">
                    <div class="Product_Text">
                        <div class="Product_Name">
                            <a href="${pageContext.request.contextPath}/productDetail-servlet?ProductID=<%= x.getProductID()%>&color_Name=<%= x.getColor_Name()%>"><%= x.getProductName()%></a>
                            <%
                            if(x.getDiscount() != 0){
                            %>
                            <p style="text-decoration:line-through"><%= x.getPrice()%></p>
                            <p><%= x.getDiscount()%></p>
                            <%
                                }else{
                            %>
                            <p><%= x.getPrice()%></p>
                            <% } %>
                        </div>
                        <p><%= x.getColor_Name()%></p>
                    </div>
                </div>
                <% } %>
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
    z1.onclick = function () {
        z3.classList.toggle("openFilter");
    }
    z2.onclick = function () {
        z3.classList.toggle("openFilter");
    }

</script>
</body>
</html>
