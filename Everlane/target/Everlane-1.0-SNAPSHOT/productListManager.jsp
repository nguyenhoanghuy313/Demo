<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 6/13/2023
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="controller.*" %>
<%@ page import="entity.Category" %>
<%@ page import="entity.ProductImg" %>
<%@ page import="entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
    List<Category> cateList = (List<Category>) request.getAttribute("cateList");
//    List<Product> productListSorted = (List<Product>) request.getAttribute("productListSorted");
    Product p = (Product) request.getAttribute("del");
    ProductImgDAO productImgDAO = new ProductImgDAO();
    List<ProductImg> productImgList = productImgDAO.getAllProductFolder();
    User user = (User) session.getAttribute("accHU");
%>
<html
        lang="en"
        class="light-style layout-menu-fixed"
        dir="ltr"
        data-theme="theme-default"
        data-assets-path="../bootstrap/assets/"
        data-template="vertical-menu-template-free"
>
<head>
    <meta charset="utf-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Product List</title>

    <meta name="description" content=""/>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="bootstrap/assets/vendor/fonts/boxicons.css"/>

    <!-- Core CSS -->
    <link rel="stylesheet" href="bootstrap/assets/vendor/css/core.css" class="template-customizer-core-css"/>
    <link rel="stylesheet" href="bootstrap/assets/vendor/css/theme-default.css" class="template-customizer-theme-css"/>
    <link rel="stylesheet" href="bootstrap/assets/css/demo.css"/>

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="bootstrap/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css"/>

    <!-- Page CSS -->
    <link rel="stylesheet" href="adminpage/product-list/product-list.css">

    <!-- Helpers -->
    <script src="bootstrap/assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="bootstrap/assets/js/config.js"></script>
</head>

<body>

<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <!-- Menu -->
        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
            <div class="app-brand demo">
                <a href="dashboardManager.jsp" class="app-brand-link">
              <span class="app-brand-logo demo">
                    <img src="webImage/other/logo/logo.png" style="width:128px; height:14px">
              </span>
                </a>

                <a href="dashboardManager.jsp"
                   class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
                    <i class="bx bx-chevron-left bx-sm align-middle"></i>
                </a>
            </div>

            <div class="menu-inner-shadow"></div>

            <ul class="menu-inner py-1">
                <!-- Dashboard -->
                <li class="menu-item active">
                    <a href="dashboardManager.jsp" class="menu-link">
                        <i class="menu-icon tf-icons bx bx-home-circle"></i>
                        <div data-i18n="Analytics">Dashboard</div>
                    </a>
                </li>

                <!-- Layouts -->
                <!-- Pages -->
                <li class="menu-header small text-uppercase"><span class="menu-header-text">Pages</span></li>
                <!-- Product List -->
                <%if (user.getRole() == 1) {%>
                <li class="menu-item">
                    <a href="${pageContext.request.contextPath}/StaffListManagerServlet?role=all" class="menu-link">
                        <i class='menu-icon tf-icons bx bx-user'></i>
                        <div data-i18n="User List">Staff List</div>
                    </a>
                </li>
                <%}%>
                <%if (user.getRole() == 1 || user.getRole() == 2) {%>
                <li class="menu-item">
                    <a href="${pageContext.request.contextPath}/ProductListManagerServlet?input=all"
                       class="menu-link">
                        <i class='menu-icon tf-icons bx bxs-package'></i>
                        <div data-i18n="Product List">Product List</div>
                    </a>
                </li>
                <!-- User List -->
                <li class="menu-item">
                    <a href="${pageContext.request.contextPath}/UserListManagerServlet?role=4" class="menu-link">
                        <i class='menu-icon tf-icons bx bx-user'></i>
                        <div data-i18n="User List">Customer List</div>
                    </a>
                </li>
                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Sale">Sale</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="PromotionServlet?input=all" class="menu-link">
                                <div data-i18n="Promotion List">Promotion List</div>
                            </a>
                        </li>
                    </ul>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="${pageContext.request.contextPath}/CollectionUpdatePromotion" class="menu-link">
                                <div data-i18n="Promotion List">Season Collection (Update Promotion)</div>
                            </a>
                        </li>
                    </ul>
                </li>
                <%}%>
                <%if (user.getRole() == 1 || user.getRole() == 3) {%>
                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Marketing">Marketing</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="${pageContext.request.contextPath}/seasonCollectionEditServlet" class="menu-link">
                                <div data-i18n="Season Collection">Season Collection (Home Page)</div>
                            </a>
                        </li>
                    </ul>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="${pageContext.request.contextPath}/categoryEditServlet" class="menu-link">
                                <div data-i18n="Season Collection">Category (Home Page)</div>
                            </a>
                        </li>
                    </ul>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="${pageContext.request.contextPath}/StoryServlet?input=all" class="menu-link">
                                <div data-i18n="Story List">Story List (Story Page)</div>
                            </a>
                        </li>
                    </ul>
                </li>
                <%}%>
            </ul>
        </aside>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">
            <!-- Navbar -->
            <nav
                    class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
                    id="layout-navbar"
            >
                <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
                    <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                        <i class="bx bx-menu bx-sm"></i>
                    </a>
                </div>

                <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
                    <ul class="navbar-nav flex-row align-items-center ms-auto">
                        <!-- User -->
                        <li class="nav-item navbar-dropdown dropdown-user dropdown">
                            <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);"
                               data-bs-toggle="dropdown">
                                <div class="avatar avatar-online">
                                    <img src="webImage/other/icon/ava.png" alt
                                         class="w-px-40 h-auto rounded-circle"/>
                                </div>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <a class="dropdown-item" href="#">
                                        <div class="d-flex">
                                            <div class="flex-shrink-0 me-3">
                                                <div class="avatar avatar-online">
                                                    <img src="webImage/other/icon/ava.png" alt
                                                         class="w-px-40 h-auto rounded-circle"/>
                                                </div>
                                            </div>
                                            <div class="flex-grow-1">
                                                <span class="fw-semibold d-block"><%=user.getFirstName()%> <%=user.getLastName()%></span>
                                                <%if (user.getRole() == 1) {%>
                                                <small class="text-muted">Admin
                                                </small>
                                                <%} else if (user.getRole() == 2) {%>
                                                <small class="text-muted">Sale
                                                </small>
                                                <%} else if (user.getRole() == 3) {%>
                                                <small class="text-muted">Marketing
                                                </small>
                                                <%} else {%>
                                                <small class="text-muted">Customer
                                                </small>
                                                <%}%>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <div class="dropdown-divider"></div>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="HighUserAccountDetailServlet">
                                        <i class="bx bx-user me-2"></i>
                                        <span class="align-middle">My Profile</span>
                                    </a>
                                </li>
                                <li>
                                    <div class="dropdown-divider"></div>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="logout-servlet">
                                        <i class="bx bx-power-off me-2"></i>
                                        <span class="align-middle">Log Out</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!--/ User -->
                    </ul>
                </div>
            </nav>
            <!-- / Navbar -->
            <!-- Content wrapper -->
            <div class="content-wrapper">
                <!-- Content -->
                <div class="list_option_container">
                    <div class="list_option_container1">
                        <div class="input-group ">
                            <a href="CreateNewProductServlet" class="btn btn-outline-dark" type="button">New Product</a>
                        </div>
                    </div>
                    <div class="list_option_container2">

                        <div class="input-group input-group-merge">
                            <form action="${pageContext.request.contextPath}/ProductListManagerServlet?input=all"
                                  method="post" style="display: flex; flex-direction: row">
                                <input name="productName" type="text" class="form-control" placeholder="Search..." aria-label="Search..." aria-describedby="basic-addon2">
                                <div  class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="button"><i class='bx bx-search'></i></button>
                                </div>
                            </form>
                        </div>

                        <div class="input-group">
                            <button
                                    class="btn btn-outline-dark dropdown-toggle"
                                    type="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                            >
                                Sort by
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/ProductListManagerServlet?input=all">All</a>
                                </li>
                                <li><a class="dropdown-item" href="javascript:void(0);">Sort by Latest</a></li>
                                <li><a class="dropdown-item" href="javascript:void(0);">Sort by Newest</a></li>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/ProductListManagerServlet?input=up">Sort
                                    by Price: Up</a></li>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/ProductListManagerServlet?input=down">Sort
                                    by Price: Down</a></li>
                            </ul>
                        </div>
                        <div class="input-group">
                            <button
                                    class="btn btn-outline-dark dropdown-toggle"
                                    type="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                            >
                                Category
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/ProductListManagerServlet?input=all">All</a>
                                </li>
                                <c:forEach var="cate" items="${cateList}" varStatus="status">
                                    <c:if test="${status.index < 8}">
                                        <li><a class="dropdown-item"
                                               href="${pageContext.request.contextPath}/ProductListManagerServlet?input=${cate.getCategoryID()}">${cate.getCategoryName()}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="container-xxl flex-grow-1 container-p-y ">
                    <div class="card">
                        <h5 class="card-header">Products</h5>
                        <div class="table-responsive text-nowrap listtable">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>PRODUCT ID</th>
                                    <th>PRODUCT THUMBNAIL</th>
                                    <th>PRODUCT IMG1</th>
                                    <th>PRODUCT IMG2</th>
                                    <th>PRODUCT IMG3</th>
                                    <th>CATEGORY NAME</th>
                                    <th>COLLECTION ID</th>
                                    <th>PRODUCT NAME</th>
                                    <th>COLOR NAME</th>
                                    <th>SIZE NAME</th>
                                    <th>PRICE</th>
                                    <th>QTU IN STOCK</th>
                                    <th>ACTION</th>
                                </tr>
                                </thead>
                                <tbody class="table-border-bottom-0">
                                <%
                                    for (Product x : productList) {
                                %>
                                <tr class="item">
                                    <td><%=x.getProductID()%>
                                    </td>
                                    <td><img src="webImage/productImg/<%=x.getThumbnail()%>"></td>
                                    <td><img src="webImage/productImg/<%=x.getProduct_img_1()%>"></td>
                                    <td><img src="webImage/productImg/<%=x.getProduct_img_2()%>"></td>
                                    <td><img src="webImage/productImg/<%=x.getProduct_img_3()%>"></td>
                                    <td><%=x.getCategoryName()%>
                                    </td>
                                    <td><%=x.getCollectionID()%>
                                    </td>
                                    <td><%=x.getProductName()%>
                                    </td>
                                    <td><%=x.getColor_Name()%>
                                    </td>
                                    <td><%=x.getSize_Name()%>
                                    </td>
                                    <td><%=x.getPrice()%>
                                    </td>
                                    <td><%=x.getQty_in_stock()%>
                                    </td>
                                    <td>
                                        <div class="dropdown">
                                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow"
                                                    data-bs-toggle="dropdown">
                                                <i class="bx bx-dots-vertical-rounded"></i>
                                            </button>
                                            <div class="dropdown-menu">
                                                <a class="dropdown-item" href="EditProductServlet?input=1&ProductID=<%=x.getProductID()%>"
                                                ><i class="bx bx-edit-alt me-1"></i> Edit Product Information</a
                                                >
                                                <a class="dropdown-item" href="EditProductServlet?input=3&ProductID=<%=x.getProductID()%>&ColorName=<%=x.getColor_Name()%>&SizeName=<%=x.getSize_Name()%>"
                                                ><i class="bx bx-edit-alt me-1"></i> Edit Variation</a
                                                >
                                                <a class="dropdown-item"
                                                   href="${pageContext.request.contextPath}/DeleteProduct?ProductID=<%=x.getProductID()%>&color_Name=<%=x.getColor_Name()%>&size_Name=<%=x.getSize_Name()%>&"
                                                   onclick="return confirm(
                                                                    'Are you sure want to delete this product?')"
                                                ><i class="bx bx-trash me-1"></i> Delete</a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="container-xxl flex-grow-1 container-p-y ">
                    <div class="card">
                        <h5 class="card-header">Product Img File List</h5>
                        <h6 class="card-header" style="color: red">${error}</h6>
                        <div class="table-responsive text-nowrap listtable">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>PRODUCT IMG ID</th>
                                    <th>PRODUCT IMG NAME</th>
                                    <th>PRODUCT THUMBNAIL</th>
                                    <th>PRODUCT IMG1</th>
                                    <th>PRODUCT IMG2</th>
                                    <th>PRODUCT IMG3</th>
                                    <th>ACTION</th>
                                </tr>
                                </thead>
                                <tbody class="table-border-bottom-0">
                                <%
                                    for (ProductImg pi : productImgList) {
                                %>
                                <tr class="item">
                                    <th><%=pi.getProduct_Img_ID()%></th>
                                    <th><%=pi.getProduct_img_name()%></th>
                                    <th><img src="webImage/productImg/<%=pi.getThumbnail()%>"></th>
                                    <th><img src="webImage/productImg/<%=pi.getProduct_Img_1()%>"></th>
                                    <th><img src="webImage/productImg/<%=pi.getProduct_Img_2()%>"></th>
                                    <th><img src="webImage/productImg/<%=pi.getProduct_Img_3()%>"></th>
                                    <td>
                                        <div class="dropdown">
                                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow"
                                                    data-bs-toggle="dropdown">
                                                <i class="bx bx-dots-vertical-rounded"></i>
                                            </button>
                                            <div class="dropdown-menu">
                                                <a class="dropdown-item" href="EditProductServlet?input=2&ImageID=<%=pi.getProduct_Img_ID()%>"
                                                ><i class="bx bx-edit-alt me-1"></i> Edit Product Image</a>
                                                <a class="dropdown-item"
                                                   href="${pageContext.request.contextPath}/DeleteImgFolderServlet?ImageID=<%=pi.getProduct_Img_ID()%>"
                                                   onclick="return confirm(
                                                                    'Are you sure want to delete this image folder?')"
                                                ><i class="bx bx-trash me-1"></i> Delete</a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- / Content -->


                <div class="content-backdrop fade"></div>
            </div>
            <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
    </div>

    <!-- Overlay -->
    <div class="layout-overlay layout-menu-toggle"></div>
</div>
<!-- / Layout wrapper -->

<!-- Core JS -->
<!-- build:js assets/vendor/js/core.js -->
<script src="bootstrap/assets/vendor/libs/jquery/jquery.js"></script>
<script src="bootstrap/assets/vendor/libs/popper/popper.js"></script>
<script src="bootstrap/assets/vendor/js/bootstrap.js"></script>
<script src="bootstrap/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

<script src="bootstrap/assets/vendor/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->

<!-- Main JS -->
<script src="bootstrap/assets/js/main.js"></script>

<!-- Page JS -->

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>

