<%@ page import="entity.User" %>
<%@ page import="model.CartItemDAO" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.CartItem" %>
<%@ page import="entity.Country" %>
<%@ page import="model.CountryDAO" %>
<%@ page import="org.checkerframework.checker.units.qual.A" %>
<%@ page import="entity.Address" %>
<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 05/07/2023
  Time: 11:42 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>

    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link href="https://unpkg.com/boxicons@latest/css/boxicons.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@200&display=swap" rel="stylesheet">

    <link href="header/header1.css" rel="stylesheet"/>
    <link href="header/cart/cart2.css" rel="stylesheet">
    <link href="header/search/search1.css" rel="stylesheet">
    <link href="checkout/checkout.css" rel="stylesheet">

    <!-- Favicon -->
    <link href="webImage/other/icon/favicon.png" rel="icon" type="image/x-icon"/>
</head>
<body>
<%
    User u = (User) request.getSession().getAttribute("acc");
    int uID = 0;
    if (u != null) {
        uID = u.getUserID();
    } else {

    }

    CartItemDAO cid = new CartItemDAO();
    List<Product> cartItemList = cid.getUserItem(uID);
    CountryDAO ctd = new CountryDAO();
    List<Country> c = ctd.getAllCountry();

%>
<jsp:include page="header.jsp"/>

<section class="Checkout_Container">
    <div class="Information_Container">
        <div class="Your_Email">
            <div class="Information_Title">
                <h1>1</h1>
                <h1>Your Email</h1>
            </div>
            <p><%=u.getEmail()%>
            </p>
        </div>

        <%
            Address tempAddress = (Address) request.getSession().getAttribute("temporaryAddress");
            if (tempAddress == null) {
        %>
        <div class="Shipping">
            <div class="Information_Title">
                <h1>2</h1>
                <h1>Shipping</h1>
            </div>
            <form action="Checkout?mod=1" method="post">
                <select id="CountryID" name="CountryID" required>
                    <option value="">Choose a country
                    </option>
                    <% for (Country ct : c) { %>
                    <option value="<%= ct.getCountryID() %>"><%= ct.getCountryName() %>
                    </option>
                    <% } %>
                </select>
                <input id="recipient" name="recipient" placeholder="Full Name" type="text" required><br>
                <input id="Street" name="address_line" placeholder="Address Line" type="text" required><br>
                <input id="city" name="city" placeholder="City*" type="text" required><br>
                <input id="postalcode" name="postalcode" placeholder="Postal Code*" type="number" min="0" required><br>
                <input id="recipent_phone" name="recipent_phone" placeholder="Phone Number*" type="tel" required><br>
                <input type="submit" value="Save Address">
                <h1 style="color:red; font-family: 'Nunito Sans', sans-serif; font-size: 14px">${ShippMessError}</h1>
                <h1 style="color:green;font-family: 'Nunito Sans', sans-serif;  font-size: 14px">${ShippMess}</h1>
            </form>
        </div>
        <%} else {%>
        <div class="Your_Email">
            <div class="Information_Title">
                <h1>2</h1>
                <h1>Shipping</h1>
            </div>
            <a href="Checkout?input=1" style="color:black;font-family: 'Nunito Sans', sans-serif;">Change address</a>
            <p style="color:green;font-family: 'Nunito Sans', sans-serif;">${ShippMess}</p>
        </div>
        <%}%>

        <%
            String PayType = (String) request.getSession().getAttribute("PayType");
            if (PayType == null) {
        %>
        <div class="Your_Email">
            <div class="Information_Title">
                <h1>3</h1>
                <h1>Payment</h1>
            </div>
            <div class="Payment_Option">
                <h2>Select Payment Option</h2>
                <form action="Checkout?mod=2" method="post">
                    <div class="Shipping-Title">
                        <input style="margin-right: 10px" type="radio" id="payment_option2" name="payment_option"
                               value="Cash" required>
                        <label for="payment_option2">
                            <div class="Shipping_Title_Container">
                                <p>BY CASH</p>
                            </div>
                        </label>
                    </div>
                    <div class="Shipping-Title">
                        <input type="radio" id="payment_option1" name="payment_option" value="VNPAY">
                        <label for="payment_option1">
                            <div class="Shipping_Title_Container">
                                <svg style="width: 200px; height: 100px" xmlns="http://www.w3.org/2000/svg"
                                     version="1.1" id="Layer_1" x="0px" y="0px" viewBox="0 0 630.383 630.383"
                                     enable-background="new 0 0 630.383 630.383" xml:space="preserve">
<path fill-rule="evenodd" clip-rule="evenodd" fill="#005AAA"
      d="M535.045,301.644h-14.943l7.247-20.648L535.045,301.644z   M121.868,278.384l-15.106,15.106l-0.753,0.742l-8.378,8.389l-7.636,7.638l-0.744,0.752h-0.008l-7.08,7.07v0.01l-0.743,0.742  c-2.122,2.123-4.468,3.863-6.961,5.241c-1.262,0.695-2.552,1.29-3.872,1.798c-2.943,1.135-6.032,1.8-9.141,1.996  c-1.906,0.128-3.842,0.078-5.739-0.156c-4.106-0.491-8.135-1.8-11.811-3.93c-1.574-0.909-3.187-1.858-4.528-3.188  c-0.009,0-0.009,0-0.019-0.01l-28.97-28.968c-0.479-0.53-0.909-1.103-1.27-1.72c-0.92-1.556-1.448-3.359-1.448-5.291  c0-0.304,0.011-0.607,0.039-0.901c0.039-0.448,0.108-0.882,0.196-1.301l0.175-0.694c0.391-1.291,1.027-2.511,1.908-3.609  c0.234-0.279,0.478-0.554,0.742-0.829l0.489-0.482l29.302-29.312h0.011l17.755-17.752c3.96-3.961,10.267-4.139,14.421-0.511  L121.868,278.384z M460.226,265.957l14.143-0.053h17.174c9.445,0,17.174,7.729,17.174,17.174v2.115  c0,9.449-7.734,16.648-17.174,16.933l-7.835,0.241c6.298-2.896,8.934-10.211,8.148-16.798c-0.631-5.291-3.354-10.375-7.893-10.471  l-2.862-0.063v47.074c0,0.916,0.159,1.298,0.641,2.102c0.766,1.281,1.793,2.562,2.848,3.841c0.279,0.332,0.554,0.664,0.829,0.996  h-24.358c0.366-0.447,0.723-0.901,1.065-1.366c0.737-1.01,1.393-2.075,1.884-3.264c0.318-0.766,0.472-1.332,0.472-2.146v-50.567  C464.384,269.07,461.763,267.571,460.226,265.957z M577.193,299.678l-22.118-31.711l-2.25-2.062h21.804l14.774,23.973l1.19-0.969  l8.929-13.275c2.197-3.262,1.899-7.05-1.229-9.729h20.6l-23.814,33.769l-0.01,24.501l4.197,4.99h-25.592l3.604-4.915  C577.28,316.082,576.986,307.822,577.193,299.678z M517.139,265.904c1.764,2.424,2.289,3.277,2.486,5.431  c0.135,1.503,0.034,2.361-0.448,3.763l-16.451,47.704c-0.771,1.961-2.361,4.128-4.679,6.232h17.954  c-1.335-1.181-2.183-2.589-2.496-4.265c-0.279-1.531-0.27-2.105,0.063-3.578l3.648-11.331h20.894l3.257,8.73  c0.342,0.915,0.535,1.765,0.622,2.746c0.284,3.26-0.761,5.945-3.349,7.84h28.146c-2.761-2.459-5.296-5.034-6.727-8.159  l-20.547-55.114H517.139z"/>
                                    <path fill-rule="evenodd" clip-rule="evenodd" fill="#ED1C24"
                                          d="M285.557,276.327l23.091-13.242l4.553,8.36l-22.255,8.177  L285.557,276.327z M291.457,319.813v-26.717c0-2.043-0.248-3.59-0.743-4.645c-0.465-1.022-1.27-2.072-2.416-3.156v-0.371h11.522  c1.797,0,3.314-0.082,4.554-0.236c1.146-0.12,2.23-0.496,3.252-1.113h0.372v36.61c0,2.108,0.216,3.717,0.65,4.833  c0.432,1.27,1.331,2.401,2.694,3.391v0.372h-23.231v-0.372c1.457-1.239,2.386-2.478,2.787-3.719  C291.271,323.484,291.457,321.858,291.457,319.813z M328.157,265.624h19.665l16.504,47.32l13.998-39.196  c0.255-1.523,0.034-2.019-0.549-3.33c-0.776-1.59-1.605-3.137-2.376-4.727h14.687c-0.872,3.2-2.159,6.712-3.267,9.936  l-18.966,55.112c-5.175-1.032-10.948-3.081-15.41-7.311c-3.359-3.183-4.554-6.802-6.211-11.118l-15.858-41.998  C329.747,268.655,328.976,267.171,328.157,265.624z M243.975,314.077l-13.894-38.348c-0.34-0.901-0.681-1.802-1.021-2.698  c-0.527-1.301-0.976-2.323-1.347-3.069c-0.558-1.113-1.456-2.337-2.696-3.672v-0.188h21.605v0.376l-0.186,0.602  c0.248,1.706,0.729,3.802,1.44,6.284l13.576,39.46l11.31-34.598l0.562-1.658l0.582-2.005c0.089-0.405,0.147-0.699,0.177-0.887  c0.03-0.188,0.043-0.467,0.043-0.843c0-2.679-1.424-4.857-4.274-6.544v-0.188h17.005v0.236l-21.882,64.428l-7.387-2.556  c-1.272-0.465-2.416-0.93-3.439-1.393c-1.022-0.466-1.936-0.962-2.741-1.49c-1.455-0.961-2.781-2.3-3.972-4.02  C246.245,319.586,245.09,317.177,243.975,314.077z M406.667,287.8v33.812c0,1.373,0.318,1.619,1.161,2.719l3.874,5.049h-18.508  c1.046-1.591,2.086-3.182,3.127-4.772c0.805-1.313,1.152-1.759,1.152-3.281v-47.24l-5.204-8.462h19.183l31.056,39.999v-31.875  c0-1.523-0.304-2.019-1.108-3.33c-1.041-1.59-2.13-3.137-3.171-4.727h18.489l-3.855,5.05c-0.843,1.099-1.161,1.344-1.161,2.718  v57.537c-7.001-0.923-13.526-2.636-19.328-10.048L406.667,287.8z M39.073,320.316c0.092,0.091,0.184,0.181,0.275,0.269  c1.339,1.328,2.963,2.287,4.546,3.197c3.676,2.131,7.705,3.441,11.811,3.931c1.896,0.234,3.832,0.283,5.739,0.155  c3.109-0.195,6.199-0.861,9.141-1.994c1.32-0.508,2.611-1.105,3.871-1.798c2.494-1.38,4.839-3.12,6.962-5.241l0.743-0.743v-0.009  l7.079-7.069h0.009l3.579-3.59l8.38-8.377l4.8-4.814l0.753-0.742l30.436-30.434l12.554-13.603l0.754-0.742  c3.832-3.831,10.051-3.831,13.882,0l6.874,6.871h0.01l0.224,0.226c-3.04-0.145-6.11,0.949-8.408,3.2l-23.26,22.879l-0.235,0.284  l-40.477,39.83l-0.059-0.049L84.148,336.6l-0.997,1.055c-1.174,1.144-3.628,3.307-6.914,4.86c-1.486,0.704-3.001,1.212-4.496,1.526  c-0.89,0.175-1.8,0.293-2.689,0.332h-0.676c-2.365,0-4.692-0.528-6.921-1.545l-7.011-6.989l-2.855-2.866l-13.121-13.268  L39.073,320.316z M208.495,306.709l-33.713,34.22l-34.62,35.15c-1.105,1.105-2.259,2.101-3.48,3.001  c-0.04,0.04-0.089,0.078-0.138,0.117c-0.166,0.118-0.332,0.245-0.508,0.362c-0.02,0.02-0.039,0.028-0.058,0.049  c-5.222,3.736-11.626,5.926-18.529,5.926c-8.526,0-16.27-3.344-21.998-8.79l-0.792-0.704l-5.956-5.954l-16.728-16.738l-6.365-6.365  c0.909,0.146,1.828,0.214,2.747,0.214l0.773-0.009c1.066-0.04,2.132-0.166,3.177-0.381c1.711-0.353,3.441-0.94,5.142-1.74  c3.657-1.732,6.385-4.126,7.685-5.398c0.509-0.5,0.89-0.919,1.047-1.105l12.867-12.641l0.049,0.058l44.241-43.526l0.235-0.284  l21.491-21.149c2.894-2.829,7.412-3.2,10.696-0.94l5.094,5.093c0.011,0,0.011,0.01,0.011,0.01l27.63,27.63  c0.244,0.246,0.479,0.501,0.694,0.761C212.318,297.442,212.083,303.133,208.495,306.709z"/>
                                    <path fill="#009EDB"
                                          d="M117.312,282.943l-2.836,2.833l-4.976-5.007c-0.783-0.79-0.773-2.062,0.009-2.833  c0.793-0.781,2.054-0.781,2.836,0.01L117.312,282.943z M108.923,291.332l-2.836,2.824l-15.643-15.762  c-5.808-5.845-14.285-7.898-22.136-5.358c-1.046,0.342-2.179-0.231-2.523-1.277c-0.342-1.06,0.235-2.192,1.29-2.535  c9.288-3.012,19.34-0.578,26.214,6.346L108.923,291.332z M92.155,308.099l-2.836,2.836l-15.712-15.83  c-0.783-0.781-0.771-2.053,0.009-2.838c0.793-0.781,2.063-0.771,2.836,0.014L92.155,308.099z M100.534,299.721l-2.836,2.833  l-15.673-15.8c-3.822-3.86-10.081-3.884-13.933-0.058c-3.852,3.831-3.881,10.081-0.049,13.931l10.099,10.19  c0.783,0.782,0.783,2.052-0.009,2.836c-0.783,0.781-2.054,0.771-2.836-0.011L65.19,303.455c-5.378-5.426-5.349-14.22,0.078-19.607  c5.426-5.382,14.226-5.344,19.603,0.082L100.534,299.721z M74.546,324.026c-0.028,0.02-0.058,0.028-0.088,0.049  c-1.232,0.674-2.484,1.262-3.774,1.761l-13.904-14.02c-8.701-8.769-9.943-22.507-2.962-32.679c0.625-0.906,1.876-1.142,2.786-0.516  c0.919,0.626,1.144,1.879,0.518,2.795c-5.895,8.577-4.839,20.171,2.504,27.573L74.546,324.026z M101.708,270.077  c-0.782,0.771-2.063,0.752-2.845-0.029c-13.044-13.14-34.338-13.222-47.478-0.188c-13.131,13.044-13.219,34.338-0.176,47.477  l10.207,10.296c0.069,0.068,0.137,0.146,0.176,0.226c-0.049,0-0.097,0.008-0.146,0.008c-1.858,0.128-3.736,0.078-5.584-0.137  l-7.499-7.567c-14.597-14.704-14.509-38.551,0.196-53.151c14.715-14.596,38.552-14.499,53.149,0.207l0.028,0.029  C102.52,268.029,102.499,269.291,101.708,270.077z M438.571,348.279c0,1.155-0.906,2.054-2.072,2.054  c-1.209,0-2.159-0.899-2.159-2.054c0-1.158,0.949-2.099,2.159-2.099C437.665,346.179,438.571,347.121,438.571,348.279z   M365.67,352.474c-0.819,0-0.906-0.299-1.036-0.813c-0.13-1.198,0.651-1.714,1.947-3.083c0.342-0.343,0.559-0.643,0.559-0.985  c0-0.73-0.347-1.071-0.781-1.113c-0.429-0.043-1.291,0.085-1.899,0.384c-0.472,0.173-0.863,0.173-1.166-0.471l-0.169-0.342  c-0.26-0.64,0.169-0.858,0.602-1.07c1.036-0.427,1.725-0.516,2.544-0.516c1.686,0,3.069,0.945,3.026,3.129  c0,1.882-2.506,2.657-2.636,4.068c0,0.342-0.342,0.73-0.776,0.813H365.67z M498.226,347.892c-0.646-0.985-0.472-1.243,0.694-1.243  h0.429c1.166,0,1.6,0.258,2.159,1.285l1.817,3.469c0.429,0.857,0.299,1.07-0.651,1.07h-0.434c-0.819,0-1.422-0.342-1.855-1.027  L498.226,347.892z M322.938,351.448c-0.429,0.685-1.036,1.027-1.855,1.027h-0.429c-0.911,0-1.123-0.213-0.65-1.07l1.812-3.471  c0.564-1.025,0.997-1.284,2.164-1.284h0.429c1.166,0,1.34,0.258,0.689,1.243L322.938,351.448z M426.621,361.856  c0-3.042-1.903-4.54-5.657-4.54c-3.84,0-5.739,1.497-5.739,4.54v0.512c0,3.083,1.378,4.625,5.739,4.625  c4.361,0,5.657-1.542,5.657-4.625V361.856z M479.582,367.423c0,4.112,1.426,5.953,6.221,5.953c4.703,0,6.216-1.841,6.216-5.953  v-3.984c0-4.11-1.513-5.953-6.216-5.953c-4.708,0-6.221,1.843-6.221,5.953V367.423z M392.346,366.269h-5.05  c-3.845,0-5.614,1.026-5.614,3.723c0,2.443,1.595,3.642,4.833,3.642c3.971,0,5.831-0.901,5.831-4.114V366.269z M456.655,366.269  h-5.05c-3.84,0-5.614,1.026-5.614,3.723c0,2.443,1.6,3.642,4.838,3.642c3.971,0,5.826-0.901,5.826-4.114V366.269z M434.861,356.244  c0-1.07,0.217-1.372,1.383-1.372h0.472c1.079,0,1.383,0.303,1.383,1.372v18.376c0,1.155-0.304,1.367-1.383,1.367h-0.472  c-1.166,0-1.383-0.212-1.383-1.367V356.244z M500.731,356.244c0-1.07,0.304-1.372,1.383-1.372h0.472  c1.166,0,1.383,0.303,1.383,1.372v18.376c0,1.155-0.217,1.367-1.383,1.367h-0.472c-1.079,0-1.383-0.212-1.383-1.367V356.244z   M319.531,356.245c0-1.07,0.299-1.372,1.378-1.372h0.477c1.166,0,1.378,0.303,1.378,1.372v18.375c0,1.156-0.212,1.369-1.378,1.369  h-0.477c-1.079,0-1.378-0.212-1.378-1.369V356.245z M532.968,347.378c0-1.155,0.308-1.37,1.383-1.37h0.477  c1.166,0,1.383,0.215,1.383,1.37v9.979c1.291-1.928,3.537-2.911,6.775-2.911c5.31,0,7.984,2.483,7.984,7.494v12.68  c0,1.156-0.212,1.368-1.383,1.368h-0.516c-1.166,0-1.383-0.212-1.383-1.368v-12.294c0-3.383-1.508-4.842-5.522-4.842  c-4.144,0-5.956,1.631-5.956,5.572v11.564c0,1.156-0.217,1.368-1.383,1.368h-0.477c-1.075,0-1.383-0.212-1.383-1.368V347.378z   M512.686,374.62c0,1.155-0.217,1.368-1.383,1.368h-0.477c-1.079,0-1.378-0.213-1.378-1.368v-12.165c0-5.356,3.021-8.01,9.025-8.01  c6.341,0,8.977,2.825,8.977,8.01v12.165c0,1.155-0.217,1.368-1.383,1.368h-0.52c-1.161,0-1.378-0.213-1.378-1.368v-12.165  c0-3.428-1.34-4.971-5.696-4.971c-4.366,0-5.787,1.586-5.787,4.971V374.62z M310.6,370.08c-0.647,2.44-1.339,4.11-2.159,5.011  c-0.821,0.854-2.116,1.283-3.972,1.283c-1.855,0-3.194-0.429-4.014-1.283c-0.819-0.901-1.511-2.571-2.158-5.011l-5.267-20.476  c-0.303-1.156-0.086-1.454,1.121-1.454h0.476c1.208,0,1.597,0.297,1.9,1.454l5.265,20.858c0.562,2.357,1.168,2.913,2.677,2.913  c1.427,0,2.074-0.556,2.634-2.913l5.267-20.858c0.301-1.156,0.689-1.454,1.9-1.454h0.472c1.209,0,1.426,0.297,1.123,1.454  L310.6,370.08z M499.054,351.275c0,0.902-0.304,1.2-1.21,1.2h-2.592v14.993c0,5.953-3.151,8.948-9.449,8.948  c-6.52,0-9.454-2.995-9.454-8.948v-3.985c0-6.039,2.978-9.036,8.934-9.036c3.325,0,5.57,1.025,6.736,3.126v-5.099h-5.874  c-0.993,0-1.21-0.298-1.21-1.2v-0.342c0-0.899,0.217-1.2,1.21-1.2h5.874v-2.355c0-1.155,0.212-1.37,1.378-1.37h0.477  c1.079,0,1.378,0.215,1.378,1.37v2.355h2.592c0.906,0,1.21,0.301,1.21,1.2V351.275z M338.348,363.31  c0-5.611,3.065-8.864,9.628-8.864c1.426,0,2.679,0.169,3.802,0.557c1.031,0.342,1.291,0.643,1.291,1.627v0.43  c0,1.07-0.26,1.368-1.291,1.027c-1.079-0.386-2.337-0.601-3.715-0.601c-4.833,0-6.476,1.885-6.476,5.869v4.155  c0,4.328,1.942,5.868,6.736,5.868c1.465,0,2.761-0.172,3.884-0.555c0.993-0.345,1.296,0,1.296,1.071v0.426  c0,0.9-0.304,1.244-1.383,1.543c-1.209,0.386-2.544,0.555-4.014,0.555c-6.606,0-9.758-2.827-9.758-8.863V363.31z M395.579,369.65  c0,4.753-2.804,6.766-9.02,6.766c-4.963,0-8.115-1.969-8.115-6.425c0-4.453,2.935-6.252,8.808-6.252h5.093v-2.571  c0-2.912-1.985-3.898-5.657-3.898c-1.899,0-3.493,0.173-4.746,0.47c-1.036,0.215-1.383-0.043-1.383-1.07v-0.342  c0-0.986,0.26-1.243,1.296-1.458c1.422-0.297,2.978-0.426,4.66-0.426c6.043,0,9.064,2.225,9.064,6.723V369.65z M459.893,369.65  c0,4.753-2.804,6.766-9.021,6.766c-4.963,0-8.115-1.969-8.115-6.425c0-4.453,2.935-6.252,8.804-6.252h5.093v-2.571  c0-2.912-1.985-3.898-5.652-3.898c-1.899,0-3.498,0.173-4.751,0.47c-1.036,0.215-1.383-0.043-1.383-1.07v-0.342  c0-0.986,0.26-1.243,1.296-1.458c1.426-0.297,2.983-0.426,4.664-0.426c6.043,0,9.064,2.225,9.064,6.723V369.65z M359.801,368.41  c0,3.382,1.421,4.967,5.782,4.967c4.361,0,5.744-1.541,5.744-4.967v-12.166c0-1.07,0.299-1.372,1.378-1.372h0.477  c1.166,0,1.383,0.303,1.383,1.372v12.166c0,5.18-2.636,8.007-8.982,8.007c-5.999,0-9.021-2.654-9.021-8.007v-12.166  c0-1.07,0.304-1.372,1.383-1.372h0.472c1.166,0,1.383,0.303,1.383,1.372V368.41z M416.733,375.519  c-1.335,0.643-2.029,1.84-2.029,3.51c0,2.784,1.6,3.514,6.259,3.514c4.491,0,6.216-0.73,6.216-3.639  c0-2.701-1.595-3.171-6.216-3.171C419.108,375.732,417.682,375.647,416.733,375.519z M429.902,362.368  c0,4.97-2.983,7.454-8.939,7.454c-2.332,0-3.84,0.128-4.573,0.342c-0.694,0.215-1.036,0.64-1.036,1.285  c0,0.6,0.342,0.986,1.075,1.198c0.737,0.173,2.245,0.258,4.534,0.258c3.585,0,6.043,0.427,7.382,1.329  c1.383,0.858,2.072,2.355,2.072,4.54c0,2.441-0.689,4.111-2.115,5.098c-1.383,0.985-3.84,1.456-7.339,1.456  c-3.58,0-6.086-0.471-7.469-1.456c-1.34-0.942-2.029-2.444-2.029-4.497c0-2.058,0.819-3.598,2.419-4.585  c-1.166-0.555-1.773-1.497-1.773-2.825c0-1.884,1.166-2.998,3.498-3.341c-2.419-0.986-3.624-3.083-3.624-6.254v-0.512  c0-4.669,3.325-7.409,8.977-7.409c1.686,0,3.151,0.297,4.448,0.855l2.462-2.184c0.732-0.643,1.075-0.643,1.768,0l0.299,0.298  c0.694,0.643,0.694,0.985,0,1.67l-1.942,1.973c1.253,1.155,1.903,2.741,1.903,4.797V362.368z"/>
</svg>
                            </div>
                        </label>
                    </div>
                    <h1 style="color:green;font-family: 'Nunito Sans', sans-serif;  font-size: 14px">${PayMess}</h1>
                    <h1 style="color:red; font-family: 'Nunito Sans', sans-serif; font-size: 14px">${PayMessError}</h1>

                    <input type="submit" value="Continue to Payment">
                </form>

            </div>
        </div>
        <%} else {%>
        <div class="Your_Email">
            <div class="Information_Title">
                <h1>3</h1>
                <h1>Payment</h1>
            </div>
            <a href="Checkout?input=2" style="color:black;font-family: 'Nunito Sans', sans-serif;">Change Payment Type</a>
            <h1 style="color:green;font-family: 'Nunito Sans', sans-serif;  font-size: 14px">${PayMess}</h1>
        </div>
        <%}%>

    </div>
    <div class="Cart_List_Container">
        <div class="Cart_List_Header">
            <div class="Cart_List_Icon">
                <svg class="styles_order-summary__cart-icon__nNLr_" fill="none" height="20" viewBox="0 0 22 20"
                     width="22" xmlns="http://www.w3.org/2000/svg">
                    <path d="M1 0.25C0.585786 0.25 0.25 0.585786 0.25 1C0.25 1.41421 0.585786 1.75 1 1.75V0.25ZM5 1L5.72922 0.824692L5.59107 0.25H5V1ZM7.81149 13.9412L7.0772 14.0939C7.14956 14.4418 7.45614 14.6912 7.81149 14.6912V13.9412ZM18.7778 13.9412V14.6912C19.1245 14.6912 19.426 14.4536 19.507 14.1165L18.7778 13.9412ZM21 4.69748L21.7292 4.87279C21.7829 4.64939 21.7313 4.41371 21.5891 4.23325C21.4468 4.05279 21.2298 3.94748 21 3.94748V4.69748ZM1 1.75H5V0.25H1V1.75ZM7.81149 14.6912H18.7778V13.1912H7.81149V14.6912ZM19.507 14.1165L21.7292 4.87279L20.2708 4.52217L18.0486 13.7659L19.507 14.1165ZM4.27078 1.17531L5.15967 4.87279L6.61811 4.52217L5.72922 0.824692L4.27078 1.17531ZM5.1546 4.8502L7.0772 14.0939L8.54577 13.7885L6.62317 4.54475L5.1546 4.8502ZM21 3.94748H5.88889V5.44748H21V3.94748Z"
                          fill="black"></path>
                    <circle cx="8.64752" cy="18.0587" fill="black" r="1.76471"></circle>
                    <circle cx="16.8819" cy="18.0587" fill="black" r="1.76471"></circle>
                </svg>
                <%
                    int cartSize = cartItemList.size();
                    int totalValue = 0;  // Tổng giá trị của các mục
                    for (Product p : cartItemList) {
                        CartItem cartItemList2 = cid.getCartItem(String.valueOf(p.getProductID()), String.valueOf(p.getVariationID()));
                        if(p.getDiscount() != 0) {
                            totalValue += (p.getDiscount() * cartItemList2.getQuantity());
                        } else {
                            totalValue += (p.getPrice() * cartItemList2.getQuantity());
                        }
                    }
                    int totalCartItem = 0;
                    for (Product ci : cartItemList) {
                        CartItem cartItemList2 = cid.getCartItem(String.valueOf(ci.getProductID()), String.valueOf(ci.getVariationID()));
                        totalCartItem += (cartItemList2.getQuantity());
                    }
                %>

                <h1>Cart (<%=totalCartItem%>)</h1>
            </div>
            <h1>₫<%=totalValue%>
            </h1>
        </div>
        <div class="Cart_List">
            <%
                int itemCount = cartItemList.size();  // Số lượng mục trong giỏ hàng
                int totalValue2 = 0;  // Tổng giá trị của các mục
                for (Product ci : cartItemList) {
                    CartItem cartItemList2 = cid.getCartItem(String.valueOf(ci.getProductID()), String.valueOf(ci.getVariationID()));
                    if(ci.getDiscount() != 0) {
                        totalValue2 += (ci.getDiscount() * cartItemList2.getQuantity());
                    } else {
                        totalValue2 += (ci.getPrice() * cartItemList2.getQuantity());
                    }
            %>
            <div class="Cart_Item" style="margin-bottom: 20px;">
                <img src="webImage/productImg/<%=ci.getThumbnail()%>" alt="">
                <div class="Cart_Item_Content">
                    <div class="Cart_Item_Header">
                        <h1><%=ci.getProductName()%>
                        </h1>
                        <p><%=ci.getSize_Name()%> | <%=ci.getColor_Name()%>
                        </p>
                        <%            if (tempAddress == null && PayType == null) {
                        %>
                        <a href="DeleteFromCart?ProductID=<%=ci.getProductID()%>&variationID=<%=ci.getVariationID()%>"
                           class='bx bx-trash' style="color:black;"></a>
                        <%}%>
                    </div>
                    <div class="Cart_Item_Price">
                        <div class="Price">
                            <%
                            if(ci.getDiscount() != 0) {
                            %>
                            <p style="text-decoration: line-through"><%=ci.getPrice()%></p>
                            <p style="font-weight: bold; margin-left: 4px">
                                ₫<%=ci.getDiscount() * cartItemList2.getQuantity()%>
                            </p>
                            <%} else {%>
<%--                            <p style="text-decoration: line-through">ci.getPrice()</p>--%>
                            <p style="font-weight: bold; margin-left: 4px">
                                ₫<%=ci.getPrice() * cartItemList2.getQuantity()%>
                            </p>
                            <%}%>

                        </div>
                        <form action="${pageContext.request.contextPath}/adjustQuantity" method="post">
                            <div class="Cart_Item_Amount_Change">
                                <%            if (tempAddress == null && PayType == null) {
                                %>
                                <button class='bx bx-minus' name="choice" value="minus"></button>
                                <p id="amount"><%= cartItemList2.getQuantity() %>
                                </p>
                                <button class='bx bx-plus' name="choice" value="plus"></button>
                                <%}else{%>
                                <p id="amount"><%= cartItemList2.getQuantity() %>
                                </p>
                                <%}%>
                            </div>
                            <input type="hidden" name="ProductID" value="<%= cartItemList2.getProductID() %>">
                            <input type="hidden" name="VariationID" value="<%= cartItemList2.getVariationID() %>">
                            <input type="submit" style="display: none;">
                        </form>

                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <div class="Cart_Total_Info">
            <table>
                <tr>
                    <td>Subtotal</td>
                    <td>₫<%=totalValue2%>
                    </td>
                </tr>
                <tr>
                    <td>Shipping</td>
                    <td>₫360000</td>
                </tr>
                <tr style="font-weight: bold">
                    <td>Total</td>
                    <td>₫<%=totalValue2 + 360000%>
                    </td>
                </tr>
            </table>
        </div>
        <div class="Place_Order_Container">
            <h1 style="color:red; font-family: 'Nunito Sans', sans-serif; font-size: 14px">${OrderMessageError}</h1>
            <h1 style="color:green;font-family: 'Nunito Sans', sans-serif;  font-size: 14px">${OrderMessage}</h1>
            <h1 style="color:red; font-family: 'Nunito Sans', sans-serif; font-size: 14px">${PayMessError}</h1>
            <a class="button" href="${pageContext.request.contextPath}/order?UserID=<%=u.getUserID()%>">Place Order</a>
        </div>
    </div>


</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
