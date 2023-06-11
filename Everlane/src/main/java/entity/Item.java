package entity;

import Entity.Product;

public class Item {
    private Product product;
    private int quantity,size;
    private double price;

    public Item(Entity.Product product, int quantity, int size, double price) {
        this.product = product;
        this.quantity = quantity;
        this.size = size;
        this.price = price;
    }

    public Item() {
    }

    public Item(Product product, int quantity, int size) {
        this.product = product;
        this.quantity = quantity;
        this.size = size;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Item{" + "product=" + product.getProductID() + ", quantity=" + quantity + ", size=" + size + ", price=" + price + '}';
    }
}
