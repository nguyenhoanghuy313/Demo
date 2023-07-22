<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 6/6/2023
  Time: 9:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="controller.*" %>
<%@ page import="entity.Story" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    List<Story> storyList = (List<Story>) request.getAttribute("storyList");
%>
<html>
<head>
    <title>everworld stories</title>

    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="header/header1.css">
    <link rel="stylesheet" href="storyList/story-list-title1.css">
    <link rel="stylesheet" href="storyList/story-list1.css">
    <link rel="stylesheet" href="footer/footer.css">
    <link rel="stylesheet" href="footer/ad-container.css">

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<section class="Stories_List_Container">
    <p style="font-size: 54px; font-family: 'Nunito Sans', sans-serif;">The Latest</p>
    <div class="Story_List">
        <%
            for (Story s : storyList) {
        %>
        <a href="StoryCusServlet?input=<%=s.getStory_ID()%>" class="Story">
            <div class="Story_img">
                <img src="webImage/story/<%=s.getThumbnail()%>">
            </div>
            <p><%=s.getTitle()%>
            </p>
        </a>
        <%
            }
        %>
    </div>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>
