package edu.polytech.repo_ihm.activities.ideeRecettes;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.Recipe;
import edu.polytech.repo_ihm.firebase.DatabaseInstance;

public class IdeeRecettesModel {

    private IdeeRecettesController controller;

    public IdeeRecettesModel(IdeeRecettesController controller) {
        this.controller = controller;
    }

    public void suggestRecipes(String ingredient) {
        String recipe_id = String.valueOf(new Random().nextInt(3));
        DatabaseReference database = DatabaseInstance.getInstance().getReference();
        DatabaseReference pasta = database.child("recipes").child("byIngredient").child(ingredient).child("results").child(recipe_id);
        pasta.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot);
                Recipe displayedRecipe = snapshot.getValue(Recipe.class);
                controller.onReceive(displayedRecipe);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
