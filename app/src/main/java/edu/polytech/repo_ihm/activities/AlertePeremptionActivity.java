package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.polytech.repo_ihm.R;

public class AlertePeremptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerte_peremption);
    }

    public void returnHomePage(View view) {
        Intent intent = new Intent(AlertePeremptionActivity.this, MainActivity.class);
        startActivity(intent);
    }
}