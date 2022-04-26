package edu.polytech.repo_ihm.datas;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.repo_ihm.mock.MockData;

public class InventoryList extends ArrayList<Inventory> {

    private static InventoryList instance = null;

    private InventoryList() {
        add(new Inventory(0,"Famille", MockData.mails));
        add(new Inventory(1,"Camping",MockData.mails));
        add(new Inventory(2,"soiree Etu",MockData.mails));
        add(new Inventory(3,"Anniv de Jhon Cena",MockData.mails));
        add(new Inventory(4,"t1",MockData.mails));
        add(new Inventory(5,"t2",MockData.mails));
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

    public int getMaxId(){
        return this.stream().mapToInt(Inventory::getId).max().orElse(0);
    }

    public String[] getAllInventoryNames(){
        return this.stream().map(Inventory::getName).toArray(String[]::new);

    }

    public String[] getAllProductNamesOfInventory(int id){
        return get(id).getProducts().stream().map(Product::getName).toArray(String[]::new);

    }

}
