package model;
import entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Đoàn Phan Hưng - HE170721
public class CartItemDAO extends myDAO{

    public void insert(String proID, String varID){
        int xProID = Integer.parseInt(proID);
        int xVarID = Integer.parseInt(varID);
        xSql = "insert into cart_item (ProductID, Quantity, VariationID) values (?, 1, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProID);
            ps.setInt(2, xVarID);
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setCartID(String proID, String varID, String userID){
        int xProID = Integer.parseInt(proID);
        int xVarID = Integer.parseInt(varID);
        int xUserID = Integer.parseInt(userID);
        xSql = "UPDATE cart_item\n" +
                "SET CartID = (SELECT MIN(CartID) FROM cart c WHERE c.UserID = ?)\n" +
                "WHERE ProductID = ? AND variationID = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xUserID);
            ps.setInt(2, xProID);
            ps.setInt(3, xVarID);
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Product> getUserItem(int userID){
        xSql = "Select DISTINCT ci.VariationID, v.ProductID, pi.thumbnail, pi.product_img_1, pi.product_img_2, pi.product_img_3, ca.CategoryName, p.CollectionID, p.ProductName, col.color_Name, s.size_Name, p.Price, v.qty_in_stock from variation v, product_img pi, product p , category ca, color col, size s, cart c, cart_item ci where p.ProductID = v.ProductID and p.CategoryID = ca.CategoryID and v.product_img_ID = pi.product_img_ID and v.color_ID = col.color_ID and v.size_ID = s.size_ID and p.ProductID = ci.ProductID and c.CartID = ci.CartID and v.VariationID = ci.variationID and c.UserID = ?";
        int xProductID;
        String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
        int xCollectionID;
        String xProductName, xColor_Name, xSize_Name;
        double xPrice;
        int xQty_in_stock, xVariationID;
        Product x;
        List<Product> ci = new ArrayList<>();
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()){
                xProductID = rs.getInt("ProductID");
                xThumbnail = rs.getString("thumbnail");
                xProduct_img_1 = rs.getString("product_img_1");
                xProduct_img_2 = rs.getString("product_img_2");
                xProduct_img_3 = rs.getString("product_img_3");
                xCategoryName = rs.getString("CategoryName");
                xCollectionID = rs.getInt("CollectionID");
                xProductName = rs.getString("ProductName");
                xColor_Name = rs.getString("color_Name");
                xSize_Name = rs.getString("size_Name");
                xPrice = rs.getDouble("Price");
                xQty_in_stock = rs.getInt("qty_in_stock");
                xVariationID = rs.getInt("variationID");
                x = new Product(xProductID, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_stock,xVariationID);
                ci.add(x);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ci;
    }

    public List<CartItem> getCartItem(String userID){
        xSql = "select ci.* from cart_item ci, product p, cart c where c.UserID = ? and ci.ProductID = p.ProductID";
        int xUserId = Integer.parseInt(userID);
        int xCart_itemID;
        int xCartID;
        int xProductID;
        int xQuantity;
        List<CartItem> c = new ArrayList<>();
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1,xUserId);
            rs = ps.executeQuery();
            while (rs.next()){
                xCart_itemID = rs.getInt("cart_itemID");
                xCartID = rs.getInt("CartID");
                xProductID = rs.getInt("ProductID");
                xQuantity = rs.getInt("Quantity");
                c.add(new CartItem(xCart_itemID,xCartID,xProductID,xQuantity));
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }

    public void addQuantity (CartItem ci, int Quan){
        xSql = "update cart_item set Quantity = Quantity + ? where ProductID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, Quan);
            ps.setInt(2, ci.getProductID());
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void buy(int userID){
        xSql = "delete from cart where UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1,userID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCartItem(String ProductID, String VariationID) {
//        int xCartItemID = Integer.parseInt(CartItemID);
        int xProductID = Integer.parseInt(ProductID);
        int xVariationID = Integer.parseInt(VariationID);
        xSql = "delete from cart_item where ProductID=? and variationID=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProductID);
            ps.setInt(2, xVariationID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCartItemByProdID(String ProductID) {
        int xProductID = Integer.parseInt(ProductID);
        xSql = "delete from cart_item where ProductID=? ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProductID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkCartItemExist(String proID, String varID, String userID) {
        int xProID = Integer.parseInt(proID);
        int xVarID = Integer.parseInt(varID);
        int xUserID = Integer.parseInt(userID);
        xSql = "SELECT COUNT(*) AS count FROM cart_item ci INNER JOIN cart c ON ci.CartID = c.CartID WHERE ci.ProductID = ? AND ci.variationID = ? AND c.UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProID);
            ps.setInt(2, xVarID);
            ps.setInt(3, xUserID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//    public void insertProductIntoCart(int userID, int productID){
//        xSql
//    }
}
