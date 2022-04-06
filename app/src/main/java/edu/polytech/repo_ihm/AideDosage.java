package edu.polytech.repo_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AideDosage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide_dosage);
    }

    public void returnHomePage(View view) {
        Intent intent = new Intent(AideDosage.this, MainActivity.class);
        startActivity(intent);
    }
}