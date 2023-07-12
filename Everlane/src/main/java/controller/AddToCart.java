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
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException{
//        response.sendRedirect(request.getHeader("referer"));
//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String xUserID = request.getParameter("UserID");
        int xCart_itemID;
        String xProductID = request.getParameter("ProductID");
        String xVariationID = request.getParameter("VariationID");
        User currUser = (User) request.getSession().getAttribute("currUser");
        if(currUser == null){
            request.setAttribute("Message", "Please Login to perform this action!");
            request.getRequestDispatcher("login-servlet").forward(request,response);
            return;
        }
        CartItemDAO cid = new CartItemDAO();
        CartDAO cd = new CartDAO();
        String buyerID = String.valueOf(currUser.getUserID());
//        CartItem ci = cid.getCartItem(buyerID);
        boolean isCartItemExist = cid.checkCartItemExist(xProductID, xVariationID, buyerID);
        if(isCartItemExist){
            request.setAttribute("CartMess", "Product has already been in cart!!!");
//            request.getRequestDispatcher("productDetail.jsp").forward(request,response);
            response.sendRedirect(request.getHeader("referer"));
        }else {
            request.setAttribute("CartMess", "Product has been added to cart!!!");
            cid.insert(xProductID, String.valueOf(1), xVariationID);
            cd.insertProductIntoCart(buyerID);
            cid.setCartID(xProductID, xVariationID, buyerID);
            cid.setQuantity(xProductID, xVariationID);
//            request.getRequestDispatcher("productDetail.jsp").forward(request,response);
            response.sendRedirect(request.getHeader("referer"));
        }
//        response.sendRedirect(request.getHeader("referer"));

    }

}

