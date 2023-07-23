package controller.sale;

import java.io.*;

import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;

import java.io.IOException;

@MultipartConfig
@WebServlet(name = "EditProductServlet", value = "/EditProductServlet")
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int input = Integer.parseInt(request.getParameter("input").trim());
        ProductForEditDAO pfed = new ProductForEditDAO();
        ProductImgDAO pid = new ProductImgDAO();
        VariationDAO vd = new VariationDAO();
        if(input == 1){
            String ProductID = request.getParameter("ProductID").trim();
            ProductForEdit productForEdit = pfed.getProductByID(ProductID);
            request.setAttribute("pfe", productForEdit);
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        } else if (input == 2) {
            String ImageID = request.getParameter("ImageID").trim();
            ProductImg productImg = pid.getProductFolderByID(ImageID);
            request.setAttribute("productImg", productImg );
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        }else if (input == 3) {
            String ProductID = request.getParameter("ProductID").trim();
            String ColorName = request.getParameter("ColorName").trim();
            String SizeName = request.getParameter("SizeName").trim();
            Variation variation = vd.getVariation(ProductID, ColorName, SizeName);
            request.setAttribute("variation", variation );
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String input = request.getParameter("input").trim();
        ProductForEditDAO pfed = new ProductForEditDAO();
        ProductImgDAO pid = new ProductImgDAO();
        VariationDAO vd = new VariationDAO();
        ColorDAO colorDAO = new ColorDAO();
        SizeDAO sizeDAO = new SizeDAO();

        if(input.equals("editProduct")){
            int productID = Integer.parseInt(request.getParameter("productID").trim());
            String productName = request.getParameter("productName").trim();
            int categoryID = Integer.parseInt(request.getParameter("categoryID").trim());
            double price = Double.parseDouble(request.getParameter("price").trim());
            int collectionID = Integer.parseInt(request.getParameter("collectionID").trim());
            String description = request.getParameter("description").trim();
            pfed.UpdateProductForEdit(categoryID, productName, price, collectionID, description, productID);
            response.sendRedirect("ProductListManagerServlet?input=all");
        }else if(input.equals("editImg")){
            int product_img_ID = Integer.parseInt(request.getParameter("product_img_ID").trim());
            String imageName = request.getParameter("imageName").trim();

            UploadImageToFile uploadImageToFile = new UploadImageToFile();
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


            pid.updateImgFolder(uThumbnail, uProductImg1, uProductImg2, uProductImg3, imageName, product_img_ID);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("ProductListManagerServlet?input=all");
        }else if(input.equals("editVariation1")){
            int variationID = Integer.parseInt(request.getParameter("variationID").trim());
            String productID = request.getParameter("productID").trim();

            String colorID = request.getParameter("colorID").trim();
            Color color = colorDAO.getColorsByID(colorID);

            String sizeID = request.getParameter("sizeID").trim();
            Size size = sizeDAO.getSizebyId(sizeID);
            Variation variationcheck = vd.getVariation(productID, color.getColor_Name(), size.getSize_Name());
            if(variationcheck == null){
                String qty_in_stock = request.getParameter("qty_in_stock").trim();
                String productimgID = request.getParameter("productimgID").trim();
                ProductImg productImg = pid.getProductFolderByID(productimgID);
                if(productImg.getProduct_img_name().contains(color.getColor_Name())){
                    vd.updateVariation(Integer.parseInt(productID), Integer.parseInt(colorID), Integer.parseInt(sizeID), Integer.parseInt(qty_in_stock), Integer.parseInt(productimgID), variationID);
                    response.sendRedirect("ProductListManagerServlet?input=all");
                }else{
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Must choose an image folder that matches the color');");
                    out.println("location='ProductListManagerServlet?input=all';");
                    out.println("</script>");
                }
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Variation is already in db');");
                out.println("location='ProductListManagerServlet?input=all';");
                out.println("</script>");
            }
        }else if (input.equals("editVariation2")){
            int variationID = Integer.parseInt(request.getParameter("variationID").trim());
            String productID = request.getParameter("productID").trim();
            String colorID = request.getParameter("colorID").trim();
            String sizeID = request.getParameter("sizeID").trim();
            String qty_in_stock = request.getParameter("qty_in_stock").trim();
            String productimgID = request.getParameter("productimgID").trim();
            vd.updateVariation(Integer.parseInt(productID), Integer.parseInt(colorID), Integer.parseInt(sizeID), Integer.parseInt(qty_in_stock), Integer.parseInt(productimgID), variationID);
            response.sendRedirect("ProductListManagerServlet?input=all");
        }
    }
}
