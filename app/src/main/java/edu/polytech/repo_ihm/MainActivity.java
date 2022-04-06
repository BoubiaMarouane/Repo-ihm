package edu.polytech.repo_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.polytech.repo_ihm.gps.MapsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToMesInventaires(View view) {
        Intent intent = new Intent(MainActivity.this, MesInventaires.class);
        startActivity(intent);
    }

    public void goToMarketPlace(View view) {
        Intent intent = new Intent(MainActivity.this, MarketPlace.class);
        startActivity(intent);
    }

    public void goToAideDosage(View view) {
        Intent intent = new Intent(MainActivity.this, AideDosage.class);
        startActivity(intent);
    }

    public void goToIdeeRecettes(View view) {
        Intent intent = new Intent(MainActivity.this, IdeeRecettes.class);
        startActivity(intent);
    }
    public void goToAlertePeremption(View view) {
        Intent intent = new Intent(MainActivity.this, IdeeRecettes.class);
        startActivity(intent);
    }
}