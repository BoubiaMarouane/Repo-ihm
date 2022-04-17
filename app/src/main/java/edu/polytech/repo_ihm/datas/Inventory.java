package edu.polytech.repo_ihm.datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

    public boolean containProduct(String research){
        return products.stream().map(Product::getName).anyMatch(name -> name.equals(research));
    }

    public void addProduct(Product p){
        this.products.add(p);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + Arrays.toString(products.toArray()) +
                '}';
    }
}
