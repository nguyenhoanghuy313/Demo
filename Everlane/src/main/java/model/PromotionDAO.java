package model;

import entity.Promotion;

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
            double xDiscountRate;
            Date xStartDate, xEndDate;
            String xBackground_color;
            Promotion p;
            while (rs.next()){
                xPromotionID = rs.getInt("PromotionID");
                xPromotionName = rs.getString("PromotionName");
                xPromotionDescription = rs.getString("PromotionDescription");
                xDiscountRate = rs.getDouble("DiscountRate");
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

    public Promotion getLastestPromotion(){
        Promotion p = null;
        xSql = "select * from promotion order by PromotionID DESC LIMIT 1;";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xPromotionID;
            String xPromotionName, xPromotionDescription;
            double xDiscountRate;
            Date xStartDate, xEndDate;
            String xBackground_color;
            while(rs.next()){
                xPromotionID = rs.getInt("PromotionID");
                xPromotionName = rs.getString("PromotionName");
                xPromotionDescription = rs.getString("PromotionDescription");
                xDiscountRate = rs.getDouble("DiscountRate");
                xStartDate = rs.getDate("StartDate");
                xEndDate = rs.getDate("EndDate");
                xBackground_color = rs.getString("background_color");
                p = new Promotion(xPromotionID, xPromotionName, xPromotionDescription, xDiscountRate, xStartDate, xEndDate, xBackground_color);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return p;
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

    public void createNewPromotion(String xPromotionName, String xPromotionDescription, double xDiscountRate, Date xStartDate, Date xEndDate, String xBackgroundColour){
        try{
            xSql = "INSERT INTO promotion (PromotionName, PromotionDescription, DiscountRate, StartDate, EndDate, background_color) value (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xPromotionName);
            ps.setString(2, xPromotionDescription);
            ps.setDouble(3, xDiscountRate);
            ps.setDate(4, xStartDate);
            ps.setDate(5, xEndDate);
            ps.setString(6, xBackgroundColour);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Create new Story: " + e.getMessage());
        }
    }

    public Promotion getPromotionByID(String xId){
        Promotion p = null;
        int xPromotionID = Integer.parseInt(xId);
        xSql = "select * from promotion where PromotionID=?;";
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xPromotionID);
            rs = ps.executeQuery();
            String xPromotionName, xPromotionDescription;
            double xDiscountRate;
            Date xStartDate, xEndDate;
            String xBackground_color;
            while(rs.next()){
                xPromotionID = rs.getInt("PromotionID");
                xPromotionName = rs.getString("PromotionName");
                xPromotionDescription = rs.getString("PromotionDescription");
                xDiscountRate = rs.getDouble("DiscountRate");
                xStartDate = rs.getDate("StartDate");
                xEndDate = rs.getDate("EndDate");
                xBackground_color = rs.getString("background_color");
                p = new Promotion(xPromotionID, xPromotionName, xPromotionDescription, xDiscountRate, xStartDate, xEndDate, xBackground_color);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return p;
    }


    public static void main(String[] args) {
        PromotionDAO pDAO = new PromotionDAO();
        Promotion p = pDAO.getLastestPromotion();
        System.out.printf("%d %s %s %f %s %s %s", p.getPromotionID(), p.getPromotionName(), p.getPromotionDescription(), p.getDiscountRate(), p.getStartDate(), p.getEndDate(), p.getBackground_color());
    }
}
