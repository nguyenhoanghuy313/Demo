package model;
//import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class CollectionDAO extends myDAO{
    public Collection getAllCollections(){
        Collection x = null;
        xSql = "select * from collection";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xCollectionID;
            String xCollectionName;
            int xPromotionID;
            String xGetCollectionImg;
            String xCollection_description;
            Timestamp xDate;
            while(rs.next()){
                xCollectionID = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xPromotionID = rs.getInt("PromotionID");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                xDate = rs.getTimestamp("create_date");
                x = new Collection(xCollectionID, xCollectionName, xPromotionID , xGetCollectionImg, xCollection_description, xDate);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }

    public Collection getCollections(String xId){
        Collection x = null;
        int i = Integer.parseInt(xId);
        xSql = "select * from collection where CollectionID = ?";
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1, i);
            rs = ps.executeQuery();
            String xCollectionName;
            int xPromotionID;
            String xGetCollectionImg;
            String xCollection_description;
            Timestamp xcreate_date;
            while(rs.next()){
                i = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xPromotionID = rs.getInt("PromotionID");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                xcreate_date = rs.getTimestamp("create_date");
                x = new Collection(i, xCollectionName, xPromotionID , xGetCollectionImg, xCollection_description, xcreate_date);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }
    public Collection getCollectionsByDate(){
        Collection x = null;
        xSql = "select * from collection";
//        order by create_date desc Limit 1
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xCollectionID;
            String xCollectionName;
            int xPromotionID;
            String xGetCollectionImg;
            String xCollection_description;
            Timestamp xcreate_date;
            while(rs.next()){
                xCollectionID = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xPromotionID = rs.getInt("PromotionID");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                xcreate_date = rs.getTimestamp("create_date");
                x = new Collection(xCollectionID, xCollectionName, xPromotionID , xGetCollectionImg, xCollection_description, xcreate_date);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }

    public void updateCollection(String xcolname, String xcolImg, String xcoldescrip, Timestamp xcreate_date, int xcolId) {
        try {
            xSql = "update collection set  CollectionName=?, collectionImg =?,collection_description=?, create_date=? where collectionID =?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xcolname);
            ps.setString(2, xcolImg);
            ps.setString(3, xcoldescrip);
            ps.setTimestamp(4, xcreate_date);
            ps.setInt(5, xcolId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateCollection: " + e.getMessage());
        }
    }

//    public String getCreationTime() {
//        Date currentDate = new Date();
//        String creationTime = currentDate.toString();
//        return creationTime;
//    }

//    public static void main(String[] args) {
//
//        Date currentDate = new Date();
//        System.out.println("Thời gian tạo: " + currentTime);
//    }
}
