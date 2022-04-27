package edu.polytech.repo_ihm.MarketPlace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.activities.MarketPlaceActivity;
import edu.polytech.repo_ihm.activities.ideeRecettes.Ingredient;
import edu.polytech.repo_ihm.activities.ideeRecettes.IngredientAdapter;
import edu.polytech.repo_ihm.datas.Ingredients;
import edu.polytech.repo_ihm.datas.IngredientsAdapter;

public class VendreProd extends AppCompatActivity {

    ArrayList<Ingredients> productsToSell = new ArrayList<>();
    RecyclerView recyclerView;

    private IngredientsAdapter.IngredientsClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendre_prod);
        recyclerView = findViewById(R.id.products_to_sell);
        setProductsToSell();
        setAdapter();


    }

    private void setAdapter() {
        IngredientsAdapter adapter = new IngredientsAdapter(productsToSell, listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setProductsToSell() {
        productsToSell.addAll(Arrays.asList(Ingredients.values()));
    }

    public void returnHomePage(View view) {
        Intent intent = new Intent(VendreProd.this, MarketPlaceActivity.class);
        startActivity(intent);
    }

}