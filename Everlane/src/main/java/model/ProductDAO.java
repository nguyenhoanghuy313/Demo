package model;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends myDAO {
    public List<Product> getProductsByCID(String cid) {
        List<Product> t = new ArrayList<>();
        xSql = "select DISTINCT v.ProductID, pi.thumbnail, pi.product_img_1, pi.product_img_2, pi.product_img_3, p.ProductName, p.Price, col.color_Name\n" +
                "from variation v, product_img pi, product p , category c, color col, size s\n" +
                "where c.CategoryID = ? \n" +
                "and c.CategoryID = p.CategoryID \n" +
                "and p.ProductID = v.ProductID \n" +
                "and v.product_img_ID = pi.product_img_ID\n" +
                "and v.color_ID = col.color_ID\n" +
                "and v.size_ID = s.size_ID;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            int xProductID;
            String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
            int xCollectionID;
            String xProductName, xColor_Name, xSize_Name;
            double xPrice;
            int xQty_in_stock;
            Product x;
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xThumbnail = rs.getString("thumbnail");
                xProduct_img_1 = rs.getString("product_img_1");
                xProduct_img_2 = rs.getString("product_img_2");
                xProduct_img_3 = rs.getString("product_img_3");
                xCategoryName = null;
                xCollectionID = 0;
                xProductName = rs.getString("ProductName");
                xColor_Name = rs.getString("color_Name");
                xSize_Name = null;
                xPrice = rs.getDouble("Price");
                xQty_in_stock = 0;
                x = new Product(xProductID, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_stock);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProductsByColIDProID(String cateID, String colID) {
        List<Product> t = new ArrayList<>();
        xSql = "select DISTINCT v.ProductID, pi.thumbnail, pi.product_img_1, pi.product_img_2, pi.product_img_3, p.ProductName, p.Price, col.color_Name\n" +
                "from variation v, product_img pi, product p , category c, color col, size s\n" +
                "where col.color_ID = ?\n" +
                "and c.CategoryID = ?\n" +
                "and c.CategoryID = p.CategoryID \n" +
                "and p.ProductID = v.ProductID \n" +
                "and v.product_img_ID = pi.product_img_ID\n" +
                "and v.color_ID = col.color_ID\n" +
                "and v.size_ID = s.size_ID;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, colID);
            ps.setString(2, cateID);
            rs = ps.executeQuery();
            int xProductID;
            String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
            int xCollectionID;
            String xProductName, xColor_Name, xSize_Name;
            double xPrice;
            int xQty_in_stock;
            Product x;
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xThumbnail = rs.getString("thumbnail");
                xProduct_img_1 = rs.getString("product_img_1");
                xProduct_img_2 = rs.getString("product_img_2");
                xProduct_img_3 = rs.getString("product_img_3");
                xCategoryName = null;
                xCollectionID = 0;
                xProductName = rs.getString("ProductName");
                xColor_Name = rs.getString("color_Name");
                xSize_Name = null;
                xPrice = rs.getDouble("Price");
                xQty_in_stock = 0;
                x = new Product(xProductID, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_stock);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Product getProductByProIDColName(String xId, String xColor_Name) {
        int i = Integer.parseInt(xId);
        xSql = "select DISTINCT v.ProductID, pi.thumbnail, pi.product_img_1, pi.product_img_2, pi.product_img_3, p.ProductName, p.Price, col.color_Name\n" +
                "from variation v, product_img pi, product p , category c, color col, size s\n" +
                "where col.color_Name like '%" + xColor_Name + "%'\n" +
                "and p.ProductID = ?\n" +
                "and p.ProductID = v.ProductID \n" +
                "and v.product_img_ID = pi.product_img_ID\n" +
                "and v.color_ID = col.color_ID\n" +
                "and v.size_ID = s.size_ID;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, i);
            rs = ps.executeQuery();
            int xProductID;
            String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
            int xCollectionID;
            String xProductName, xSize_Name;
            double xPrice;
            int xQty_in_stock;
            Product x = null;
            while (rs.next()) {
                i = rs.getInt("ProductID");
                xThumbnail = rs.getString("thumbnail");
                xProduct_img_1 = rs.getString("product_img_1");
                xProduct_img_2 = rs.getString("product_img_2");
                xProduct_img_3 = rs.getString("product_img_3");
                xCategoryName = null;
                xCollectionID = 0;
                xProductName = rs.getString("ProductName");
                xColor_Name = rs.getString("color_Name");
                xSize_Name = null;
                xPrice = rs.getDouble("Price");
                xQty_in_stock = 0;
                x = new Product(i, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_stock);
                return x;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> searchByName(String sName) {
        List<Product> t = new ArrayList<>();
        xSql = "select DISTINCT v.ProductID, pi.thumbnail, pi.product_img_1, pi.product_img_2, pi.product_img_3, p.ProductName, p.Price, col.color_Name\n" +
                "from variation v, product_img pi, product p , category c, color col, size s\n" +
                "where p.ProductName like '%" + sName + "%'\n" +
                "and p.ProductID = v.ProductID \n" +
                "and v.product_img_ID = pi.product_img_ID\n" +
                "and v.color_ID = col.color_ID\n" +
                "and v.size_ID = s.size_ID;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xProductID;
            String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
            int xCollectionID;
            String xProductName, xColor_Name, xSize_Name;
            double xPrice;
            int xQty_in_stock;
            Product x;
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xThumbnail = rs.getString("thumbnail");
                xProduct_img_1 = rs.getString("product_img_1");
                xProduct_img_2 = rs.getString("product_img_2");
                xProduct_img_3 = rs.getString("product_img_3");
                xCategoryName = null;
                xCollectionID = 0;
                xProductName = rs.getString("ProductName");
                xColor_Name = rs.getString("color_Name");
                xSize_Name = null;
                xPrice = rs.getDouble("Price");
                xQty_in_stock = 0;
                x = new Product(xProductID, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_stock);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
}
