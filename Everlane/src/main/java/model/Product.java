package model;
import java.sql.Date;
public class Product {
    int productID;
    String thumbnail, product_img_1, product_img_2, product_img_3, categoryName;
    int collectionID;
    String productName, color_Name, size_Name;
    double price;
    int qty_in_stock;

    public Product() {
    }

    public Product(int productID, String thumbnail, String product_img_1, String product_img_2, String product_img_3, String categoryName, int collectionID, String productName, String color_Name, String size_Name, double price, int qty_in_stock) {
        this.productID = productID;
        this.thumbnail = thumbnail;
        this.product_img_1 = product_img_1;
        this.product_img_2 = product_img_2;
        this.product_img_3 = product_img_3;
        this.categoryName = categoryName;
        this.collectionID = collectionID;
        this.productName = productName;
        this.color_Name = color_Name;
        this.size_Name = size_Name;
        this.price = price;
        this.qty_in_stock = qty_in_stock;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProduct_img_1() {
        return product_img_1;
    }

    public void setProduct_img_1(String product_img_1) {
        this.product_img_1 = product_img_1;
    }

    public String getProduct_img_2() {
        return product_img_2;
    }

    public void setProduct_img_2(String product_img_2) {
        this.product_img_2 = product_img_2;
    }

    public String getProduct_img_3() {
        return product_img_3;
    }

    public void setProduct_img_3(String product_img_3) {
        this.product_img_3 = product_img_3;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor_Name() {
        return color_Name;
    }

    public void setColor_Name(String color_Name) {
        this.color_Name = color_Name;
    }

    public String getSize_Name() {
        return size_Name;
    }

    public void setSize_Name(String size_Name) {
        this.size_Name = size_Name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty_in_stock() {
        return qty_in_stock;
    }

    public void setQty_in_stock(int qty_in_stock) {
        this.qty_in_stock = qty_in_stock;
    }
}
