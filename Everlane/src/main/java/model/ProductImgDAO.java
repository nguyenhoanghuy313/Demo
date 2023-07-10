package model;

import java.sql.Date;

public class ProductImgDAO extends myDAO{
    public ProductImg getProductFolder(String ProductImgName){
        ProductImg pi = null;
        xSql = "select * from product_img where product_img_name like '"+ProductImgName+"';";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProduct_Img_ID;
            String xThumbnail, xProduct_Img_1, xProduct_Img_2, xProduct_Img_3, xProduct_img_name;
            while(rs.next()){
                xProduct_Img_ID = rs.getInt("product_img_ID");
                xThumbnail = rs.getString("thumbnail");
                xProduct_Img_1 = rs.getString("product_img_1");
                xProduct_Img_2 = rs.getString("product_img_2");
                xProduct_Img_3 = rs.getString("product_img_3");
                xProduct_img_name = rs.getString("product_img_name");
                pi = new ProductImg(xProduct_Img_ID, xThumbnail, xProduct_Img_1, xProduct_Img_2, xProduct_Img_3, xProduct_img_name);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return pi;
    }


    public void createNewImageFolder(String xThumbnail, String xProductImg1, String xProductImg2,String xProductImg3, String xProductImgName){
        try{
            xSql = "INSERT INTO product_img (thumbnail, product_img_1, product_img_2, product_img_3, product_img_name) value (?,?,?,?,?)";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xThumbnail);
            ps.setString(2, xProductImg1);
            ps.setString(3, xProductImg2);
            ps.setString(4, xProductImg3);
            ps.setString(5, xProductImgName);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Create new Story: " + e.getMessage());
        }
    }
}
