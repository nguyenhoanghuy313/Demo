package controller;

import entity.CartItem;
import entity.ShopOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "order", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lấy thông tin từ yêu cầu
        int userID = Integer.parseInt(request.getParameter("UserID"));
        String xUserID = String.valueOf(userID);
        CartItemDAO cartItemDAO = new CartItemDAO();
        ProductDAO pd = new ProductDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        List<Product> cartItems = cartItemDAO.getUserItem(userID);

        // Tạo đơn hàng
        ShopOrderDAO shopOrderDAO = new ShopOrderDAO();
        ShopOrder shopOrder = new ShopOrder(userID, 0);
        shopOrderDAO.insertOrder(shopOrder);
        shopOrder = shopOrderDAO.getLatestOrder();
        int orderID = shopOrder.getShop_orderID();

        // Đặt hàng cho từng sản phẩm trong giỏ hàng

        for (Product cartItem : cartItems) {
            String productID = String.valueOf(cartItem.getProductID());
            String variationID = String.valueOf(cartItem.getVariationID());
            int quantity = cartItem.getQty_in_cart();  // Lấy số lượng từ giỏ hàng
            double price = cartItem.getPrice() * quantity;  // Tính toán giá trị dựa trên số lượng
            // Thực hiện đặt hàng cho sản phẩm
            orderDetailDAO.insert(orderID, productID, variationID,quantity,price);

            // Xóa sản phẩm đã đặt hàng khỏi giỏ hàng
            cartItemDAO.deleteCartItemByProdID(productID, variationID);
            pd.reduceQuantityOfProduct(productID,variationID,quantity);

        }
        request.setAttribute("ErrMessage", "Order Placed");
        response.sendRedirect(request.getHeader("referer"));
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        int xUserID = Integer.parseInt(request.getParameter("UserID"));
//        String OrderPid = request.getParameter("ProductID");
//        String OrderVid = request.getParameter("VariationID");
//        ShopOrderDAO sod = new ShopOrderDAO();
//        OrderDetailDAO odd = new OrderDetailDAO();
//        CartItemDAO cid = new CartItemDAO();
//        ShopOrder so = new ShopOrder(xUserID,0);
//        sod.insertOrder(so);
//        so = sod.getLatestOrder();
//        int maxOID = sod.getCurrentMaxOrderID();
//        odd.insert(maxOID, OrderPid);
//        cid.deleteCartItemByProdID(OrderPid,OrderVid);
////        List<Product> ci = cid.getUserItem(xUserID);
////        for (Product p : ci){
////            int xProductID = p.getProductID();
////            int xQuantity = p.getQty_in_stock();
////            double xPrice = p.getPrice();
////            double xTotalPrice = xPrice * xQuantity;
////        }
//    }
}
