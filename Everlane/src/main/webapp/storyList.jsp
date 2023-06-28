<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 6/6/2023
  Time: 9:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>everworld stories</title>

    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="header/header1.css">
    <link rel="stylesheet" href="storyList/story-list-title.css">
    <link rel="stylesheet" href="storyList/story-list.css">
    <link rel="stylesheet" href="storyList/scrolling-text-container.css">
    <link rel="stylesheet" href="footer/footer.css">
    <link rel="stylesheet" href="footer/ad-container.css">

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="a.template/assets/img/favicon/favicon.png"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="Stories_Title_Container">
    <div></div>
    <p style="font-size: 160px">everworld</p>
    <p style="font-size: 22px">We’re on a mission to clean up a dirty industry.</p>
    <p style="font-size: 22px">These are the people, stories, and ideas that will help us get there.</p>
</section>

<section class="Stories_List_Container">
    <p style="font-size: 54px">The Latest</p>
    <div class="Story_List">
        <div class="Story">
            <img src="https://cdn.builder.io/api/v1/image/assets%2F444142b2cae54a19aeb8b5ba245feffe%2Fdfd4c4b833da428186ffab1a304e5b1d">
            <p>The EverLane Team Celebrates AAPI Heritage Month</p>
        </div>
        <div class="Story">
            <img src="https://cdn.builder.io/api/v1/image/assets%2F444142b2cae54a19aeb8b5ba245feffe%2Fdfd4c4b833da428186ffab1a304e5b1d">
            <p>The EverLane Team Celebrates AAPI Heritage Month</p>
        </div>
        <div class="Story">
            <img src="https://cdn.builder.io/api/v1/image/assets%2F444142b2cae54a19aeb8b5ba245feffe%2Fdfd4c4b833da428186ffab1a304e5b1d">
            <p>The EverLane Team Celebrates AAPI Heritage Month</p>
        </div>
        <div class="Story">
            <img src="https://cdn.builder.io/api/v1/image/assets%2F444142b2cae54a19aeb8b5ba245feffe%2Fdfd4c4b833da428186ffab1a304e5b1d">
            <p>The EverLane Team Celebrates AAPI Heritage Month</p>
        </div>
        <div class="Story">
            <img src="https://cdn.builder.io/api/v1/image/assets%2F444142b2cae54a19aeb8b5ba245feffe%2Fdfd4c4b833da428186ffab1a304e5b1d">
            <p>The EverLane Team Celebrates AAPI Heritage Month</p>
        </div>
        <div class="Story">
            <img src="https://cdn.builder.io/api/v1/image/assets%2F444142b2cae54a19aeb8b5ba245feffe%2Fdfd4c4b833da428186ffab1a304e5b1d">
            <p>The EverLane Team Celebrates AAPI Heritage Month</p>
        </div>
    </div>
</section>

<section class="Scrolling_Text_Container">
    <div class="Scrolling_Text_Inner_Container">
        <p><- Keep It Cool <- Keep It Clean <- Do right by people <- Keep It Cool <- Keep It Clean <- Do right by people
            <- Keep It Cool <- Keep It Clean <- Do right by people</p>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
