package entity;

public class ProductImg {
    int product_Img_ID;
    String thumbnail, product_Img_1,product_Img_2, product_Img_3, product_img_name;

    public ProductImg() {
    }

    public ProductImg(int product_Img_ID, String thumbnail, String product_Img_1, String product_Img_2, String product_Img_3, String product_img_name) {
        this.product_Img_ID = product_Img_ID;
        this.thumbnail = thumbnail;
        this.product_Img_1 = product_Img_1;
        this.product_Img_2 = product_Img_2;
        this.product_Img_3 = product_Img_3;
        this.product_img_name = product_img_name;
    }

    public int getProduct_Img_ID() {
        return product_Img_ID;
    }

    public void setProduct_Img_ID(int product_Img_ID) {
        this.product_Img_ID = product_Img_ID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProduct_Img_1() {
        return product_Img_1;
    }

    public void setProduct_Img_1(String product_Img_1) {
        this.product_Img_1 = product_Img_1;
    }

    public String getProduct_Img_2() {
        return product_Img_2;
    }

    public void setProduct_Img_2(String product_Img_2) {
        this.product_Img_2 = product_Img_2;
    }

    public String getProduct_Img_3() {
        return product_Img_3;
    }

    public void setProduct_Img_3(String product_Img_3) {
        this.product_Img_3 = product_Img_3;
    }

    public String getProduct_img_name() {
        return product_img_name;
    }

    public void setProduct_img_name(String product_img_name) {
        this.product_img_name = product_img_name;
    }
}
