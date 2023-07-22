package controller.user;
import java.io.*;

import entity.Cart;
import entity.User;
import entity.Variation;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;
//import entity.*;
@WebServlet(name = "addToCart", urlPatterns = {"/addToCart"})

public class AddToCart extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO pd = new ProductDAO();
        VariationDAO vd = new VariationDAO();
        String xProductID = request.getParameter("ProductID");
        String xColor_Name = request.getParameter("color_Name").trim();
        String xSize_Name = request.getParameter("size_name").trim();
        Variation xVariation = vd.getVariation(xProductID,xColor_Name,xSize_Name) ;
        User currUser = (User) request.getSession().getAttribute("acc");

        CartItemDAO cid = new CartItemDAO();
        if (currUser == null) {
            request.setAttribute("CartMessError", "Please Login to perform this action!");
            request.getRequestDispatcher("productDetail-servlet").forward(request,response);
        }else{
            CartDAO cd = new CartDAO();
            CartItemDAO cartItemDAO= new CartItemDAO();
            String buyerID = String.valueOf(currUser.getUserID());
            boolean isCartItemExist = cid.checkCartItemExist(xProductID, String.valueOf(xVariation.getVariationID()), buyerID);
            if(isCartItemExist){
                request.setAttribute("CartMessError", "Product has already been in cart!!!");
                request.getRequestDispatcher("productDetail-servlet").forward(request,response);
            }else {
                Product x = pd.getProductByProIDColNameSizName(xProductID,xColor_Name,xSize_Name);
                HttpSession session = request.getSession();
                Cart currentCart = (Cart) request.getSession().getAttribute("currentCart");
                Cart curCart;
                if(currentCart==null){
                    List<Product> cartItemList = cartItemDAO.getUserItem(currUser.getUserID());
                    if(!cartItemList.isEmpty()){
                        curCart = cd.getCart(buyerID);
                        session.setAttribute("currentCart", curCart);
                        cid.insert(String.valueOf(curCart.getCartID()), String.valueOf(x.getProductID()), String.valueOf(xVariation.getVariationID()));
                        request.setAttribute("CartMess", "Product has been added to cart!!!");
                        response.sendRedirect(request.getHeader("referer"));
                    }else{
                        cd.insertProductIntoCart(buyerID);
                        curCart = cd.getCart(buyerID);
                        session.setAttribute("currentCart", curCart);
                        cid.insert(String.valueOf(curCart.getCartID()), String.valueOf(x.getProductID()), String.valueOf(xVariation.getVariationID()));
                        response.sendRedirect(request.getHeader("referer"));
                    }
                }else{
                    curCart = cd.getCart(buyerID);
                    session.setAttribute("currentCart", curCart);
                    cid.insert(String.valueOf(curCart.getCartID()),String.valueOf(x.getProductID()), String.valueOf(xVariation.getVariationID()));
                    response.sendRedirect(request.getHeader("referer"));
                }
            }
        }
    }
}

