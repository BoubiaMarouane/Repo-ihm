package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.Ingredients;
import edu.polytech.repo_ihm.datas.IngredientsAdapter;
import edu.polytech.repo_ihm.datas.QuantityAdapter;

public class AideDosageActivity extends AppCompatActivity {

    private ArrayList<Ingredients> ingredients = new ArrayList<>();
    private RecyclerView ingredientsView;
    private RecyclerView quantityView;
    private Button show_quantity_button;
    private EditText nbPersonnes;
    private IngredientsAdapter.IngredientsClickListener listener;

    private final int BASE_CAL = 2400; // base calories per person per meal per day
    private int nbOfIngredients = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_aide_dosage);
        ingredientsView = findViewById(R.id.aide_dosage_ingredients);
        quantityView = findViewById(R.id.quantity);
        nbPersonnes = findViewById(R.id.nb_personnes);
        show_quantity_button = findViewById(R.id.quantity_btn);
        show_quantity_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                quantityView.setVisibility(View.VISIBLE);
            }
        });
        setIngredients();
        setAdapter();
    }

    public void returnHomePage(View view) {
        Intent intent = new Intent(AideDosageActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void setAdapter() {
        setOnClickListener();
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredients, listener);
        ingredientsView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        quantityView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ingredientsView.setItemAnimator(new DefaultItemAnimator());
        quantityView.setItemAnimator(new DefaultItemAnimator());
        ingredientsView.setAdapter(ingredientsAdapter);
        quantityView.setAdapter(new QuantityAdapter(ingredients));

    }

    private void setOnClickListener() {
        listener = new IngredientsAdapter.IngredientsClickListener() {
            @Override
            public void onClick(View v, int position) {
                nbOfIngredients++;

                // set up listener here
            }
        };
    }

    private void setIngredients() {
        ingredients.addAll(Arrays.asList(Ingredients.values()));
    }

    private void calculateQuantity() {
        int nb = Integer.parseInt(nbPersonnes.getText().toString());
        int totalCal = BASE_CAL * nb;
    }
}