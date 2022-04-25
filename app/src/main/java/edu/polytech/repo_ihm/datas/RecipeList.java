package edu.polytech.repo_ihm.datas;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.List;

import edu.polytech.repo_ihm.R;

public class RecipeList extends ArrayAdapter<Recipe> {

    private Activity context;
    private List<Recipe> recipeList;

    public RecipeList(Activity context, List<Recipe> recipeList) {
        super(context, R.layout.activity_afficher_idee_recette, recipeList);
        this.context = context;
        this.recipeList = recipeList;
    }


}
