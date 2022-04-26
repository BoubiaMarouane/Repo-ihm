package edu.polytech.repo_ihm.activities.ideeRecettes;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.Recipe;
import edu.polytech.repo_ihm.firebase.DrawableFromURL;

public class IdeeRecettesController {

    public IdeeRecettesModel model;

    public IdeeRecettesActivity ideeRecettesActivity;

    public IdeeRecettesController(IdeeRecettesActivity ideeRecettesActivity){
        this.ideeRecettesActivity = ideeRecettesActivity;
        model = new IdeeRecettesModel(this);
    }
    public void suggestRecipes(String ingredient){
        model.suggestRecipes(ingredient);
    }

    public void onReceive(Recipe recipe) {
        TextView titleView = (TextView) ideeRecettesActivity.findViewById(R.id.recipe_title);
        ImageView imageView = (ImageView) ideeRecettesActivity.findViewById(R.id.recipe_img);
        WebView linkView = (WebView) ideeRecettesActivity.findViewById(R.id.recipe_link);
        titleView.setText(recipe.getTitle());
        linkView.setWebViewClient(new WebViewClient());
        linkView.loadUrl(recipe.getLink());
        new DrawableFromURL(imageView).execute(recipe.getImage());
    }

}
