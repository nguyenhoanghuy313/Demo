package controller;

import entity.CartItem;
import entity.Address;
import entity.ShopOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "order", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Lấy thông tin từ yêu cầu
        HttpSession session = request.getSession();
        Address tempAddress = (Address)  request.getSession().getAttribute("temporaryAddress");
        if(tempAddress != null){
            String PayType = String.valueOf(request.getSession().getAttribute("PayType"));
            System.out.println(PayType);
            if(PayType.equals("null")){
                request.setAttribute("PayMessError", "You must chooser payment method");
                request.getRequestDispatcher("checkout.jsp").forward(request,response);
            } else if (PayType.equals("VNPAY")) {
                request.getRequestDispatcher("ajaxServlet").forward(request,response);
            } else{
                int userID = Integer.parseInt(request.getParameter("UserID"));
                CartItemDAO cartItemDAO = new CartItemDAO();
                ProductDAO pd = new ProductDAO();
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                AddressDAO ad = new AddressDAO();
                List<Product> cartItems = cartItemDAO.getUserItem(userID);

                // Tạo đơn hàng
                ShopOrderDAO shopOrderDAO = new ShopOrderDAO();
                ShopOrder shopOrder = new ShopOrder(userID, 0);
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
                    ad.setAddressIDtoShopOrder();
                    shopOrderDAO.setOrderTotal();
                }
                session.removeAttribute("temporaryAddress");
                session.removeAttribute("PayType");
                request.setAttribute("OrderMessage", "Order Placed");
                request.getRequestDispatcher("checkout.jsp").forward(request,response);
            }
        }else{
            request.setAttribute("OrderMessageError", "You must enter shipping information");
            request.getRequestDispatcher("checkout.jsp").forward(request,response);
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

    }
}
