package edu.polytech.repo_ihm.datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class InventoryFactory implements Serializable {
    private final int id;
    private String name;
    private List<Product> products = new ArrayList<>();

    public InventoryFactory(int id, String name) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void addProduct(Product p) {
        this.products.add(p);
    }

    public boolean containProduct(String research) {
        return products.stream().findFirst().filter(product -> product.getName().equals(research)).isPresent();
    }



}
