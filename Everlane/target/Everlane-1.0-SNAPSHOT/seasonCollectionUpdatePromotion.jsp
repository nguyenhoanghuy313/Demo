<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 29/06/2023
  Time: 12:02 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html
        class="light-style layout-menu-fixed"
        data-assets-path="../bootstrap/assets/"
        data-template="vertical-menu-template-free"
        data-theme="theme-default"
        dir="ltr"
        lang="en"
>
<head>
    <meta charset="utf-8"/>
    <meta
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
            name="viewport"
    />

    <title>Season Collection Edit</title>

    <meta content="" name="description"/>

    <!-- Favicon -->
    <link href="webImage/other/icon/favicon.png" rel="icon" type="image/x-icon"/>

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com" rel="preconnect"/>
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link href="bootstrap/assets/vendor/fonts/boxicons.css" rel="stylesheet"/>

    <!-- Core CSS -->
    <link class="template-customizer-core-css" href="bootstrap/assets/vendor/css/core.css" rel="stylesheet"/>
    <link class="template-customizer-theme-css" href="bootstrap/assets/vendor/css/theme-default.css" rel="stylesheet"/>
    <link href="bootstrap/assets/css/demo.css" rel="stylesheet"/>

    <!-- Vendors CSS -->
    <link href="bootstrap/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet"/>

    <link href="bootstrap/assets/vendor/libs/apex-charts/apex-charts.css" rel="stylesheet"/>

    <!-- Page CSS -->

    <!-- Helpers -->
    <script src="bootstrap/assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="bootstrap/assets/js/config.js"></script>
</head>
<%
    List<Collection> collectionList = (List<Collection>) request.getAttribute("collectionList");
    User user = (User) session.getAttribute("accHU");

%>
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
                <div class="container-xxl flex-grow-1 container-p-y">
                    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Sale / </span> Season
                        Collection
                    </h4>
                    <div class="row">
                        <div class="col-xxl">
                            <div class="card mb-4">
                                <div class="card-header d-flex align-items-center justify-content-between">
                                    <h5 class="mb-0">Edit Season Collection</h5>
                                </div>
                                <div class="card-body">
                                    <form action="CollectionUpdatePromotion" method="get" id="frm">
                                        <div class="row mb-3">
                                            <label for="exampleFormControlSelect1" class="col-sm-2 col-form-label">Select
                                                Collection</label>
                                            <select class=" form-select col-sm-10" id="exampleFormControlSelect1" required
                                                    name="collectionIDGet"
                                                    aria-label="Default select example" onchange="change()">
<%--                                                <option value="${c.getCollectionID()}" selected>${c.getCollectionName()}</option>--%>
                                                <option value="1" ${c.getCollectionID() != "1" ? "" : "selected"}>Summer Collection</option>
                                                <option value="2" ${c.getCollectionID() != "2" ? "" : "selected"}>Winter Collection</option>
                                                <option value="3" ${c.getCollectionID() != "3" ? "" : "selected"}>Spring Collection</option>
                                                <option value="4" ${c.getCollectionID() != "4" ? "" : "selected"}>Autumn Collection</option>
                                            </select>
                                        </div>
                                    </form>
                                    <form action="CollectionUpdatePromotion" method="post" >
                                        <div class="row mb-3">
                                            <div class="col-sm-10">
                                                <input class="form-control" id="basic-default-image"
                                                       name="collectionImg"
                                                       value="${c.getCollectionImg()}" type="hidden"/>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-10">
                                                <input
                                                        class="form-control"
                                                        name="collectionName"
                                                        id="basic-default-h1"
                                                        value="${c.getCollectionName()}"
                                                        type="hidden"
                                                />
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-10">
                                                <input
                                                        class="form-control"
                                                        name="collectionDescription"
                                                        id="basic-default-p"
                                                        value="${c.getCollection_description()}"
                                                        type="hidden"
                                                />
                                            </div>
                                        </div>
                                        <input class="form-control"
                                               name="collectionID"
                                               value="${c.getCollectionID()}" type="hidden"/>
                                        <input type="hidden" id="currentTimeInput" name="createDate">
                                        <div class="row mb-3">
                                            <label class="col-sm-2 col-form-label"
                                                   for="basic-default-promo">Promotion ID</label>
                                            <div class="col-sm-10">
                                                <input
                                                        class="form-control"
                                                        name="promotionID"
                                                        id="basic-default-promo"
                                                        value="${c.getPromotionID()}"
                                                        type="text"
                                                        required
                                                />
                                            </div>
                                        </div>
                                        <div style="color:green;">
                                            ${message}
                                        </div>
                                        <div class="mt-2">
                                            <button type="submit" class="btn btn-dark me-2">Save changes</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
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
<script>
    function getCurrentDateTime() {
        var currentDateTime = new Date();

        var year = currentDateTime.getFullYear();
        var month = currentDateTime.getMonth() + 1; // Tháng được đánh số từ 0 đến 11, nên cần +1
        var day = currentDateTime.getDate();
        var hours = currentDateTime.getHours();
        var minutes = currentDateTime.getMinutes();
        var seconds = currentDateTime.getSeconds();

        var formattedDateTime = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds ;

        return formattedDateTime;
    }

    // Lấy tham chiếu đến phần tử input
    var inputElement = document.getElementById('currentTimeInput');

    // Gán giá trị thời gian hiện tại vào thuộc tính value của input
    inputElement.value = getCurrentDateTime();
</script>
<script>
    function change() {
        document.getElementById("frm").submit();
    }
</script>
<script src="bootstrap/assets/vendor/libs/jquery/jquery.js"></script>
<script src="bootstrap/assets/vendor/libs/popper/popper.js"></script>
<script src="bootstrap/assets/vendor/js/bootstrap.js"></script>
<script src="bootstrap/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

<script src="bootstrap/assets/vendor/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->
<script src="bootstrap/assets/vendor/libs/apex-charts/apexcharts.js"></script>

<!-- Main JS -->
<script src="bootstrap/assets/js/main.js"></script>

<!-- Page JS -->
<script src="bootstrap/assets/js/dashboards-analytics.js"></script>

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
