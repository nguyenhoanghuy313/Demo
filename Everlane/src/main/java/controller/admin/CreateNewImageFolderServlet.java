package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;

@WebServlet(name = "CreateNewImageFolderServlet", value = "/CreateNewImageFolderServlet")
public class CreateNewImageFolderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductImgDAO pid = new ProductImgDAO();
        ProductForEditDAO pfed = new ProductForEditDAO();

        String imageName = request.getParameter("imageName").trim();
        ProductForEdit pfe = pfed.getProductByImageName(imageName);
        if (pfe != null){
            String colorName = request.getParameter("colorName").trim();
            String imageandcolorname = imageName + " (" + colorName + ")";
            ProductImg picheckname = pid.getProductFolder(imageandcolorname);
            if(picheckname == null){
                String thumbnail = request.getParameter("thumbnail").trim();
                String productImg1 = request.getParameter("productImg1").trim();
                String productImg2 = request.getParameter("productImg2").trim();
                String productImg3 = request.getParameter("productImg3").trim();
                pid.createNewImageFolder(thumbnail, productImg1,productImg2,productImg3,imageandcolorname);
                request.setAttribute("alert2", "Create done");
                request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
            }else{
                request.setAttribute("picheckname", picheckname);
                request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("alert21", "IMAGE NAME must be the same as a product name in product");
            request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
        }
    }
}
