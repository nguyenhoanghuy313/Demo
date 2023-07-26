<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 09/07/2023
  Time: 10:40 CH
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
    Story story = (Story) request.getAttribute("story");
%>
<html>
<head>
    <title>Story Detail</title>
    <link href="header/pop-up/pop-up.css" rel="stylesheet">
    <link href="header/pop-up/policy.css" rel="stylesheet">
    <link rel="stylesheet" href="footer/ad-container.css">
    <link rel="stylesheet" href="footer/footer.css">
    <script src="https://md-block.verou.me/md-block.js" type="module"></script>
    <link rel="stylesheet" href="storyList/storyDetail.css">
</head>
<body>

<jsp:include page="header.jsp"/>
<section style="background-image: url('webImage/story/<%=story.getThumbnail()%>')" class="Story_Thumbnail_Tile">
    <h1>
        <%=story.getTitle()%>
    </h1>
</section>
<section class="Story_Description">
    <div class="line"></div>
    <md-block>
        <%=story.getDescription()%>
    </md-block>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
