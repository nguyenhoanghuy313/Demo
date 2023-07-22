package controller.admin;

import entity.ProductForEdit;
import entity.ProductImg;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;
import jakarta.servlet.http.Part;

import java.io.IOException;

@MultipartConfig
@WebServlet(name = "CreateNewImageFolderServlet", value = "/CreateNewImageFolderServlet")
public class CreateNewImageFolderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductImgDAO pid = new ProductImgDAO();
        ProductForEditDAO pfed = new ProductForEditDAO();
        UploadImageToFile uploadImageToFile = new UploadImageToFile();

        String imageName = request.getParameter("imageName").trim();
        ProductForEdit pfe = pfed.getProductByImageName(imageName);
        if (pfe != null){
            String colorName = request.getParameter("colorName").trim();
            String imageandcolorname = imageName + " (" + colorName + ")";
            ProductImg picheckname = pid.getProductFolder(imageandcolorname);
            if(picheckname == null){
                HttpSession session = request.getSession();
                String thumbnail = null;
                String productImg1 = null;
                String productImg2 = null;
                String productImg3 = null;

                Part file1 = request.getPart("thumbnail");
                Part file2 = request.getPart("productImg1");
                Part file3 = request.getPart("productImg2");
                Part file4 = request.getPart("productImg3");

                String uThumbnail = uploadImageToFile.uploadPath(file1, thumbnail, "productImg");
                String uProductImg1 = uploadImageToFile.uploadPath(file2, productImg1, "productImg");
                String uProductImg2 = uploadImageToFile.uploadPath(file3, productImg2, "productImg");
                String uProductImg3 = uploadImageToFile.uploadPath(file4, productImg3, "productImg");

                pid.createNewImageFolder(uThumbnail, uProductImg1,uProductImg2,uProductImg3,imageandcolorname);

                session.setAttribute("colorName", colorName);
                ProductImg newProductImg = pid.getProductFolder(imageandcolorname);
                session.setAttribute("newProductImg", newProductImg);
                System.out.println(newProductImg);
                request.setAttribute("alert2", "Create done");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
