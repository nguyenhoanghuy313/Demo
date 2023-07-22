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

    <title>User Account</title>

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

    <link rel="stylesheet" href="bootstrap/assets/vendor/libs/apex-charts/apex-charts.css"/>

    <!-- Page CSS -->

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

                <div class="container-xxl flex-grow-1 container-p-y">
                    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Account Settings /</span> Account
                    </h4>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="card mb-4">
                                <h5 class="card-header">Profile Details</h5>
                                <!-- Account -->
                                <hr class="my-0"/>
                                <div class="card-body">
                                    <form action="user-account-detail-servlet?input=username" method="POST">
                                        <div class="row">
                                            <div class="mb-3 col-md-6">
                                                <label for="username" class="form-label">User Name</label>
                                                <input
                                                        type="text"
                                                        class="form-control"
                                                        id="username"
                                                        name="username"
                                                        value="${u.getUserName()}"
                                                        placeholder="Please enter your User Name"
                                                        required
                                                />
                                            </div>
                                            <input type="hidden" class="form-control" name="password"
                                                   value="${u.getPassword()}"/>
                                            <input class="form-control" type="hidden" name="email"
                                                   value="${u.getEmail()}"/>
                                            <input class="form-control" type="hidden" name="firstName"
                                                   value="${u.getFirstName()}"/>
                                            <input class="form-control" type="hidden" name="lastName"
                                                   value="${u.getLastName()}"/>
                                            <input class="form-control" type="hidden" name="dob" value="${u.getDob()}"/>
                                            <input class="form-control" type="hidden" name="gender"
                                                   value="${u.getSex()}"/>
                                            <input type="hidden" class="form-control" name="role"
                                                   value="${u.getRole()}"/>
                                            <input type="hidden" name="phoneNumber" class="form-control"
                                                   value="${u.getPhone()}"/>
                                            <input type="hidden" name="UserID" class="form-control"
                                                   value="${u.getUserID()}"/>

                                            <div style="color: red">
                                                ${error1}
                                            </div>
                                            <div style="color: green">
                                                ${success1}
                                            </div>
                                        </div>
                                        <div class="mt-2">
                                            <button type="submit" class="btn btn-dark me-2">Change username
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <hr class="my-0"/>

                                <div class="card-body">
                                    <div class="mb-3 col-md-6">
                                        <label for="email" class="form-label">E-mail</label>
                                        <input
                                                class="form-control"
                                                type="email"
                                                id="email"
                                                name="email"
                                                value="${u.getEmail()}"
                                                required
                                                placeholder="Please enter your Email"
                                                readonly
                                        />
                                    </div>
                                </div>
                                <hr class="my-0"/>

                                <div class="card-body">
                                    <form action="user-account-detail-servlet?input=detail" method="POST">
                                        <div class="row">
                                            <input type="hidden" class="form-control" name="username"
                                                   value="${u.getUserName()}"/>
                                            <input type="hidden" class="form-control" id="password" name="password"
                                                   value="${u.getPassword()}"/>
                                            <input class="form-control" type="hidden" name="email"
                                                   value="${u.getEmail()}"/>
                                            <div class="mb-3 col-md-6">
                                                <label for="firstName" class="form-label">First Name</label>
                                                <input class="form-control" type="text" id="firstName" name="firstName"
                                                       value="${u.getFirstName()}" placeholder="Enter your First Name"
                                                       required
                                                       pattern="([A-Z][a-zA-Z]*)"
                                                       title="First letter must be uppercase, no number and white space"
                                                />
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label for="lastName" class="form-label">Last Name</label>
                                                <input class="form-control" type="text" name="lastName" id="lastName"
                                                       value="${u.getLastName()}" placeholder="Enter your last name"
                                                       required
                                                       pattern="([A-Z][a-zA-Z]*)"
                                                       title="First letter must be uppercase, no number and white space"
                                                />
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label for="dob" class="form-label">Date Of Birth</label>
                                                <input type="date" class="form-control" id="dob" name="dob"
                                                       value="${u.getDob()}" required/>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label for="gender" class="form-label">Gender</label>
                                                <select id="gender" name="gender" class="select2 form-select" required>
                                                    <c:if test="${u.getSex() == null}">
                                                        <option value="0">Choose Your Gender</option>
                                                    </c:if>
                                                    <c:if test="${u.getSex() != null}">
                                                        <option value="1" ${u.getSex() != "1" ? "" : "selected"}>Male
                                                        </option>
                                                        <option value="2" ${u.getSex() != "2" ? "" : "selected"}>
                                                            Female
                                                        </option>
                                                        <option value="3" ${u.getSex() != "3" ? "" : "selected"}>
                                                            Other
                                                        </option>
                                                    </c:if>

                                                </select>
                                            </div>
                                            <input type="hidden" class="form-control" id="role" name="role"
                                                   value="${u.getRole()}"/>
                                            <div class="mb-3 col-md-6">
                                                <label class="form-label" for="phoneNumber">Phone Number</label>
                                                <div class="input-group input-group-merge">
                                                    <input
                                                            type="tel"
                                                            id="phoneNumber"
                                                            name="phoneNumber"
                                                            class="form-control"
                                                            placeholder="Enter your phone number"
                                                            value="${u.getPhone()}"
                                                            required
                                                    />
                                                </div>
                                            </div>
                                            <input
                                                    type="hidden"
                                                    id="userID"
                                                    name="UserID"
                                                    class="form-control"
                                                    value=${u.getUserID()}
                                            />

                                            <div style="color: red">
                                                ${error3}
                                            </div>
                                            <div style="color: green">
                                                ${success3}
                                            </div>
                                        </div>
                                        <div class="mt-2">
                                            <button type="submit" class="btn btn-dark me-2">Save changes
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <hr class="my-0"/>



                                <!-- /Account -->
                            </div>
                            <div class="card">
                                <h5 class="card-header">Delete Account</h5>
                                <div class="card-body">
                                    <div class="mb-3 col-12 mb-0">
                                        <div class="alert alert-warning">
                                            <h6 class="alert-heading fw-bold mb-1">Are you sure you want to delete your
                                                account?</h6>
                                            <p class="mb-0">Once you delete your account, there is no going back. Please
                                                be certain.</p>
                                        </div>
                                    </div>
                                    <form action="deleteAccountSevlet" id="formAccountDeactivation" method="post">
                                        <div class="form-check mb-3">
                                            <input
                                                    class="form-check-input"
                                                    type="checkbox"
                                                    name="accountActivation"
                                                    id="accountActivation"
                                            />
                                            <label class="form-check-label" for="accountActivation"
                                            >I confirm my account deactivation</label
                                            >
                                        </div>
                                        <div style="color: red">
                                            ${errorDelete}
                                        </div>
                                        <button type="submit" class="btn btn-danger deactivate-account">Deactivate
                                            Account
                                        </button>
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
<%--<script>--%>
<%--    document.getElementById("deleteAccount").addEventListener("click", function(event) {--%>
<%--        if (document.getElementById("accountActivation").checked) {--%>
<%--            event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết--%>
<%--            window.location.href = "user-account-detail-servlet?mod=2"; // Chuyển hướng đến servlet--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>
<script>
    var file = document.getElementById("upload");
    var image = document.getElementById("uploadedAvatar");
    file.addEventListener("change", (e) => {
        image.src = URL.createObjectURL(e.target.files[0])
    })
    // file.addEventListener("change", function() {
    //     if (this.files[0]) {
    //         var fileReader = new FileReader();
    //         fileReader.onload = function(e) {
    //             image.setAttribute("src", e.target.result);
    //         };
    //         fileReader.readAsDataURL(this.files[0]);
    //     }
    // });
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
