package edu.polytech.repo_ihm.datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable {

    private int id;
    private String name;
    private List<Product> products;

    public Inventory(int id, String name) {
        this.id = id;
        this.name = name;
        this.products =  new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }
}
