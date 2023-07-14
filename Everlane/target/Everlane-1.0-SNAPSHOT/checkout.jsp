<%@ page import="model.User" %>
<%@ page import="model.CartItemDAO" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.CartItem" %>
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
    <link href="header/cart/cart1.css" rel="stylesheet">
    <link href="header/search/search1.css" rel="stylesheet">
    <link href="checkout/checkout1.css" rel="stylesheet">

    <!-- Favicon -->
    <link href="a.template/assets/img/favicon/favicon.png" rel="icon" type="image/x-icon"/>
</head>
<body>
<%
    User u = (User) request.getSession().getAttribute("currUser");
    int uID = 0;
    if (u != null) {
        uID = u.getUserID();
    }else {
        request.setAttribute("Message", "Please Login to perform this action!");
        request.getRequestDispatcher("login-servlet").forward(request,response);
        return;
    }
//    if(u == null){
//        response.setStatus(response.SC_MOVED_TEMPORARILY);
//        response.setHeader("Location", "login-servlet");
//        return;
//    }
//    int uID = u.getUserID();
    CartItemDAO cid = new CartItemDAO();
    List<Product> cartItemList = cid.getUserItem(uID);
%>
<jsp:include page="header.jsp"/>

<section class="Checkout_Container">
    <div class="Information_Container">
        <div class="Your_Email">
            <div class="Information_Title">
                <h1>1</h1>
                <h1>Your Email</h1>
            </div>
            <p><%=u.getEmail()%></p>
        </div>
        <div class="Shipping">
            <div class="Information_Title">
                <h1>2</h1>
                <h1>Shipping</h1>
            </div>
            <form action="Checkout" method="post">
                <input id="FirstName" name="FirstName" placeholder="Full Name*" type="text"><br>
                <input id="country" name="Country" placeholder="Country*" type="text"><br>
                <input id="Street" name="Street" placeholder="Street Address*" type="text"><br>
                <input id="address_line1" name="address_line1" placeholder="Apartment, Suite, Building (Optional)" type="text"><br>
                <input id="city" name="city" placeholder="City*" type="text"><br>
                <input id="region" name="region" placeholder="State/Province/Region" type="text"><br>
                <input id="postalcode" name="postalcode" placeholder="Postal Code*" type="text"><br>
                <input id="phone" name="phone" placeholder="Phone Number*" type="text"><br>
                <input type="submit" value="Save Address">
                <h2 style="color: red">${ErrMessage}</h2>
            </form>
        <%--            <form>--%>
<%--                <input id="FirstName" name="FullName" placeholder="Full Name*" type="text"><br>--%>
<%--                <input id="country" name="Country" placeholder="Country*" type="text"><br>--%>
<%--                <input id="address_line1" name="Address" placeholder="Street Address*" type="text"><br>--%>
<%--                <input id="apm" name="apartment" placeholder="Apartment, Suite, Building (Optional)" type="text"><br>--%>
<%--                <input id="city" name="City" placeholder="City*" type="text"><br>--%>
<%--                <input id="region" name="Region" placeholder="State/Province/Region" type="text"><br>--%>
<%--                <input id="postalCode" name="PCode" placeholder="Postal Code*" type="text"><br>--%>
<%--                <input id="phone" name="PhoneNumber" placeholder="Phone Number*" type="text"><br>--%>
<%--                <a href="Checkout?FullName=FirstName&Country=country&Address=address_line1&apartment=apm&City=city&Region=region&PCode=postalCode&PhoneNumber=phone">Save Address</a>--%>
<%--&lt;%&ndash;                <input type="submit" value="Save Address" href="Checkout?FullName=FirstName&Country=country&Address=address_line1&apartment=apm&City=city&Region=region&PCode=postalCode&PhoneNumber=phone">&ndash;%&gt;--%>
<%--            </form>--%>
            <div class="Shipping_Option">
                <h2>Select Shipping Option</h2>
                <form>
                    <div class="Shipping-Title">
                        <input id="shipping_option" name="shipping_option" type="checkbox" value="1">
                        <label for="shipping_option">
                            <div class="Shipping_Title_Container">
                                <p>Express Shipping</p>
                                <p>360.000</p>
                            </div>
                            <p>3 - 5 business days</p>
                        </label>
                    </div>

                    <input type="submit" value="Continue to Payment">
                </form>

            </div>
        </div>
        <div class="Your_Email">
            <div class="Information_Title">
                <h1>3</h1>
                <h1>Payment</h1>
            </div>
            <div class="Payment_Option">
                <h2>Select Payment Option</h2>
                <form>
                    <div class="Shipping-Title">
                        <input id="payment_option" name="payment_option" type="checkbox" value="1">
                        <label for="payment_option">
                            <div class="Shipping_Title_Container">
                                <p>Credit Card</p>
                                <div class="payment-img">
                                    <svg class="styles_payment-type-logo__icon__Fwkl_" height="24"
                                         viewBox="0 0 750 471" width="38" xmlns="http://www.w3.org/2000/svg">
                                        <g fill="none">
                                            <rect fill="#0E4595" height="471" rx="40" width="750"></rect>
                                            <path d="m278.197 334.228 33.361-195.763h53.36l-33.385 195.763zm246.111-191.54c-10.572-3.966-27.137-8.222-47.823-8.222-52.725 0-89.865 26.55-90.18 64.603-.298 28.13 26.513 43.822 46.753 53.186 20.77 9.594 27.752 15.714 27.654 24.283-.132 13.121-16.587 19.116-31.923 19.116-21.357 0-32.703-2.966-50.226-10.276l-6.876-3.111-7.49 43.824c12.464 5.464 35.51 10.198 59.438 10.443 56.09 0 92.501-26.246 92.916-66.882.2-22.268-14.016-39.216-44.8-53.188-18.65-9.055-30.072-15.099-29.951-24.268 0-8.137 9.667-16.839 30.556-16.839 17.45-.27 30.089 3.535 39.937 7.5l4.781 2.26 7.234-42.43m137.307-4.222h-41.231c-12.774 0-22.332 3.487-27.942 16.234l-79.245 179.404h56.032s9.161-24.123 11.233-29.418c6.124 0 60.554.084 68.337.084 1.596 6.853 6.491 29.334 6.491 29.334h49.513l-43.188-195.638zm-65.418 126.407c4.413-11.279 21.26-54.723 21.26-54.723-.316.522 4.38-11.334 7.075-18.684l3.606 16.879s10.217 46.728 12.352 56.528h-44.293z"
                                                  fill="#FFF"></path>
                                            <path d="m45.879 138.465-.682 4.074c21.091 5.106 39.93 12.494 56.422 21.686l47.346 169.691 56.455-.066 84.004-195.385h-56.522l-52.24 133.496-5.566-27.129a88.005 88.005 0 0 0-.823-2.49l-18.166-87.35c-3.23-12.396-12.598-16.095-24.187-16.527H45.879z"
                                                  fill="#fff"></path>
                                        </g>
                                    </svg>
                                    <svg class="styles_payment-type-logo__icon__Fwkl_" height="24" viewBox="0 0 750 471"
                                         width="38" xmlns="http://www.w3.org/2000/svg">
                                        <g fill="none">
                                            <rect fill="#2557D6" height="471" rx="40" width="750"></rect>
                                            <path d="M.003 221.185h36.024l8.123-19.51h18.185l8.101 19.51h70.88V206.27l6.327 14.98h36.796l6.327-15.202v15.138h176.151l-.082-32.026h3.408c2.386.083 3.083.302 3.083 4.226v27.8h91.106v-7.455c7.349 3.92 18.779 7.455 33.819 7.455h38.328l8.203-19.51h18.185l8.021 19.51h73.86v-18.532l11.186 18.532h59.187V98.678h-58.576v14.468l-8.202-14.468h-60.105v14.468l-7.532-14.468h-81.188c-13.59 0-25.536 1.889-35.186 7.153v-7.153h-56.026v7.153c-6.14-5.426-14.508-7.153-23.812-7.153H179.908l-13.734 31.641-14.104-31.641H87.6v14.468l-7.083-14.468H25.534L0 156.924v64.261h.003zm227.397-17.67h-21.615l-.08-68.794-30.573 68.793H156.62l-30.652-68.854v68.854H83.084l-8.101-19.592h-43.9L22.9 203.514H0l37.756-87.837h31.326l35.859 83.164v-83.164h34.412l27.593 59.587 25.347-59.587h35.104v87.837h.003zM67.777 165.692l-14.43-35.017-14.35 35.017h28.78zm245.642 37.821h-70.433v-87.837h70.433v18.291h-49.348V149.8h48.165v18.005H264.07v17.542h49.348v18.166zm99.256-64.18c0 14.004-9.386 21.24-14.856 23.412 4.613 1.748 8.553 4.838 10.43 7.397 2.976 4.369 3.49 8.271 3.49 16.116v17.255h-21.266l-.08-11.077c0-5.285.508-12.886-3.328-17.112-3.081-3.09-7.777-3.76-15.368-3.76h-22.633v31.95H327.98v-87.838h48.495c10.775 0 18.714.283 25.53 4.207 6.67 3.924 10.67 9.652 10.67 19.45zm-26.652 13.042c-2.898 1.752-6.324 1.81-10.43 1.81H349.98v-19.51h25.962c3.674 0 7.508.164 9.998 1.584 2.735 1.28 4.427 4.003 4.427 7.765 0 3.84-1.61 6.929-4.344 8.351zm60.466 51.138h-21.513v-87.837h21.513v87.837zm249.74 0H666.35l-39.964-65.927v65.927h-42.94l-8.204-19.592h-43.799l-7.96 19.592H498.81c-10.248 0-23.224-2.257-30.572-9.715-7.41-7.458-11.265-17.56-11.265-33.533 0-13.027 2.304-24.936 11.366-34.347 6.816-7.01 17.49-10.242 32.02-10.242h20.412v18.821h-19.984c-7.694 0-12.039 1.14-16.224 5.203-3.594 3.699-6.06 10.69-6.06 19.897 0 9.41 1.878 16.196 5.797 20.628 3.245 3.476 9.144 4.53 14.694 4.53h9.469l29.716-69.076h31.592l35.696 83.081v-83.08h32.103l37.062 61.174V115.68h21.596v87.834zm-128.159-37.82-14.591-35.017-14.51 35.017h29.1zm181.886 178.074c-5.121 7.458-15.101 11.239-28.611 11.239h-40.718v-18.84h40.553c4.022 0 6.837-.527 8.532-2.175a7.71 7.71 0 0 0 2.493-5.73c0-2.56-1.024-4.592-2.575-5.81-1.53-1.341-3.757-1.95-7.429-1.95-19.797-.67-44.495.609-44.495-27.194 0-12.743 8.125-26.157 30.25-26.157h41.998l.002-17.48h-39.022c-11.776 0-20.33 2.808-26.388 7.174v-7.175H626.83c-9.23 0-20.063 2.279-25.187 7.175v-7.175H498.578v7.175c-8.203-5.892-22.043-7.175-28.431-7.175h-67.983v7.175c-6.49-6.258-20.92-7.175-29.716-7.175h-76.085l-17.41 18.763-16.307-18.763H148.99v122.592h111.516l17.94-19.06 16.9 19.06 68.739.061v-28.838h6.757c9.12.14 19.878-.226 29.368-4.31v33.085h56.697v-31.952h2.735c3.49 0 3.834.143 3.834 3.616v28.333H635.71c10.935 0 22.365-2.787 28.695-7.845v7.845h54.632c11.369 0 22.471-1.587 30.918-5.651v-22.838zm-341.503-47.154c0 24.406-18.286 29.445-36.716 29.445H345.43v29.469h-40.98l-25.962-29.085-26.981 29.085H167.99v-87.859h84.8l25.941 28.799 26.819-28.799h67.371c16.732 0 35.532 4.613 35.532 28.945zm-167.625 40.434h-51.839v-17.481h46.289V301.64h-46.289v-15.973h52.86l23.062 25.604-24.083 25.776zm83.526 10.06-32.37-35.788 32.37-34.651v70.439zm47.873-39.066H344.98v-22.374h27.492c7.612 0 12.896 3.09 12.896 10.773 0 7.598-5.04 11.601-13.14 11.601zm142.744-40.373h70.369v18.17h-49.372v15.973h48.167v17.925h-48.167v17.481l49.372.08v18.23h-70.37v-87.859zm-27.054 47.03c4.693 1.724 8.53 4.816 10.329 7.375 2.977 4.29 3.408 8.293 3.493 16.037v17.417H480.57v-10.992c0-5.286.511-13.112-3.408-17.198-3.08-3.147-7.777-3.9-15.468-3.9h-22.533v32.09h-21.186v-87.859h48.678c10.674 0 18.448.47 25.369 4.146 6.654 4.004 10.839 9.488 10.839 19.51-.003 14.024-9.395 21.18-14.945 23.373zM476 303.59c-2.82 1.667-6.308 1.81-10.41 1.81h-25.614v-19.733h25.962c3.754 0 7.51.08 10.062 1.587 2.732 1.423 4.366 4.144 4.366 7.903 0 3.76-1.634 6.788-4.366 8.433zm190.336 5.597c4.106 4.23 6.306 9.572 6.306 18.614 0 18.9-11.858 27.723-33.122 27.723h-41.065v-18.84h40.9c4 0 6.836-.527 8.613-2.175 1.45-1.359 2.49-3.333 2.49-5.73 0-2.56-1.125-4.592-2.573-5.81-1.612-1.34-3.836-1.95-7.508-1.95-19.717-.67-44.41.61-44.41-27.193 0-12.744 8.04-26.158 30.144-26.158h42.269v18.7h-38.677c-3.834 0-6.327.143-8.447 1.587-2.31 1.422-3.166 3.534-3.166 6.32 0 3.315 1.96 5.57 4.613 6.545 2.224.77 4.613.996 8.205.996l11.35.305c11.446.278 19.303 2.249 24.078 7.066zM750 285.667h-38.427c-3.836 0-6.385.143-8.532 1.587-2.224 1.423-3.081 3.534-3.081 6.322 0 3.314 1.878 5.569 4.61 6.544 2.225.77 4.614.996 8.126.996l11.427.304c11.531.284 19.228 2.258 23.921 7.072.855.67 1.368 1.422 1.956 2.175v-25z"
                                                  fill="#FFF"></path>
                                        </g>
                                    </svg>
                                    <svg class="styles_payment-type-logo__icon__Fwkl_" height="24" viewBox="0 0 750 471"
                                         width="38" xmlns="http://www.w3.org/2000/svg">
                                        <g fill="none">
                                            <rect fill="#000" height="471" rx="40" width="750"></rect>
                                            <path d="M221.13 421.67v-24.85c0-9.53-5.8-15.74-15.32-15.74-5 0-10.35 1.66-14.08 7-2.9-4.56-7-7-13.25-7a14.07 14.07 0 0 0-12 5.8v-5h-7.87v39.76h7.87v-22.75c0-7 4.14-10.35 9.94-10.35s9.11 3.73 9.11 10.35v22.78h7.87v-22.78c0-7 4.14-10.35 9.94-10.35s9.11 3.73 9.11 10.35v22.78h8.68zm129.22-39.35h-14.5v-12H328v12h-8.28v7H328V408c0 9.11 3.31 14.5 13.25 14.5a23.17 23.17 0 0 0 10.75-2.9l-2.49-7a13.63 13.63 0 0 1-7.46 2.07c-4.14 0-6.21-2.49-6.21-6.63V389h14.5v-6.63l.01-.05zm73.72-1.24a12.39 12.39 0 0 0-10.77 5.8v-5h-7.87v39.76h7.87v-22.33c0-6.63 3.31-10.77 8.7-10.77a24.24 24.24 0 0 1 5.38.83l2.49-7.46a28 28 0 0 0-5.8-.83zm-111.41 4.14c-4.14-2.9-9.94-4.14-16.15-4.14-9.94 0-16.15 4.56-16.15 12.43 0 6.63 4.56 10.35 13.25 11.6l4.14.41c4.56.83 7.46 2.49 7.46 4.56 0 2.9-3.31 5-9.53 5a21.84 21.84 0 0 1-13.25-4.14l-4.14 6.21c5.8 4.14 12.84 5 17 5 11.6 0 17.81-5.38 17.81-12.84 0-7-5-10.35-13.67-11.6l-4.14-.41c-3.73-.41-7-1.66-7-4.14 0-2.9 3.31-5 7.87-5 5 0 9.94 2.07 12.43 3.31l4.07-6.25zm120.11 16.57c0 12 7.87 20.71 20.71 20.71 5.8 0 9.94-1.24 14.08-4.56l-4.14-6.21a16.74 16.74 0 0 1-10.35 3.73c-7 0-12.43-5.38-12.43-13.25S446 389 453.07 389a16.74 16.74 0 0 1 10.35 3.73l4.14-6.21c-4.14-3.31-8.28-4.56-14.08-4.56-12.43-.83-20.71 7.87-20.71 19.88v-.05zm-55.5-20.71c-11.6 0-19.47 8.28-19.47 20.71 0 12.43 8.28 20.71 20.29 20.71a25.33 25.33 0 0 0 16.15-5.38l-4.14-5.8a19.79 19.79 0 0 1-11.6 4.14c-5.38 0-11.18-3.31-12-10.35h29.41v-3.31c0-12.43-7.46-20.71-18.64-20.71v-.01zm-.41 7.46c5.8 0 9.94 3.73 10.35 9.94h-21.53c1.24-5.8 5-9.94 11.18-9.94zm-107.27 13.25v-19.88h-7.87v5c-2.9-3.73-7-5.8-12.84-5.8-11.18 0-19.47 8.7-19.47 20.71 0 12.01 8.28 20.71 19.47 20.71 5.8 0 9.94-2.07 12.84-5.8v5h7.87v-19.94zm-31.89 0c0-7.46 4.56-13.25 12.43-13.25 7.46 0 12 5.8 12 13.25 0 7.87-5 13.25-12 13.25-7.87.41-12.43-5.8-12.43-13.25zm306.08-20.71a12.39 12.39 0 0 0-10.77 5.8v-5h-7.87v39.76H533v-22.33c0-6.63 3.31-10.77 8.7-10.77a24.24 24.24 0 0 1 5.38.83l2.49-7.46a28 28 0 0 0-5.8-.83h.01zm-30.65 20.71v-19.88h-7.87v5c-2.9-3.73-7-5.8-12.84-5.8-11.18 0-19.47 8.7-19.47 20.71 0 12.01 8.28 20.71 19.47 20.71 5.8 0 9.94-2.07 12.84-5.8v5h7.87v-19.94zm-31.89 0c0-7.46 4.56-13.25 12.43-13.25 7.46 0 12 5.8 12 13.25 0 7.87-5 13.25-12 13.25-7.87.41-12.43-5.8-12.43-13.25zm111.83 0v-35.62h-7.87v20.71c-2.9-3.73-7-5.8-12.84-5.8-11.18 0-19.47 8.7-19.47 20.71 0 12.01 8.28 20.71 19.47 20.71 5.8 0 9.94-2.07 12.84-5.8v5h7.87v-19.91zm-31.89 0c0-7.46 4.56-13.25 12.43-13.25 7.46 0 12 5.8 12 13.25 0 7.87-5 13.25-12 13.25-7.88.42-12.44-5.79-12.44-13.25h.01z"
                                                  fill="#FFF"></path>
                                            <path d="M303.55 80.39h143.72v234.42H303.55z" fill="#FF5F00"></path>
                                            <path d="M318.05 197.6a149.5 149.5 0 0 1 56.74-117.21c-61.128-48.061-148.928-41.075-201.687 16.048-52.758 57.123-52.758 145.2 0 202.324 52.759 57.123 140.559 64.11 201.687 16.048a149.5 149.5 0 0 1-56.74-117.21z"
                                                  fill="#EB001B"></path>
                                            <path d="M616.26 197.6c.041 57.047-32.503 109.106-83.804 134.056-51.302 24.95-112.347 18.408-157.196-16.846a149.43 149.43 0 0 0 0-234.42c44.85-35.254 105.894-41.797 157.196-16.846 51.3 24.95 83.845 77.01 83.804 134.056z"
                                                  fill="#F79E1B"></path>
                                        </g>
                                    </svg>
                                </div>
                            </div>
                        </label>
                    </div>

                    <input type="submit" value="Continue to Payment">
                </form>

            </div>

        </div>

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
                    for (Product p: cartItemList) {
                        CartItem cartItemList2 = cid.getCartItem(String.valueOf(p.getProductID()) ,String.valueOf(p.getVariationID()));
                        totalValue += (p.getPrice() * cartItemList2.getQuantity());
                    }
                %>
                <h1>Cart (<%=cartSize%>)</h1>
            </div>
            <h1><%=totalValue%></h1>
        </div>
        <div class="Cart_List">
            <%
                int itemCount = cartItemList.size();  // Số lượng mục trong giỏ hàng
                int totalValue2 = 0;  // Tổng giá trị của các mục
                for (Product ci: cartItemList){
                    CartItem cartItemList2 = cid.getCartItem(String.valueOf(ci.getProductID()) ,String.valueOf(ci.getVariationID()));
                    totalValue2 += (ci.getPrice() * cartItemList2.getQuantity());
            %>
            <div class="Cart_Item">
                <img src="<%=ci.getThumbnail()%>" alt="">
                <div class="Cart_Item_Content">
                    <div class="Cart_Item_Header">
                        <h1><%=ci.getProductName()%></h1>
                        <p><%=ci.getSize_Name()%> | <%=ci.getColor_Name()%></p>
                        <a href="DeleteFromCart?ProductID=<%=ci.getProductID()%>&variationID=<%=ci.getVariationID()%>" class='bx bx-trash'></a>
                    </div>
                    <div class="Cart_Item_Price">
                        <div class="Price">
                            <p style="text-decoration: line-through">₫981800</p>
                            <p style="font-weight: bold; margin-left: 4px">₫<%=ci.getPrice()*cartItemList2.getQuantity()%>></p>
                        </div>
                        <form action="${pageContext.request.contextPath}/adjustQuantity" method="post">
                            <div class="Cart_Item_Amount_Change">
                                <button class='bx bx-minus' name="choice" value="minus"></button>
                                <p id="amount"><%= cartItemList2.getQuantity() %></p>
                                <button class='bx bx-plus' name="choice" value="plus"></button>
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
                    <td>₫<%=totalValue2%></td>
                </tr>
                <tr>
                    <td>Duties</td>
                    <td>???</td>
                </tr>
                <tr>
                    <td>Tax</td>
                    <td>???</td>
                </tr>
                <tr>
                    <td>Shipping</td>
                    <td>₫360,000</td>
                </tr>
                <tr style="font-weight: bold">
                    <td>Total</td>
                    <td>₫<%=totalValue2%></td>
                </tr>
            </table>
        </div>
        <div class="Place_Order_Container">
            <a href="${pageContext.request.contextPath}/order?UserID=<%=u.getUserID()%>">Place Order</a>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
