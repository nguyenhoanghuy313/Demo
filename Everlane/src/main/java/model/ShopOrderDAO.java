package model;
import entity.ShopOrder;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ShopOrderDAO extends myDAO {
    public List<ShopOrder> getOrdersByUserID(int userID){
        List<ShopOrder> so = new ArrayList<>();
        xSql = "select DISTINCT so.* from shop_order so, orderdetails od where UserID = ? and so.shop_orderID = od.OrderID";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            int xShop_orderID;
            int xUserID;
            int xAddressID;
            int xOrder_total;
            String xOrder_status;
            String xRecipient, xRecipent_phone;
            while (rs.next()){
                xShop_orderID = rs.getInt("shop_orderID");
                xUserID = rs.getInt("UserID");
                xAddressID = rs.getInt("AddressID");
                xOrder_total = rs.getInt("Order_total");
                xOrder_status = rs.getString("Order_status");
                xRecipient = rs.getString("recipient");
                xRecipent_phone = rs.getString("recipent_phone");
                so.add(new ShopOrder(xShop_orderID,xUserID,xAddressID,xOrder_total,xOrder_status,xRecipient,xRecipent_phone));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("getOrdersByUserID: " + e.getMessage());
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
        String xRecipient, xRecipent_phone;

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
                xRecipient = rs.getString("recipient");
                xRecipent_phone = rs.getString("recipent_phone");
                so=new ShopOrder(xShop_orderID,xUserID,xAddressID,xOrder_total,xOrder_status,xRecipient,xRecipent_phone);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("getLatestOrder: " + e.getMessage());
        }
        return so;
    }

    public void insertOrder(ShopOrder so){
        xSql = "insert into shop_order(UserID,Order_status,recipient,recipent_phone) values (?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1,so.getUserID());
            ps.setString(2,so.getOrder_status());
            ps.setString(3,so.getRecipient());
            ps.setString(4,so.getRecipent_phone());
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

    public void setOrderTotal(){
        xSql = "UPDATE shop_order AS so SET Order_total = (SELECT SUM(od.Quantity * od.Price) FROM orderdetails AS od WHERE od.OrderID = so.shop_orderID) WHERE so.Order_status = 'Ordered';\n";
        try {
            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            System.out.println("setOrderTotal: " + e.getMessage());
        }
    }
}
