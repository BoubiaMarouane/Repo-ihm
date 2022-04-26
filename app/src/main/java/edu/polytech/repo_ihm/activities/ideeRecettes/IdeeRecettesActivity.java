package edu.polytech.repo_ihm.activities.ideeRecettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.activities.MainActivity;
import edu.polytech.repo_ihm.api.Requester;

public class IdeeRecettesActivity extends AppCompatActivity {

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private RecyclerView recyclerView;
    private IngredientAdapter.RecyclerViewClickListener listener;

    private String selected_ingredient;
    Button button_showRecipes;

    IdeeRecettesController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idee_recettes);
        button_showRecipes = (Button) findViewById(R.id.button_recipes);
        controller = new IdeeRecettesController(this);
        button_showRecipes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                controller.suggestRecipes(selected_ingredient);
                setContentView(R.layout.activity_afficher_idee_recette);
            }
        });
        recyclerView = findViewById(R.id.ingredients_recycler_view);
        setIngredients();
        setAdapter();
    }



    public void returnHomePage(View view) {
        Intent intent = new Intent(IdeeRecettesActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void setAdapter() {
        setOnClickListener();
        IngredientAdapter adapter = new IngredientAdapter(ingredients, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new IngredientAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                selected_ingredient = ingredients.get(position).getName();
                System.out.println(selected_ingredient);
            }
        };
    }

    private void setIngredients() {
        ingredients.add(new Ingredient("cheese","Fromage"));
        ingredients.add(new Ingredient("chocolate","Chocolat"));
        ingredients.add(new Ingredient("pasta","Pâtes"));
    }





}