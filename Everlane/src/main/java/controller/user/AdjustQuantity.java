package controller.user;
import java.io.*;

import entity.Cart;
import entity.CartItem;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.CartDAO;
import model.CartItemDAO;
import entity.User;

@WebServlet(name = "adjustQuantity", urlPatterns = {"/adjustQuantity"})

public class AdjustQuantity extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        CartItemDAO cid = new CartItemDAO();
        String xProductID = request.getParameter("ProductID");
        String xVariationID = request.getParameter("VariationID");
        String choice = request.getParameter("choice");
        CartItemDAO cartItemDAO= new CartItemDAO();
        CartDAO cd = new CartDAO();
        Cart curCart;
        Cart currentCart = (Cart) request.getSession().getAttribute("currentCart");
        User currUser = (User) request.getSession().getAttribute("acc");
        String buyerID = String.valueOf(currUser.getUserID());
        if(currentCart==null){
            curCart = cd.getCart(buyerID);
            session.setAttribute("currentCart", curCart);
            if(choice.equals("plus")){
                cid.plusQuantity(xProductID,xVariationID, String.valueOf(curCart.getCartID()));
                response.sendRedirect(request.getHeader("referer"));
            }else {
                CartItem cartItem = cid.getCartQuantity(xProductID, xVariationID, String.valueOf(curCart.getCartID()));
                int quan = cartItem.getQuantity();
                if(quan>1){
                    cid.minusQuantity(xProductID,xVariationID, String.valueOf(curCart.getCartID()));
                }
                response.sendRedirect(request.getHeader("referer"));
            }
        }else{
            if(choice.equals("plus")){
                cid.plusQuantity(xProductID,xVariationID, String.valueOf(currentCart.getCartID()));
                response.sendRedirect(request.getHeader("referer"));
            }else {
                CartItem cartItem = cid.getCartQuantity(xProductID, xVariationID, String.valueOf(currentCart.getCartID()));
                int quan = cartItem.getQuantity();
                if(quan>1){
                    cid.minusQuantity(xProductID,xVariationID, String.valueOf(currentCart.getCartID()));
                }
                response.sendRedirect(request.getHeader("referer"));
            }
        }

    }

}
