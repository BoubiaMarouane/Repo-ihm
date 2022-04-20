package edu.polytech.repo_ihm.MarketPlace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.activities.AideDosageActivity;
import edu.polytech.repo_ihm.activities.MainActivity;
import edu.polytech.repo_ihm.activities.MarketPlaceActivity;
import edu.polytech.repo_ihm.gps.MapsActivity;

public class BuyProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);
    }

    public void returnHomePage(View view) {
        Intent intent = new Intent(BuyProductActivity.this, MarketPlaceActivity.class);
        startActivity(intent);
    }
    public void goToMap(View view) {
        Intent intent = new Intent(BuyProductActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}