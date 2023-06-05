package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class ProductsDAO extends myDAO {
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        xSql = "select * from Product order by ProductID desc";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProductID;
            int xCategoryID;
            String xProductName;
            String xCollection;
            String xColor;
            String xProductImg;
            float xPrice;
            Product p;
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xCategoryID = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xCollection = rs.getString("Collection");
                xColor = rs.getString("Color");
                xProductImg = rs.getString("Product_img");
                xPrice = rs.getFloat("Price");
                p = new Product(xProductID, xCategoryID, xProductName, xCollection, xColor, xProductImg, xPrice);
                productList.add(p);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> searchByFilter(String[] type, int priceRange, String color) {
        List<Product> filterList = new ArrayList<>();
        xSql = "select * from Product,Category where Price <= " + priceRange;
        if (!(type == null && color == null)) {
            if (type != null && type.length > 0) {
                for (int i = 0; i < type.length; i++) {
                    if (type.length >= 2 && i == 1) {
                        xSql += " and CategoryName like '" + type[i] + "'";
                    }
                    if (type.length > 2 && i > 1) {
                        xSql += " or CategoryName '" + type[i] + "'";
                    }
                }
            }
//            if ((!size.equalsIgnoreCase()))
        }
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProductID;
            int xCategoryID;
            String xProductName;
            String xCollection;
            String xColor;
            String xProductImg;
            float xPrice;
            Product p;
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xCategoryID = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xCollection = rs.getString("Collection");
                xColor = rs.getString("Color");
                xProductImg = rs.getString("Product_img");
                xPrice = rs.getFloat("Price");
                p = new Product(xProductID, xCategoryID, xProductName, xCollection, xColor, xProductImg, xPrice);
                filterList.add(p);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterList;
    }

    public List<Product> searchByName(String sName) {
        xSql = "select * from Product where ProductName like '%" + sName + "'";
        List<Product> productList = new ArrayList<>();
        try {
            int xProductID;
            int xCategoryID;
            String xProductName;
            String xCollection;
            String xColor;
            String xProductImg;
            float xPrice;
            Product p;
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xCategoryID = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xCollection = rs.getString("Collection");
                xColor = rs.getString("Color");
                xProductImg = rs.getString("Product_img");
                xPrice = rs.getFloat("Price");
                p = new Product(xProductID, xCategoryID, xProductName, xCollection, xColor, xProductImg, xPrice);
                productList.add(p);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getProductsByCateID(String cateID) {
        List<Product> productList = new ArrayList<>();
        xSql = "Select * from Product where CategoryID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, cateID);
            rs = ps.executeQuery();
            int xProductID;
            int xCategoryID;
            String xProductName;
            String xCollection;
            String xColor;
            String xProductImg;
            float xPrice;
            Product p;
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xCategoryID = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xCollection = rs.getString("Collection");
                xColor = rs.getString("Color");
                xProductImg = rs.getString("Product_img");
                xPrice = rs.getFloat("Price");
                p = new Product(xProductID, xCategoryID, xProductName, xCollection, xColor, xProductImg, xPrice);
                productList.add(p);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product getProduct(String xId){
        Product x = null;
        int i = Integer.parseInt(xId);
        xSql = "select * from product where ProductID = ?";
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1, i);
            rs = ps.executeQuery();
            int xCategoryID;
            String xProductName;
            String xCollection;
            String xColor;
            String xProductImg;
            float xPrice;
            while (rs.next()){
                i = rs.getInt("ProductID");
                xCategoryID = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xCollection = rs.getString("Collection");
                xColor = rs.getString("Color");
                xProductImg = rs.getString("Product_img");
                xPrice = rs.getFloat("Price");
                x = new Product(i, xCategoryID, xProductName, xCollection, xColor, xProductImg, xPrice);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

//    public List<Product> sortProductsByPrice(List<Product> products) {
//        Collections.sort(products, Comparator.comparing(Product::getPrice));
//        return products;
//    }
//
//    public List<Product> sortProductsByName(List<Product> products) {
//        Collections.sort(products, Comparator.comparing(Product::getProductName));
//        return products;
//    }
//    public List<Product> sortProductsByReleaseDate(List<Product> products) {
//        Collections.sort(products, Comparator.comparing(Product::getReleaseDate, Comparator.reverseOrder()));
//        return products;
//    }
}

