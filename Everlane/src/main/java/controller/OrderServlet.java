package controller;

import entity.CartItem;
import entity.ShopOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartItemDAO;
import model.OrderDetailDAO;
import model.Product;
import model.ShopOrderDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = {"/buy"})
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int xUserID = Integer.parseInt(request.getParameter("UserID"));
        ShopOrderDAO sod = new ShopOrderDAO();
        CartItemDAO cid = new CartItemDAO();
        ShopOrder so = new ShopOrder(xUserID,0);
        sod.insert(so);
        so = sod.getLatestOrder();
        OrderDetailDAO odd = new OrderDetailDAO();
        List<Product> ci = cid.getUserItem(xUserID);
        for (Product p : ci){
            int xProductID = p.getProductID();
            int xQuantity = p.getQty_in_stock();
            double xPrice = p.getPrice();
            double xTotalPrice = xPrice * xQuantity;
        }
    }
}
