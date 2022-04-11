package edu.polytech.repo_ihm.datas;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private String name;
    private List<Product> products;

    public Inventory(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }
}
