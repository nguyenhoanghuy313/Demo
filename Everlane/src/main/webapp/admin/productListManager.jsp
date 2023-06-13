<%--
  Created by IntelliJ IDEA.
  User: minileisduk
  Date: 6/12/2023
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="sidebar/sidebar.css">
    <link rel="stylesheet" href="product-list-manager/product-list-header-manager.css">

</head>
<body>
    <jsp:include page="sidebar.jsp" />
    <section class="list_container">
        <h1>Products</h1>
        <div class="list_option_container">
            <a class="new_product"><i class='bx bx-plus' ></i><span>New Product</span></a>
            <div class="list_option_inner_container">
                <div class="search_container">
                    <input type="search" id="search" name="q">
                    <button><i class='bx bx-search' ></i></button>
                </div>
                <select id="Sort-by" name="Sort by">
                    <option value="">Sort by Latest</option>
                    <option value="">Sort by Newest</option>
                    <option value="">Sort by Price: Up</option>
                    <option value="">Sort by Price: Down</option>
                </select>
                <select id="category" name="Category">
                    <option value="">Category</option>
                </select>
            </div>
        </div>
        <div class="item_list_container">
            <table>
                <tr class="item_list_tile">
                    <th>PRODUCT ID</th>
                    <th>PRODUCT IMAGE</th>
                    <th>PRODUCT NAME</th>
                    <th>CATEGORY</th>
                    <th>COLOR</th>
                    <th>PRICE</th>
                    <th>AMOUNT</th>
                    <th>RATE</th>
                    <th>ACTION</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
                <tr class="item">
                    <th>1</th>
                    <th><img src="https://media.everlane.com/image/upload/c_fill,w_640,ar_4:5,q_auto,dpr_1.0,f_auto,fl_progressive:steep/i/aabd7304_283f"></th>
                    <th>The Poplin Tiered Dress</th>
                    <th>Dresses</th>
                    <th>Brown</th>
                    <th>₫4369000</th>
                    <th>60</th>
                    <th>sao sao sao</th>
                    <th>...</th>
                </tr>
            </table>
        </div>
    </section>
</body>
</html>
