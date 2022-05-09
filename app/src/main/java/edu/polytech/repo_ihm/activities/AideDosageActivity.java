package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.api.Requester;
import edu.polytech.repo_ihm.datas.Ingredients;
import edu.polytech.repo_ihm.datas.IngredientsAdapter;
import edu.polytech.repo_ihm.datas.QuantityAdapter;

public class AideDosageActivity extends AppCompatActivity {

    private ArrayList<Ingredients> ingredients = new ArrayList<>();
    private ArrayList<Boolean> ingredientIsSelected = new ArrayList<>();
    private ArrayList<String> quantityPerIngredient = new ArrayList<>();

    private RecyclerView ingredientsView;
    private RecyclerView quantityView;
    private Button show_quantity_button;
    private EditText nbPersonnes;
    private IngredientsAdapter.IngredientsClickListener listener;

    private final int BASE_CAL = 800; // base calories per person per meal per day
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
        nbPersonnes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ingredientsView.setVisibility(View.VISIBLE);
                show_quantity_button.setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.textView12)).setText("Nombre de personnes :");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        show_quantity_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                System.out.println(nbOfIngredients);
                quantityView.setAdapter(new QuantityAdapter(ingredients, ingredientIsSelected, quantityPerIngredient));
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
    }

    private void setOnClickListener() {
        listener = new IngredientsAdapter.IngredientsClickListener() {
            @Override
            public void onClick(View v, int position) {
                nbOfIngredients++;
                ingredientIsSelected.set(position, true);
                int id = ingredients.get(position).id();
                try {
                    quantityPerIngredient.set(position, calculateQuantity(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // set up listener here
            }
        };
    }

    private void setIngredients() {
        ingredients.addAll(Arrays.asList(Ingredients.values()));
        for (Ingredients i : Ingredients.values()) {
            ingredientIsSelected.add(false);
            quantityPerIngredient.add("");
        }
    }

    private String calculateQuantity(int ingredientID) throws Exception {
        double coef = ingredients.stream()
                .filter(ingredient -> ingredient.id() == ingredientID)
                .collect(Collectors.toList())
                .get(0).coef();
        int nb = Integer.parseInt(nbPersonnes.getText().toString());
        int totalCal = BASE_CAL * nb;
        JSONObject quantity = Requester.getIngredientQuantity(ingredientID,totalCal);
        String response = Double.toString(quantity.getDouble("amount") * coef) + quantity.getString("unit");
        return response;
    }

    public void back(View view) {
        Intent intent = new Intent(AideDosageActivity.this, MainActivity.class);
        startActivity(intent);
    }
}