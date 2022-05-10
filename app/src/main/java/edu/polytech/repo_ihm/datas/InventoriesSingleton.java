package edu.polytech.repo_ihm.datas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.repo_ihm.StartActivity;
import edu.polytech.repo_ihm.account.AuthenticatorSingleton;
import edu.polytech.repo_ihm.api.Request;

public class InventoriesSingleton extends ArrayList<InventoryFactory> {

    private static InventoriesSingleton instance = null;

    private InventoriesSingleton() {
    }

    public static InventoriesSingleton getInstance() {
        if (instance == null)
            instance = new InventoriesSingleton();
        return instance;
    }

    private void reload() {
        clear();
        Request request = new Request("inventory", Request.RequestType.POST, "token", StartActivity.API_KEY, "session_token", AuthenticatorSingleton.getInstance().getCurrentUser().getSessionToken());
        try {
            request.getRequestThread().join();
            if (request.getRequestMessage().getRequestCode() == 200) {
                JSONArray array = new JSONArray(request.getRequestMessage().getStringMessage());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    add(new Inventory(object.getInt("id"), object.getString("label")));
                }
            }
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

    public List<InventoryFactory> getInventories() {
        reload();

        return this;
    }

    public InventoryFactory getById(int id) {
        return this.stream().filter(inventory -> inventory.getId() == id).findFirst().orElse(null);
    }

    public int getMaxId() {
        return this.stream().mapToInt(InventoryFactory::getId).max().orElse(0);
    }

    public String[] getAllInventoryNames() {
        return this.stream().map(InventoryFactory::getName).toArray(String[]::new);

    }

    public String[] getAllProductNamesOfInventory(int id) {
        return get(id).getProducts().stream().map(Product::getName).toArray(String[]::new);

    }

}
