package edu.polytech.repo_ihm.datas;

import java.util.ArrayList;
import java.util.List;

public class InventoryList extends ArrayList<Inventory> {

    private static InventoryList instance = null;

    private InventoryList() {
        add(new Inventory(1,"Famille"));
        add(new Inventory(2,"Camping"));
        add(new Inventory(3,"soiree Etu"));
        add(new Inventory(4,"Anniv de Jhon Cena"));
        add(new Inventory(5,"t1"));
        add(new Inventory(6,"t2"));
    }


    public static InventoryList getInstance()
    {
        if (instance == null)
            instance = new InventoryList();
        return instance;
    }

    public List<Inventory> getInventories() {
        return this;
    }

    public Inventory getById(int id) {
        return this.stream().filter(inventory -> inventory.getId() == id).findFirst().orElse(null);
    }

    public String[] getAllInventoryNames(){
        return this.stream().map(Inventory::getName).toArray(String[]::new);

    }

    public String[] getAllProductNamesOfInventory(int id){
        return get(id).getProducts().stream().map(Product::getName).toArray(String[]::new);

    }

}
