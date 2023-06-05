package model;
import java.sql.Date;
public class Product {
    int productID;
    int categoryID;
    String productName;
    String collection;
    String color;
    String productImg;
    float price;
    public Product() {
    }

    public Product(int productID, int categoryID, String productName, String collection, String color, String productImg, float price) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.productName = productName;
        this.collection = collection;
        this.color = color;
        this.productImg = productImg;
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
