package edu.polytech.repo_ihm.datas;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private int quantity;
    private int img;

    public Product(String name, int quantity, int img) {
        this.name = name;
        this.quantity = quantity;
        this.img = img;

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
}
