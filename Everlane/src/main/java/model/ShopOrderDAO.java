package model;
import entity.ShopOrder;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ShopOrderDAO extends myDAO {
    public List<ShopOrder> getOrdersByUserID(int userID){
        List<ShopOrder> so = new ArrayList<>();
        xSql = "select * from shop_order where UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            int xShop_orderID;
            int xUserID;
            int xAddressID;
            int xOrder_total;
            String xOrder_status;
            while (rs.next()){
                xShop_orderID = rs.getInt("shop_orderID");
                xUserID = rs.getInt("UserID");
                xAddressID = rs.getInt("AddressID");
                xOrder_total = rs.getInt("Order_total");
                xOrder_status = rs.getString("Order_status");
                so.add(new ShopOrder(xShop_orderID,xUserID,xAddressID,xOrder_total,xOrder_status));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return so;
    }

    public ShopOrder getLatestOrder(){
        xSql = "SELECT * FROM shop_order ORDER BY shop_orderID DESC LIMIT 1 ";
        int xShop_orderID;
        int xUserID;
        int xAddressID;
        int xOrder_total;
        String xOrder_status;
        ShopOrder so = null;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()){
                xShop_orderID = rs.getInt("shop_orderID");
                xUserID = rs.getInt("UserID");
                xAddressID = rs.getInt("AddressID");
                xOrder_total = rs.getInt("Order_total");
                xOrder_status = rs.getString("Order_status");
                so = new ShopOrder(xShop_orderID,xUserID,xAddressID,xOrder_total,xOrder_status);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("getLatestOrder: " + e.getMessage());
        }
        return so;
    }

    public void insertOrder(ShopOrder so){
        xSql = "insert into shop_order(UserID,Order_total) values (?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1,so.getUserID());
            ps.setInt(2,so.getOrder_total());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getCurrentMaxOrderID(){
        xSql = "select max(shop_orderID) as maxOID from shop_order";
        int currMaxOID = 0;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if(rs.next()){
                currMaxOID = rs.getInt("maxOID");
            }
        } catch (Exception e) {
            System.out.println("getCurrMaxOID: " + e.getMessage());
        }
        return currMaxOID;
    }
}
