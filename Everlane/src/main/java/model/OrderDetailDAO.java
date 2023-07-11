package model;

import entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailDAO extends myDAO{
    public List<OrderDetail> getOrderItems(int orderID) {
        List<OrderDetail> od = new ArrayList<>();
        xSql = "select * from orderdetails od, product p where od.OrderID = ? and od.ProductID = p.ProductID";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1,orderID);
            rs = ps.executeQuery();
            int xOrder_detailID;
            int xProductID;
            int xOrderID;
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

    public void insert(OrderDetail od){
        xSql = "insert into orderdetails(productid, orderid, quantity, price) values (?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1,od.getProductID());
            ps.setInt(2,od.getOrderID());
            ps.setInt(3,od.getQuantity());
            ps.setInt(4,od.getPrice());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
