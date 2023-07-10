package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductImg;
import model.ProductImgDAO;

import java.io.IOException;

@WebServlet(name = "CreateNewImageFolderServlet", value = "/CreateNewImageFolderServlet")
public class CreateNewImageFolderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductImgDAO pid = new ProductImgDAO();
        String imageName = request.getParameter("imageName").trim();
        ProductImg picheckname = pid.getProductFolder(imageName);
        if(picheckname == null){
            String thumbnail = request.getParameter("thumbnail").trim();
            String productImg1 = request.getParameter("productImg1").trim();
            String productImg2 = request.getParameter("productImg2").trim();
            String productImg3 = request.getParameter("productImg3").trim();
            pid.createNewImageFolder(thumbnail, productImg1,productImg2,productImg3,imageName);
            response.sendRedirect("addNewProduct.jsp");
        }else{
            request.setAttribute("picheckname", picheckname);
            request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
        }
    }
}
