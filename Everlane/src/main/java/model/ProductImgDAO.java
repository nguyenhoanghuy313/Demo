package model;

import entity.ProductImg;

import java.util.ArrayList;
import java.util.List;

public class ProductImgDAO extends myDAO{

    public List<ProductImg> getAllProductFolder(){
        List<ProductImg> t = new ArrayList<>();
        xSql = "select * from product_img";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProduct_Img_ID;
            String xThumbnail, xProduct_Img_1, xProduct_Img_2, xProduct_Img_3, xProduct_img_name;
            ProductImg pi;
            while(rs.next()){
                xProduct_Img_ID = rs.getInt("product_img_ID");
                xThumbnail = rs.getString("thumbnail");
                xProduct_Img_1 = rs.getString("product_img_1");
                xProduct_Img_2 = rs.getString("product_img_2");
                xProduct_Img_3 = rs.getString("product_img_3");
                xProduct_img_name = rs.getString("product_img_name");
                pi = new ProductImg(xProduct_Img_ID, xThumbnail, xProduct_Img_1, xProduct_Img_2, xProduct_Img_3, xProduct_img_name);
                t.add(pi);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (t);
    }


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

    public ProductImg getProductFolderByID(String ProductImgID){
        ProductImg pi = null;
        xSql = "select * from product_img where product_img_ID = '"+ProductImgID+"';";
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

    public ProductImg getProductFolderByColor(String ProductID, String ColorName){
        ProductImg pi = null;
        xSql = "select distinct pi.*\n" +
                "from variation v, product_img pi, product p , category c, color col, size s\n" +
                "where p.ProductID = '"+ProductID+"' and col.color_Name like '"+ColorName+"'\n" +
                "and p.ProductID = v.ProductID \n" +
                "and p.CategoryID = c.CategoryID\n" +
                "and v.product_img_ID = pi.product_img_ID\n" +
                "and v.color_ID = col.color_ID\n" +
                "and v.size_ID = s.size_ID;";
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

    public void updateImgFolder(String thumbnail, String product_img_1, String product_img_2, String product_img_3, String product_img_name, int product_img_ID){
        try{
            xSql = "update product_img set thumbnail = ?, product_img_1 = ?, product_img_2 = ?, product_img_3 = ?, product_img_name = ?  where product_img_ID = ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, thumbnail);
            ps.setString(2, product_img_1);
            ps.setString(3, product_img_2);
            ps.setString(4, product_img_3);
            ps.setString(5, product_img_name);
            ps.setInt(6, product_img_ID);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Update: " + e.getMessage());
        }
    }

    public void delete(String productImgID) {
        xSql = "delete from product_img where product_img_ID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, productImgID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
