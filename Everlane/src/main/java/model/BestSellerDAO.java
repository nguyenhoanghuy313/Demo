package model;

import entity.BestSeller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BestSellerDAO extends myDAO {
    public List<BestSeller> getBestSeller() {
        List<BestSeller> od = new ArrayList<>();
        xSql = "select o.ProductID, c.color_Name, sum(Quantity) as Quantity \n" + "from orderdetails o, variation v, color c\n" + "where o.VariationID = v.VariationID\n" + "and v.color_ID = c.color_ID\n" + "group by o.ProductID, v.color_ID\n" + "order by Quantity desc;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int ProductID;
            String colorName;
            int Quantity;
            BestSeller bl;
            while (rs.next()) {
                ProductID = rs.getInt("ProductID");
                colorName = rs.getString("color_Name");
                Quantity = rs.getInt("Quantity");
                od.add(new BestSeller(ProductID, colorName, Quantity));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return od;
    }
}
