package edu.polytech.repo_ihm.datas;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.polytech.repo_ihm.StartActivity;
import edu.polytech.repo_ihm.api.Request;
import edu.polytech.repo_ihm.mock.MockData;

public abstract class InventoryFactory implements Serializable {
    private final int id;
    private String name;
    private List<Product> products = new ArrayList<>(MockData.products);

    public InventoryFactory(int id, String name) {
        this.id = id;
        this.name = name;

        Request request = new Request("inventory/product", Request.RequestType.POST, "token", StartActivity.API_KEY, "inventory_id", id);
        try {
            request.getRequestThread().join();
            if(request.getRequestMessage().getRequestCode() == 200) {
                JSONArray array = new JSONArray(request.getRequestMessage().getStringMessage());
                Log.d("last", "InventoryFactory: " + array);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    addProduct(new Product(object.getString("label"), object.getInt("quantity"), object.getString("expiry")));
                }

            }
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }



        addProduct(new Product("iui", 5, "5"));
    }

    public List<Product> getProducts() {
        return products;
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

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product p) {
        this.products.add(p);
    }

    public boolean containProduct(String research) {
        return products.stream().findFirst().filter(product -> product.getName().equals(research)).isPresent();
    }


}
