package edu.polytech.repo_ihm.datas;

import java.io.Serializable;
import java.util.List;

public abstract class InventoryFactory implements Serializable {
    private final int id;
    private final String name;
    private List<Product> products;

    public InventoryFactory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
