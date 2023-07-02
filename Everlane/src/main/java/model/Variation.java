package model;

public class Variation {
    int variationID;
    int productID;
    int color_ID;
    int size_ID;
    int qty_In_Stock;
    int product_Img_ID;

    public Variation() {
    }

    public Variation(int variationID, int productID, int color_ID, int size_ID, int qty_In_Stock, int product_Img_ID) {
        this.variationID = variationID;
        this.productID = productID;
        this.color_ID = color_ID;
        this.size_ID = size_ID;
        this.qty_In_Stock = qty_In_Stock;
        this.product_Img_ID = product_Img_ID;
    }

    public int getVariationID() {
        return variationID;
    }

    public void setVariationID(int variationID) {
        this.variationID = variationID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getColor_ID() {
        return color_ID;
    }

    public void setColor_ID(int color_ID) {
        this.color_ID = color_ID;
    }

    public int getSize_ID() {
        return size_ID;
    }

    public void setSize_ID(int size_ID) {
        this.size_ID = size_ID;
    }

    public int getQty_In_Stock() {
        return qty_In_Stock;
    }

    public void setQty_In_Stock(int qty_In_Stock) {
        this.qty_In_Stock = qty_In_Stock;
    }

    public int getProduct_Img_ID() {
        return product_Img_ID;
    }

    public void setProduct_Img_ID(int product_Img_ID) {
        this.product_Img_ID = product_Img_ID;
    }
}
