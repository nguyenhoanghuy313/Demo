package controller;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;
import entity.*;
@WebServlet(name = "addToCart", urlPatterns = {"/addToCart"})

public class AddToCart extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int xUserID;
        int xCart_itemID;
        int xCartID;
        int xProductID;
        int xQuantity;
        User currUser = (User) request.getSession().getAttribute("currUser");
        if(currUser == null){
            request.getRequestDispatcher("login-servlet").forward(request,response);
            return;
        }
        CartItemDAO cid = new CartItemDAO();
        CartDAO cd = new CartDAO();
//        xUserID = Integer.parseInt(request.getParameter("UserID"));
//        xCart_itemID = Integer.parseInt(request.getParameter("cart_itemID"));
//        xQuantity = Integer.parseInt(request.getParameter("Quantity"));
        xProductID = Integer.parseInt(request.getParameter("ProductID"));
        int buyerID = currUser.getUserID();
        cd.insertProductIntoCart(buyerID);
        cid.insert(xProductID);

        request.getRequestDispatcher("home-servlet").forward(request,response);

    }
}

