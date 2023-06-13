<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 6/12/2023
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--    icon--%>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

    <%--    font--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@200&display=swap" rel="stylesheet">

<%--    css--%>
    <link rel="stylesheet" href="sidebar/sidebar.css">
</head>
<body>
    <div class="sidebar">
        <img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/Everlane_logo.png">
        <div class="sidebar-user-account">
            <h1>Lê Minh Đức</h1>
          <h1>Marketing</h1>
      </div>

        <div class="sidebar-menu">
            <ul>
                <li><a><i class='bx bx-category'></i><span>Overview</span></a></li>
                <li><a><i class='bx bx-line-chart'></i><span>Analysis</span></a></li>
                <li><a><i class='bx bx-package' ></i><span>Product List</span></a></li>
                <li><a><i class='bx bx-user' ></i><span>User List</span></a></li>
            </ul>
        </div>

        <a class="logout"><i class='bx bx-log-out' ></i><span>Log out</span></a>
    </div>
</body>
</html>
