package edu.polytech.repo_ihm.datas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory implements Serializable {

    private int id;
    private String name;
    private List<Product> products;
    private String endDate;
    private List<String> sharedMails;
    private boolean isShared = false;

    public Inventory(int id, String name,List<String> mails) {
        this.id = id;
        this.name = name;
        this.products =  new ArrayList<>();
        this.sharedMails = mails;
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

    @NonNull
    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + Arrays.toString(products.toArray()) +
                '}';
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public boolean isShared() {
        return isShared;
    }

    public List<String> getSharedMails() {
        return sharedMails;
    }

    public void setSharedMails(List<String> sharedMails) {
        this.sharedMails = sharedMails;
    }

    public String getMailsSeparated(){
        String s = "";
        for(String ss : sharedMails){
            s += ss + ";";
        }
        return s.substring(0,s.length());
    }

    public String getEndDate() {
        return endDate;
    }
}
