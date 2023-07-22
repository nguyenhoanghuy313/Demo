package controller.sale;

import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;

@WebServlet(name = "CreateVariationServlet", value = "/CreateVariationServlet")
public class CreateVariationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VariationDAO variationDAO = new VariationDAO();
        ColorDAO colorDAO = new ColorDAO();
        ProductImgDAO productImgDAO = new ProductImgDAO();
        SizeDAO sizeDAO = new SizeDAO();
        ProductForEditDAO productForEditDAO = new ProductForEditDAO();

        String productName = request.getParameter("productName").trim();
        ProductForEdit pfr = productForEditDAO.getProduct(productName);
        String productID = String.valueOf(pfr.getProductId());

        String colorName = request.getParameter("colorName").trim();
        Color color = colorDAO.getColorsByName(colorName);

        String sizeName = request.getParameter("sizeName").trim();
        Size size = sizeDAO.getSizebyName(sizeName);
        Variation variationcheck = variationDAO.getVariation(productID, colorName, sizeName);
        if(variationcheck == null){
            String qty_in_stock = request.getParameter("qty_in_stock").trim();
            String productimgName = request.getParameter("productimgName").trim();
            ProductImg productImg = productImgDAO.getProductFolder(productimgName);
            if(productImg.getProduct_img_name().contains(productName)){
                if(productImg.getProduct_img_name().contains(color.getColor_Name())){
                    variationDAO.createNewVariation(Integer.parseInt(productID),color.getColor_ID() ,size.getSize_ID() ,Integer.parseInt(qty_in_stock) ,productImg.getProduct_Img_ID());
                    request.setAttribute("alert3", "Create done");
                    request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
                }else{
                    request.setAttribute("alert31", "Must choose an image folder that matches the color");
                    request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("alert31", "Product IMG must be used for right Product");
                request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("alert31", "Variation is already in db");
            request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
        }
    }
}
