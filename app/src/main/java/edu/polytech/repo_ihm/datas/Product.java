package edu.polytech.repo_ihm.datas;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private int quantity;
    private String dateP;

    public Product(String name, int quantity, String dateP) {
        this.name = name;
        this.quantity = quantity;
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


    public String getDateP() {
        return dateP;
    }


    public void setDateP(String dateP) {
        this.dateP = dateP;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", dateP='" + dateP + '\'' +
                '}';
    }
}
