package edu.polytech.repo_ihm.activities;

import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.Inventory;
import edu.polytech.repo_ihm.datas.InventoryList;
import edu.polytech.repo_ihm.datas.Product;
import edu.polytech.repo_ihm.fragments.ProductListFragment;
import edu.polytech.repo_ihm.mock.MockData;

public class SelectedInventory extends AppCompatActivity {

    private int inventoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_inventory);
        inventoryId = getIntent().getIntExtra("IV_ID", 0);
        getIv(inventoryId).setProducts(MockData.products);
        generateFragListProducts();
        TextView title = findViewById(R.id.title);
        title.setText(getIv(inventoryId).getName());

        //findViewById(R.id.b_scan).setOnClickListener(v -> scanProduct()); marche pas bien


    }

    void generateFragListProducts() {
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



    public void back(View view) {
        Intent intent = new Intent(SelectedInventory.this, MyInventoriesActivity.class);
        startActivity(intent);
    }

    public void scanProduct() {



    }
}
