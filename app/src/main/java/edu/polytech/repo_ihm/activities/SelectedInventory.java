package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Arrays;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.Inventory;
import edu.polytech.repo_ihm.datas.InventoryList;
import edu.polytech.repo_ihm.datas.Product;
import edu.polytech.repo_ihm.fragments.ProductListFragment;

public class SelectedInventory extends AppCompatActivity {

    private Inventory inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_inventory);

        int index = getIntent().getIntExtra("IV_ID",0);

        this.inventory = InventoryList.getInstance().getById(index);
        if(inventory == null)
            back(this.getCurrentFocus());

        TextView title = findViewById(R.id.title);
        title.setText(inventory.getName());
        title.setTextSize(20);

        inventory.setProducts(Arrays.asList(new Product("mayo", 1, R.drawable.mayo),
                                            new Product("riz", 1, R.drawable.riz),
                                            new Product("mayo", 1, R.drawable.mayo),
                                            new Product("riz", 1, R.drawable.riz)));

        Toast.makeText(this,inventory.getProducts().get(0).getName(),Toast.LENGTH_SHORT).show();
        Fragment frag = new ProductListFragment();
        Bundle args = new Bundle();
        args.putInt("IV_ID", index);
        frag.setArguments(args);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.frag_product_list, frag);
        ft.addToBackStack(null);
        ft.commit();

    }

    public void back(View view) {
        Intent intent = new Intent(SelectedInventory.this, MyInventoriesActivity.class);
        startActivity(intent);
    }
}