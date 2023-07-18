package controller;
import java.io.*;
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
//        PrintWriter pr = response.getWriter();
//        String xUserID = request.getParameter("UserID");
//        int xCart_itemID;
        ProductDAO pd = new ProductDAO();
        VariationDAO vd = new VariationDAO();
        String xProductID = request.getParameter("ProductID");
        String xColor_Name = request.getParameter("color_Name").trim();
        String xSize_Name = request.getParameter("size_name").trim();
        Variation xVariation = vd.getVariation(xProductID,xColor_Name,xSize_Name) ;
        User currUser = (User) request.getSession().getAttribute("acc");
        if (currUser == null) {
            request.setAttribute("CartMessError", "Please Login to perform this action!");
            request.getRequestDispatcher("productDetail-servlet").forward(request,response);
        }else{
            CartDAO cd = new CartDAO();
            CartItemDAO cid = new CartItemDAO();
            String buyerID = String.valueOf(currUser.getUserID());
            boolean isCartItemExist = cid.checkCartItemExist(xProductID, String.valueOf(xVariation.getVariationID()), buyerID);
            if(isCartItemExist){
                request.setAttribute("CartMessError", "Product has already been in cart!!!");
                request.getRequestDispatcher("productDetail-servlet").forward(request,response);
            }else {
                request.setAttribute("CartMess", "Product has been added to cart!!!");
                Product x = pd.getProductByProIDColNameSizName(xProductID,xColor_Name,xSize_Name);
                cid.insert(String.valueOf(x.getProductID()), String.valueOf(x.getVariationID()));
                cd.insertProductIntoCart(buyerID);
                cid.setCartID(String.valueOf(x.getProductID()), String.valueOf(x.getVariationID()), buyerID);
                cid.setQuantity(String.valueOf(x.getProductID()), String.valueOf(x.getVariationID()));
                request.getRequestDispatcher("productDetail-servlet").forward(request,response);
            }
        }
    }
}

