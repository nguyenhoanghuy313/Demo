package model;

import entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailDAO extends myDAO{
    public List<Product> getUserOrder(String userID){
        int xUserID = Integer.parseInt(userID);
        xSql = "Select DISTINCT od.VariationID, v.ProductID, pi.thumbnail, pi.product_img_1, pi.product_img_2, pi.product_img_3, ca.CategoryName, p.CollectionID, p.ProductName, col.color_Name, s.size_Name, p.Price, od.Quantity from variation v, product_img pi, product p , category ca, color col, size s, orderdetails od, shop_order so where p.ProductID = v.ProductID and p.CategoryID = ca.CategoryID and v.product_img_ID = pi.product_img_ID and od.OrderID = so.shop_orderID and v.color_ID = col.color_ID and v.size_ID = s.size_ID and p.ProductID = od.ProductID and v.VariationID = od.VariationID and so.UserID = ?;";
        int xProductID;
        String xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName;
        int xCollectionID;
        String xProductName, xColor_Name, xSize_Name;
        double xPrice;
        int xQty_in_cart, xVariationID;
        Product x;
        List<Product> ci = new ArrayList<>();
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xUserID);
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
                xQty_in_cart = rs.getInt("Quantity");
                xVariationID = rs.getInt("variationID");
                x = new Product(xProductID, xThumbnail, xProduct_img_1, xProduct_img_2, xProduct_img_3, xCategoryName, xCollectionID, xProductName, xColor_Name, xSize_Name, xPrice, xQty_in_cart,xVariationID);
                ci.add(x);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            System.out.println("getUserOrder" + e.getMessage());
        }
        return ci;
    }

    public List<OrderDetail> getOrderItems(String orderID) {
        int xOrderID = Integer.parseInt(orderID);
        List<OrderDetail> od = new ArrayList<>();
        xSql = "select * from orderdetails od, product p where od.OrderID = ? and od.ProductID = p.ProductID";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1,xOrderID);
            rs = ps.executeQuery();
            int xOrder_detailID;
            int xProductID;
            int xQuantity;
            int xPrice;
            Date xOrderDate;
            while (rs.next()){
                xOrder_detailID = rs.getInt("OrderDetailID");
                xProductID = rs.getInt("ProductID");
                xOrderID = rs.getInt("OrderID");
                xQuantity = rs.getInt("Quantity");
                xPrice = rs.getInt("Price");
                xOrderDate = rs.getDate("OrderDate");
                od.add(new OrderDetail(xOrder_detailID,xProductID,xOrderID,xQuantity,xPrice,xOrderDate));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return od;
    }

    public void insert(int orderID, String productID, String variationID,int quan, double price){
        int xProductID = Integer.parseInt(productID);
        int xVariationID = Integer.parseInt(variationID);
        xSql = "insert into orderdetails(OrderID, ProductID, VariationID,Quantity,Price) values (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1,orderID);
            ps.setInt(2,xProductID);
            ps.setInt(3, xVariationID);
            ps.setInt(4, quan);
            ps.setDouble(5, price);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("insertOrderDetail: " + e.getMessage());
        }
    }
}
