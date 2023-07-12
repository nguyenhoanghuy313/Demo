package model;
import entity.*;
import java.util.ArrayList;
import java.util.List;
public class CartDAO extends myDAO {
    public void insertProductIntoCart(String  userID){
        int xUserID = Integer.parseInt(userID);
        xSql = "insert into Cart(UserID) values (?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xUserID);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Product> getProductInCart(int userID){
        xSql = "select p.* from product p, cart c, cart_item ci where p.ProductID = ci.ProductID and c.UserID = ?";
        List<Product> productList = new ArrayList<>();
        int xProductID;
        String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
        int xCollectionID;
        String xProductName, xColor_Name, xSize_Name;
        double xPrice;
        int xQty_in_stock;
        try{
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
                productList.add(new Product(xProductID, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_stock));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    public void deleteFromCart(String cartID) {
        xSql = "delete from cart where CartID=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, cartID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
