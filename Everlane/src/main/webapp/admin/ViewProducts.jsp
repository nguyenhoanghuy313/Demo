<%@page import="Entity.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Everlane Admin</title>
  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <script src="https://kit.fontawesome.com/d532025e5e.js" crossorigin="anonymous"></script>
  <!-- Embed CSS  here-->
  <link rel="stylesheet" type="text/css" href="css/admin.css">
</head>

<body>
<!--        <header class="main-header" style="max-width: 250px;">
            <a href="/home.html" class="logo">
                <img src="../images/1.jpg" style="width: 94%; padding-top: 10px;">
            </a>
        </header>-->
<div>
  <aside class="main-sidebar">
    <!--name-badge-->
    <!--                <div class="upgrade">
                        <div id="upgrade" class="max-upgrade" style="background-color: rgb(38, 201, 123);">
                            <div class="badge-text">anpthe173136@fpt.edu.vn</div>
                        </div>
                    </div>-->
    <!--side left menu-->
    <div class="slimScrollDiv" style="overflow: hidden; width: auto; height: 839px;">
      <!-- <section class="sidebar" style="margin-top: 15px; overflow: hidden; width: auto; height: 849px;"> -->
      <ul class="sidebar-menu tree" style="min-height: 500px;">
        <li class="" style="margin-top: 0px;">
          <a id="" style="right: 8%; margin-top: 15px; background-color: white;">
            <span style="color: rgb(133, 146, 158); margin-left: 15px; font-size: 12px;">MAIN MENU</span>
          </a>
        </li>
        <li class="" style="margin-top: 0px;">
          <a href="ViewProducts" id="">
            <i class="fa-solid fa-file"></i>
            <span>Manage Products</span>
          </a>
        </li>
        <li class="" style="margin-top: 0px;">
          <a href="AddProducts" id="">
            <i class="fa-sharp fa-solid fa-pen-to-square"></i>
            <span>Add Products</span>
          </a>
        </li>
        <li class="" style="margin-top: 0px;">
          <a href="ViewCustomers" id="getStarted">
            <i class="fa-solid fa-users"></i>
            <span>Customers</span>
          </a>
        </li>
        <li class="" style="margin-top: 0px;">
          <a href="ViewOrders" id="getStarted">
            <i class="fa-sharp fa-solid fa-shopping-bag"></i>
            <span>Orders</span>
          </a>
        </li>
      </ul>
      </section>
    </div>
  </aside>

  <aside class="content-wrapper">
    <!-- Tab links -->
    <div class="tab">
      <button class="tablinks" onclick="openCity(event, 'view')" id="defaultOpen">View Products</button>
      <!--<button class="tablinks" onclick="openCity(event, 'add')"><a href="AddProducts">Add Products</a></button>-->
    </div>

    <!-- Tab content -->
    <div id="view" class="tabcontent">
      <!--<h2>View Products</h2>-->
      <table class="table">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">NAME</th>
          <th scope="col">SIZE</th>
          <th scope="col">STOCK</th>
          <th scope="col">PRICE</th>
          <th colspan="2">ACTION</th>
        </tr>
        </thead>
        <tbody>
        <%
          int row = 0;
          ArrayList<Item> list = (ArrayList<Item>) request.getAttribute("ViewProducts");

          for(int i = 0; i < list.size(); i++) {

        %>
        <tr class="product-row" id="row1" style="vertical-align: middle;">
          <td><%= row + 1 %></td>
          <td class="prod">
            <div class="prod-name">
              <div class="prod-img">
                <img alt="Test product"
                     src="<%String[] imgs = list.get(i).getProduct().getProductImg();%>
                                                 <%= imgs[0]%>"
                     class="">
              </div>
              <div class="prod-link">
                <a href="SanPham" target="_top"><%=list.get(i).getProduct().getProductName()%></a>
              </div>
            </div>
            <!-- </div> -->
          </td>
          <td><%= list.get(i).getSize()%></td>
          <td><%= list.get(i).getQuantity()%></td>
          <td><%= list.get(i).getPrice()%></td>
          <td>
            <a href="EditProducts?productID=<%=list.get(i).getProduct().getProductID()%>&size=<%=list.get(i).getSize()%>">
              <i class="fa-sharp fa-solid fa-pen-to-square"></i>
            </a>
          </td>
          <td>
            <!--<button>-->
            <a href="DeleteProducts?productID=<%=list.get(i).getProduct().getProductID()%>&size=<%=list.get(i).getSize()%>">
              <i class="fa-solid fa-trash"></i>
            </a>
            <!--</button>-->
          </td>
        </tr>
        <%
            row++;
          }
        %>
        </tbody>
      </table>
    </div>
  </aside>
</div>
<script src="js/admin.js"></script>
<script>
  // Get the element with id="defaultOpen" and click on it
  document.getElementById("defaultOpen").click();
</script>
</body>
</html>
