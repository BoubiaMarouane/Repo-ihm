package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.polytech.repo_ihm.R;

public class AideDosageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide_dosage);
    }

    public void returnHomePage(View view) {
        Intent intent = new Intent(AideDosageActivity.this, MainActivity.class);
        startActivity(intent);
    }
}