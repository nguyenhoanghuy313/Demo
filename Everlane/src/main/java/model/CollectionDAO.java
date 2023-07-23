package model;
import entity.Collection;

import java.sql.Timestamp;

public class CollectionDAO extends myDAO{
    public entity.Collection getAllCollections(){
        entity.Collection x = null;
        xSql = "select * from collection";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xCollectionID;
            String xCollectionName;
            String xGetCollectionImg;
            String xCollection_description;
            Timestamp xDate;
            int xPromotionID;
            while(rs.next()){
                xCollectionID = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                xDate = rs.getTimestamp("create_date");
                xPromotionID = rs.getInt("PromotionID");
                x = new entity.Collection(xCollectionID, xCollectionName, xGetCollectionImg, xCollection_description, xDate, xPromotionID);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }

    public entity.Collection getCollections(String xId){
        entity.Collection x = null;
        int i = Integer.parseInt(xId);
        xSql = "select * from collection where CollectionID = ?";
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1, i);
            rs = ps.executeQuery();
            String xCollectionName;
            String xGetCollectionImg;
            String xCollection_description;
            Timestamp xcreate_date;
            int xPromotionID;
            while(rs.next()){
                i = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                xcreate_date = rs.getTimestamp("create_date");
                xPromotionID = rs.getInt("PromotionID");
                x = new entity.Collection(i, xCollectionName,xGetCollectionImg, xCollection_description, xcreate_date, xPromotionID);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }
    public entity.Collection getCollectionsByDate(){
        entity.Collection x = null;
        xSql = "select * from collection order by create_date desc Limit 1;";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xCollectionID;
            String xCollectionName;
            String xGetCollectionImg;
            String xCollection_description;
            Timestamp xcreate_date;
            int xPromotionID;
            while(rs.next()){
                xCollectionID = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                xcreate_date = rs.getTimestamp("create_date");
                xPromotionID = rs.getInt("PromotionID");
                x = new Collection(xCollectionID, xCollectionName , xGetCollectionImg, xCollection_description, xcreate_date, xPromotionID);
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

    public void updateCollectionPromoID(String xcolname, int xpromotionID, String xcolImg, String xcoldescrip, Timestamp xcreate_date, int xcolId) {
        try {
            xSql = "update collection set  CollectionName=?, PromotionID=?,  collectionImg =?,collection_description=?, create_date=? where collectionID =?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xcolname);
            ps.setInt(2, xpromotionID);
            ps.setString(3, xcolImg);
            ps.setString(4, xcoldescrip);
            ps.setTimestamp(5, xcreate_date);
            ps.setInt(6, xcolId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateCollectionPromoID: " + e.getMessage());
        }
    }



}
