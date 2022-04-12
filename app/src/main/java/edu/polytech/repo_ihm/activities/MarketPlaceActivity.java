package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.polytech.repo_ihm.R;

public class MarketPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_place_hp);
    }

    public void returnHomePage(View view) {
        Intent intent = new Intent(MarketPlaceActivity.this, MainActivity.class);
        startActivity(intent);
    }
}