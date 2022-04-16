package edu.polytech.repo_ihm.datas;

import java.util.ArrayList;

public class InventoryList extends ArrayList<Inventory> {

    private static InventoryList instance = null;

    private InventoryList() {
        add(new Inventory("Famille"));
        add(new Inventory("Camping"));
        add(new Inventory("soiree Etu"));
        add(new Inventory("Anniv de Jhon Cena"));
        add(new Inventory("t1"));
        add(new Inventory("t2"));
    }


    public static InventoryList getInstance()
    {
        if (instance == null)
            instance = new InventoryList();
        return instance;
    }

    public String[] getAllInventoryNames(){
        return this.stream().map(Inventory::getName).toArray(String[]::new);

    }

    public String[] getAllProductNamesOfInventory(int id){
        return get(id).getProducts().stream().map(Product::getName).toArray(String[]::new);

    }

}
