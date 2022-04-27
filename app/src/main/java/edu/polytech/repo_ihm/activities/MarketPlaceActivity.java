package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.polytech.repo_ihm.MarketPlace.BuyProductActivity;
import edu.polytech.repo_ihm.MarketPlace.VendreProd;
import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.gps.MapsActivity;



public class MarketPlaceActivity extends FragmentActivity {

    private Button buttonSellProduct;
    private Intent intentToSellProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_place_hp);

        buttonSellProduct = findViewById(R.id.btnVendresDespr);
        buttonSellProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToSellProduct=new Intent(MarketPlaceActivity.this , VendreProd.class);
                startActivity(intentToSellProduct);
            }
        });

    }

    public void returnHomePage(View view) {
        Intent intent = new Intent(MarketPlaceActivity.this, MainActivity.class);
        startActivity(intent);
    }


}