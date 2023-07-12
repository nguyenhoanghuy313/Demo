package controller.admin;

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

        String productID = request.getParameter("productID").trim();

        String colorID = request.getParameter("colorID").trim();
        Color color = colorDAO.getColorsByID(colorID);

        String sizeID = request.getParameter("sizeID").trim();
        Size size = sizeDAO.getSizebyId(sizeID);
        Variation variationcheck = variationDAO.getVariation(productID, color.getColor_Name(), size.getSize_Name());
        if(variationcheck == null){
            String qty_in_stock = request.getParameter("qty_in_stock").trim();
            String productimgID = request.getParameter("productimgID").trim();
            ProductImg productImg = productImgDAO.getProductFolderByID(productimgID);
            if(productImg.getProduct_img_name().contains(color.getColor_Name())){
                variationDAO.createNewVariation(Integer.parseInt(productID),Integer.parseInt(colorID) ,Integer.parseInt(sizeID) ,Integer.parseInt(qty_in_stock) ,Integer.parseInt(productimgID) );
                request.setAttribute("alert3", "Create done");
                request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
            }else{
                request.setAttribute("alert31", "Must choose an image folder that matches the color");
                request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("alert31", "Variation is already in db");
            request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
        }
    }
}
