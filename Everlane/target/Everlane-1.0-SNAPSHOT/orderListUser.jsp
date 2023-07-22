<%@ page import="entity.User" %>
<%@ page import="model.OrderDetailDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.ShopOrderDAO" %>
<%@ page import="entity.ShopOrder" %><%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 06/07/2023
  Time: 11:58 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

  <title>Order List</title>

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
<%
  User u = (User) request.getSession().getAttribute("acc");
  String xUID = String.valueOf(u.getUserID());
  OrderDetailDAO odd = new OrderDetailDAO();
  ShopOrderDAO sod = new ShopOrderDAO();
  List<ShopOrder> Bill = sod.getOrdersByUserID(Integer.parseInt(xUID));
//  List<Product> odredPro = odd.getUserOrder(xUID);
%>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
  <div class="layout-container">
    <!-- Menu -->
    <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
      <div class="app-brand demo">
        <a href="${pageContext.request.contextPath}/home-servlet" class="app-brand-link">
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
        <!-- Account Settings -->
        <li class="menu-item active">
          <a href="user-account-detail-servlet" class="menu-link ">
            <i class='menu-icon tf-icons bx bx-user'></i>
            <div data-i18n="Account Setting">Account Setting</div>
          </a>
        </li>
        <!-- User List -->
        <li class="menu-item">
          <a href="orderListUser.jsp" class="menu-link">
            <i class='menu-icon tf-icons bx bxs-package'></i>
            <div data-i18n="Orders & Returns">Orders List</div>
          </a>
        </li>

        <li class="menu-item">
          <a href="${pageContext.request.contextPath}/changePassword" class="menu-link">
            <i class='menu-icon tf-icons bx bxs-package'></i>
            <div data-i18n="Change password">Change password</div>
          </a>
        </li>
        <!-- Forms -->
        <%--logout--%>
        <li class="menu-item">
          <a href="logout-servlet" class="menu-link ">
            <i class='menu-icon tf-icons bx bx-user'></i>
            <div data-i18n="Account Setting" style="color:red;">Log out</div>
          </a>
        </li>
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


      </nav>
      <!-- / Navbar -->
      <!-- Content wrapper -->
      <div class="content-wrapper">
        <!-- Content -->
        <div class="container-xxl flex-grow-1 container-p-y ">
          <h4 class="fw-bold py-3 mb-4">Orders List</h4>
          <div class="card">
            <h5 class="card-header">Products</h5>
            <div class="table-responsive text-nowrap listtable">
              <table class="table">
                <thead>
                <tr>
                  <th>Order ID</th>
                  <th>Total Price</th>
                  <th>Status</th>
                  <th>Recipient</th>
                  <th>Recipient's Phone Number</th>
                </tr>
                </thead>
                <%
                  for (ShopOrder so: Bill){


                %>
                <tbody class="table-border-bottom-0">
                <tr class="item">
                  <td><%=so.getShop_orderID()%></td>
                  <th><%=so.getOrder_total()%></th>
                  <th><%=so.getOrder_status()%></th>
                  <th><%=so.getRecipient()%></th>
                  <th><%=so.getRecipent_phone()%></th>
                  <td>
                    <div class="dropdown">
                      <button type="button" class="btn p-0 dropdown-toggle hide-arrow"
                              data-bs-toggle="dropdown">
                        <i class="bx bx-dots-vertical-rounded"></i>
                      </button>
                      <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/OrderDetail?buyerID=<%=xUID%>&OrderID=<%=so.getShop_orderID()%>&mod=1"
                        >Detail</a>
                      </div>
                    </div>
                  </td>
                </tr>
                </tbody>
                <%
                  }
                %>
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
