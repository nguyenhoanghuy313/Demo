package model;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ProductForEditDAO extends myDAO{
    public  List<ProductForEdit> getAllProductFor

    public ProductForEdit getProduct(String ProductName){
        xSql = "select * from product where ProductName like '"+ProductName+"';";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProductId, xCategoryId;
            String xProductName;
            double xPrice;
            int xCollectionId;
            String xDescription;
            ProductForEdit p;
            while(rs.next()){
                xProductId = rs.getInt("ProductID");
                xCategoryId = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xPrice = rs.getDouble("Price");
                xCollectionId = rs.getInt("CollectionID");
                xDescription = rs.getString("description");
                p = new ProductForEdit(xProductId, xCategoryId, xProductName, xPrice, xCollectionId, xDescription);
                return p;
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void createNewProduct(int xCategoryID, String xProductName, double xPrice, int xCollectionID, String xDescription){
        try{
            xSql = "INSERT INTO product (CategoryID, ProductName, Price, CollectionID, description) value (?,?,?,?,?)";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xCategoryID);
            ps.setString(2, xProductName);
            ps.setDouble(3, xPrice);
            ps.setInt(4, xCollectionID);
            ps.setString(5, xDescription);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Create new Story: " + e.getMessage());
        }
    }
}
