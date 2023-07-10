package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAO extends myDAO{
    public List<Promotion> getAllPromotions(){
        List<Promotion> pl = new ArrayList<>();
        xSql = "select * from promotion";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xPromotionID;
            String xPromotionName, xPromotionDescription;
            int xDiscountRate;
            Date xStartDate, xEndDate;
            String xBackground_color;
            Promotion p;
            while (rs.next()){
                xPromotionID = rs.getInt("PromotionID");
                xPromotionName = rs.getString("PromotionName");
                xPromotionDescription = rs.getString("PromotionDescription");
                xDiscountRate = rs.getInt("DiscountRate");
                xStartDate = rs.getDate("StartDate");
                xEndDate = rs.getDate("EndDate");
                xBackground_color = rs.getString("background_color");
                p = new Promotion(xPromotionID, xPromotionName, xPromotionDescription, xDiscountRate, xStartDate, xEndDate, xBackground_color);
                pl.add(p);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (pl);
    }

    public void deletePromotion(String promotionID) {
        int uID = Integer.parseInt(promotionID);
        xSql = "Delete from promotion where PromotionID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewPromotion(String xPromotionName, String xPromotionDescription, int xDiscountRate, Date xStartDate, Date xEndDate, String xBackgroundColour){
        try{
            xSql = "INSERT INTO promotion (PromotionName, PromotionDescription, DiscountRate, StartDate, EndDate, background_color) value (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xPromotionName);
            ps.setString(2, xPromotionDescription);
            ps.setInt(3, xDiscountRate);
            ps.setDate(4, xStartDate);
            ps.setDate(5, xEndDate);
            ps.setString(6, xBackgroundColour);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Create new Story: " + e.getMessage());
        }
    }
}
