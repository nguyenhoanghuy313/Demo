package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class ProductDAO extends myDAO{
    public List<Product> getProductsByCID(String cid) {
        int xCid = Integer.parseInt(cid);
        List<Product> t = new ArrayList<>();
        xSql = "SELECT * FROM Product\n"
                + "Where CategoryID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            int xProductID;
            String xProductName;
            double xPrice;
            int xCollectionID;
            String xDescription;
            Product x;
            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xCid = rs.getInt("CategoryID");
                xProductName = rs.getString("ProductName");
                xPrice = rs.getDouble("Price");
                xCollectionID = rs.getInt("CollectionID");
                xDescription = rs.getString("description");
                x = new Product(xProductID, xCid, xProductName, xPrice, xCollectionID, xDescription);
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
