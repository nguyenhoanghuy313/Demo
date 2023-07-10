<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 22/06/2023
  Time: 10:14 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="controller.*" %>
<%@ page import="java.util.Collection" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  CategoryDAO c = new CategoryDAO();
  List<Category> cateList = c.getAllCategory();

  ProCollectionDAO colDAO = new ProCollectionDAO();
  List<ProCollection> proCollectionsList = colDAO.getAllCollections();

  ProductForEditDAO proEditDAO = new ProductForEditDAO();
  List<ProductForEdit> productForEditList = proEditDAO.getAllProductForEdit();

  ColorDAO colorDAO = new ColorDAO();
  List<Color> colorList = colorDAO.getAllColors();

  SizeDAO sizeDAO = new SizeDAO();
  List<Size> sizeList = sizeDAO.getAllSizes();

  ProductImgDAO productImgDAO = new ProductImgDAO();
  List<ProductImg> productImgList = productImgDAO.getAllProductFolder();

  ProductImg picheckname = (ProductImg) request.getAttribute("picheckname");
  ProductForEdit pfe = (ProductForEdit) request.getAttribute("pfe");
%>
<html
        class="light-style layout-menu-fixed"
        data-assets-path="../a.template/assets/"
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

  <title>Create New Product</title>

  <meta content="" name="description"/>

  <!-- Favicon -->
  <link href="a.template/assets/img/favicon/favicon.png" rel="icon" type="image/x-icon"/>

  <!-- Fonts -->
  <link href="https://fonts.googleapis.com" rel="preconnect"/>
  <link crossorigin href="https://fonts.gstatic.com" rel="preconnect"/>
  <link
          href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet"
  />

  <!-- Icons. Uncomment required icon fonts -->
  <link href="a.template/assets/vendor/fonts/boxicons.css" rel="stylesheet"/>

  <!-- Core CSS -->
  <link class="template-customizer-core-css" href="a.template/assets/vendor/css/core.css" rel="stylesheet"/>
  <link class="template-customizer-theme-css" href="a.template/assets/vendor/css/theme-default.css" rel="stylesheet"/>
  <link href="a.template/assets/css/demo.css" rel="stylesheet"/>

  <!-- Vendors CSS -->
  <link href="a.template/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet"/>

  <link href="a.template/assets/vendor/libs/apex-charts/apex-charts.css" rel="stylesheet"/>

  <!-- Page CSS -->

  <!-- Helpers -->
  <script src="a.template/assets/vendor/js/helpers.js"></script>

  <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
  <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
  <script src="a.template/assets/js/config.js"></script>
</head>

<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
  <div class="layout-container">
    <!-- Menu -->
    <aside class="layout-menu menu-vertical menu bg-menu-theme" id="layout-menu">
      <div class="app-brand demo">
        <a class="app-brand-link" href="dashboardManager.jsp">
              <span class="app-brand-logo demo">
                    <svg class="styles_everlane-logo__4o010" fill="none" height="14px" viewBox="0 0 128 14" width="128"
                         xmlns="http://www.w3.org/2000/svg"><path
                            d="M116.776 0V14H127.933V11.2198H119.6V8.38015H127.032V5.61985H119.6V2.78015H127.933V0H116.776ZM108.221 0V9.45982L101.311 0H98.5062V14H101.311V4.54018L108.222 14H111.066V0H108.222H108.221ZM86.0055 0L80.3766 14H83.3211L84.4828 11.1397H90.2922L91.4341 14H94.4191L88.7902 0H86.0055ZM87.3875 3.7201L89.2307 8.48018H85.5046L87.3875 3.7201ZM67.1754 0V14H77.3717V11.2198H69.9998V0H67.1754ZM52.4714 2.71982V5.70003H57.6201C58.4017 5.70003 59.0625 5.09986 59.0625 4.22024C59.0625 3.34063 58.4216 2.71982 57.6201 2.71982H52.4714ZM58.7619 14L55.396 8.38015H52.4714V14H49.647V0H57.6201C60.0238 0 61.867 1.74018 61.867 4.17976C61.867 6.35974 60.4651 7.9197 58.5019 8.28012L61.9672 14H58.7619ZM32.9201 0V14H44.0785V11.2198H35.7453V8.38015H43.1776V5.61985H35.7453V2.78015H44.0785V0H32.9201ZM26.1494 0L22.1227 10.1997L18.0753 0H15.0307L20.6596 14H23.484L29.1336 0H26.1486H26.1494ZM0.0865293 0L0.0666504 14H11.245V11.2198H2.91092V8.38015H10.3432V5.61985H2.91092V2.78015H11.2442V0H0.0865293Z"
                            fill="black"></path></svg>
              </span>
        </a>

        <a class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none"
           href="dashboardManager.jsp">
          <i class="bx bx-chevron-left bx-sm align-middle"></i>
        </a>
      </div>

      <div class="menu-inner-shadow"></div>

      <ul class="menu-inner py-1">
        <!-- Dashboard -->
        <li class="menu-item">
          <a class="menu-link" href="dashboardManager.jsp">
            <i class="menu-icon tf-icons bx bx-home-circle"></i>
            <div data-i18n="Analytics">Dashboard</div>
          </a>
        </li>

        <!-- Layouts -->
        <!-- Pages -->
        <li class="menu-header small text-uppercase"><span class="menu-header-text">Pages</span></li>
        <!-- Product List -->
        <li class="menu-item active">
          <a class="menu-link"
             href="${pageContext.request.contextPath}/ProductListManagerServlet?input=all">
            <i class='menu-icon tf-icons bx bxs-package'></i>
            <div data-i18n="Product List">Product List</div>
          </a>
        </li>
        <!-- User List -->
        <li class="menu-item">
          <a class="menu-link" href="${pageContext.request.contextPath}/UserListManagerServlet?role=all">
            <i class='menu-icon tf-icons bx bx-user'></i>
            <div data-i18n="User List">User List</div>
          </a>
        </li>
        <!-- Forms -->
        <li class="menu-item">
          <a class="menu-link menu-toggle" href="javascript:void(0);">
            <i class="menu-icon tf-icons bx bx-detail"></i>
            <div data-i18n="Marketing">Marketing</div>
          </a>
          <ul class="menu-sub">
            <li class="menu-item">
              <a class="menu-link" href="${pageContext.request.contextPath}/seasonCollectionEditServlet">
                <div data-i18n="Season Collection">Season Collection (Home Page)</div>
              </a>
            </li>
          </ul>
          <ul class="menu-sub">
            <li class="menu-item">
              <a class="menu-link" href="${pageContext.request.contextPath}/categoryEditServlet">
                <div data-i18n="Season Collection">Category (Home Page)</div>
              </a>
            </li>
          </ul>
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

        <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
          <ul class="navbar-nav flex-row align-items-center ms-auto">
            <!-- User -->
            <li class="nav-item navbar-dropdown dropdown-user dropdown">
              <a class="nav-link dropdown-toggle hide-arrow" data-bs-toggle="dropdown"
                 href="javascript:void(0);">
                <div class="avatar avatar-online">
                  <img alt class="w-px-40 h-auto rounded-circle"
                       src="a.template/assets/img/avatars/1.png"/>
                </div>
              </a>
              <ul class="dropdown-menu dropdown-menu-end">
                <li>
                  <a class="dropdown-item" href="#">
                    <div class="d-flex">
                      <div class="flex-shrink-0 me-3">
                        <div class="avatar avatar-online">
                          <img alt class="w-px-40 h-auto rounded-circle"
                               src="a.template/assets/img/avatars/1.png"/>
                        </div>
                      </div>
                      <div class="flex-grow-1">
                        <c:if test=" ${sessionScope.acc!= null}">
                          <span class="fw-semibold d-block">${sessionScope.acc.userName}</span>
                        </c:if>
                        <small class="text-muted">Admin</small>
                      </div>
                    </div>
                  </a>
                </li>
                <li>
                  <div class="dropdown-divider"></div>
                </li>
                <li>
                  <a class="dropdown-item" href="highUserAccount.jsp">
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
          <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Product List / </span> New Product
          </h4>

          <div class="row">
            <div class="col-md-12">
              <div class="col-md-12">
                <div class="card mb-4">
                  <h5 class="card-header">Create a Product</h5>
                  <!-- Account -->
                  <hr class="my-0"/>
                  <div class="card-body">
                    <form id="formAccountSettings" method="POST"  action="CreateNewProductServlet">
                      <div class="row">
                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="productName">Product Name</label>
                          <%if (pfe != null) {%>
                          it's already in the database with id = <%=pfe.getProductId()%>
                          <%}%>
                          <input class="form-control" id="productName" name="productName"
                                 placeholder="Please enter Product Name" type="text"/>
                        </div>

                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="categoryID">Category</label>
                          <select class="select2 form-select" id="categoryID" name="categoryID">
                            <option value="">Please chose Category</option>
                            <%for (Category category: cateList){%>
                            <option value=<%=category.getCategoryID()%>><%=category.getCategoryName()%></option>
                            <%}%>
                          </select>
                        </div>

                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="price">Price</label>
                          <input
                                  class="form-control"
                                  id="price"
                                  name="price"
                                  placeholder="Please enter Product Price"
                                  type="number"
                          />
                        </div>

                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="collectionID">Collection</label>
                          <select class="select2 form-select" id="collectionID" name="collectionID">
                            <option value="">Please chose Collection</option>
                            <%for (ProCollection pc: proCollectionsList ){%>
                            <option value="<%=pc.getCollectionID()%>"><%=pc.getCollectionName()%>
                            </option>
                            <%}%>
                          </select>
                        </div>

                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="description">Description</label>
                          <input
                                  class="form-control"
                                  id="description"
                                  name="description"
                                  placeholder="Please enter Product Price"
                                  type=text"
                          />
                        </div>
                      </div>
                      <div class="mt-2">
                        <button class="btn btn-dark me-2" type="submit">Create new Product
                        </button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
              <div class="col-md-12">
                <div class="card mb-4">
                  <h5 class="card-header">Create Image File</h5>
                  <!-- Account -->
                  <hr class="my-0"/>
                  <div class="card-body">
                    <form action="CreateNewImageFolderServlet" id="formAccountSettings" method="POST">
                      <div class="mb-3 col-md-6">
                        <label class="form-label" for="imageName">Image Name (Name / Color Ex: The Satin V-Neck Maxi Dress / Black) </label>
                        <input class="form-control" id="imageName" name="imageName" placeholder="Please enter Image Name"
                               type="text" list="product1"/>
                          <datalist id="product1">
                            <%for (ProductForEdit productForEdit: productForEditList){%>
                            <option value="<%=productForEdit.getProductName()%>">
                            <%}%>
                          </datalist>
                      </div>
                      <%if (picheckname != null) {%>
                      it's already in the database with id = <%=picheckname.getProduct_Img_ID()%>
                      <%}%>
                      <div class="mb-3 col-md-6">
                        <label class="form-label" for="thumbnail">Select thumbnail image</label>
                        <input class="form-control" id="thumbnail" name="thumbnail" type="text"/>
                      </div>
                      <div class="mb-3 col-md-6">
                        <label class="form-label" for="productImg1">Select product Image 1</label>
                        <input class="form-control" id="productImg1" name="productImg1" type="url"/>
                      </div>
                      <div class="mb-3 col-md-6">
                        <label class="form-label" for="productImg2">Select product Image 2</label>
                        <input class="form-control" id="productImg2" name="productImg2" type="url"/>
                      </div>
                      <div class="mb-3 col-md-6">
                        <label class="form-label" for="productImg3">Select product Image 3</label>
                        <input class="form-control" id="productImg3" name ="productImg3" type="url"/>
                      </div>
                      <div class="mt-2">
                        <button class="btn btn-dark me-2" type="submit">Create new Image File</button>
                      </div>
                    </form>
                  </div>
                </div>

              </div>
              <div class="col-md-12">
                <div class="card mb-4">
                  <h5 class="card-header">Create a variation</h5>
                  <!-- Account -->
                  <hr class="my-0"/>
                  <div class="card-body">
                    <form id="formAccountSettings" method="POST" action="CreateVariationServlet">
                      <div class="row">
                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="productID">Product ID</label>
                          <input class="form-control" id="productID" name="productID" list="product"
                                 placeholder="Please enter Product Name"/>
                          <datalist id="product">
                            <%for (ProductForEdit productForEdit: productForEditList){%>
                              <option value="<%=productForEdit.getProductId()%>"><%=productForEdit.getProductName()%></option>
                            <%}%>
                          </datalist>
                        </div>
                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="colorID">Color</label>
                          <select class="select2 form-select" id="colorID" name="colorID">
                            <option value="">Please chose Color</option>
                            <%for (Color color: colorList){%>
                            <option value=<%=color.getColor_ID()%>><%=color.getColor_Name()%></option>
                            <%}%>

                          </select>
                        </div>
                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="sizeID">Size</label>
                          <select class="select2 form-select" id="sizeID" name="sizeID">
                            <option value="">Please chose Size</option>
                            <%for (Size size: sizeList){%>
                            <option value=<%=size.getSize_ID()%>><%=size.getSize_Name()%></option>
                            <%}%>
                          </select>
                        </div>
                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="qty_in_stock">Quantity in Stock</label>
                          <input
                                  class="form-control"
                                  id="qty_in_stock"
                                  name="qty_in_stock"
                                  placeholder="Please enter Product Price"
                                  type="number"
                          />
                        </div>
                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="productimgID">Product Img ID</label>
                          <input class="form-control" id="productimgID" name="productimgID" list="productimg"
                                 placeholder="Please enter Product Img Name"/>
                          <datalist id="productimg">
                            <%for (ProductImg productImg: productImgList){%>
                            <option value=<%=productImg.getProduct_Img_ID()%>><%=productImg.getProduct_img_name()%></option>
                            <%}%>
                          </datalist>
                        </div>

                      </div>
                      <div class="mt-2">
                        <button class="btn btn-dark me-2" type="submit">Create new Variation
                        </button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>

          </div>


        </div>
        <!-- / Content -->

        <div class="content-backdrop fade"></div>
      </div>
      <!-- Content wrapper -->
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
<script src="a.template/assets/vendor/libs/jquery/jquery.js"></script>
<script src="a.template/assets/vendor/libs/popper/popper.js"></script>
<script src="a.template/assets/vendor/js/bootstrap.js"></script>
<script src="a.template/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

<script src="a.template/assets/vendor/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->
<script src="a.template/assets/vendor/libs/apex-charts/apexcharts.js"></script>

<!-- Main JS -->
<script src="a.template/assets/js/main.js"></script>

<!-- Page JS -->
<script src="a.template/assets/js/dashboards-analytics.js"></script>

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>


