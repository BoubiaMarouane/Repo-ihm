package edu.polytech.repo_ihm.datas;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private int quantity;
    private int img;
    private String dateP;

    public Product(String name, int quantity, int img, String dateP) {
        this.name = name;
        this.quantity = quantity;
        this.img = img;
        this.dateP = dateP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImg() {
        return this.img;
    }

    public String getDateP() {
        return dateP;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setDateP(String dateP) {
        this.dateP = dateP;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", img=" + img +
                ", dateP='" + dateP + '\'' +
                '}';
    }
}
