package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.Inventory;
import edu.polytech.repo_ihm.datas.InventoryList;
import edu.polytech.repo_ihm.datas.Product;
import edu.polytech.repo_ihm.fragments.ProductListFragment;

public class SelectedInventory extends AppCompatActivity {

    private int inventoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_inventory);
        inventoryId = getIntent().getIntExtra("IV_ID", 0);
        ArrayList<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("riz", 1, R.drawable.riz, "10/2/2020"));
        mockProducts.add(new Product("mayo", 1, R.drawable.mayo, "10/2/2020"));
        mockProducts.add(new Product("riz", 1, R.drawable.riz, "10/2/2020"));
        mockProducts.add(new Product("mayo", 1, R.drawable.mayo, "10/2/2020"));
        getIv(inventoryId).setProducts(mockProducts);
        generateFragListProducts();


    }

    void generateFragListProducts(){
        Fragment frag = new ProductListFragment();
        Bundle args = new Bundle();
        args.putInt("IV_ID", inventoryId);
        frag.setArguments(args);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.frag_product_list, frag);
        ft.addToBackStack(null);
        ft.commit();
    }




    public void submit(View view) {
        String pName = ((EditText) findViewById(R.id.et_name_product)).getText().toString();
        String pQty = ((EditText) findViewById(R.id.et_product_qty)).getText().toString();
        String pDate = ((EditText) findViewById(R.id.et_product_date)).getText().toString();
        Product newP = new Product(pName, Integer.parseInt(pQty), R.drawable.ic_edit, pDate);
        getIv(inventoryId).addProduct(newP);
        generateFragListProducts();
    }

        public Inventory getIv(int id) {
            return InventoryList.getInstance().get(id);
        }
}