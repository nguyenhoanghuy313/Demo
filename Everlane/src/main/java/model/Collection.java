package model;

public class Collection {
    int collectionID;
    String collectionName;
    int promotionID;
    String collectionImg;
    String collection_description;

    public Collection() {
    }

    public Collection(int collectionID, String collectionName, int promotionID, String collectionImg, String collection_description) {
        this.collectionID = collectionID;
        this.collectionName = collectionName;
        this.promotionID = promotionID;
        this.collectionImg = collectionImg;
        this.collection_description = collection_description;
    }

    public int getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public String getCollectionImg() {
        return collectionImg;
    }

    public void setCollectionImg(String collectionImg) {
        this.collectionImg = collectionImg;
    }

    public String getCollection_description() {
        return collection_description;
    }

    public void setCollection_description(String collection_description) {
        this.collection_description = collection_description;
    }
}
