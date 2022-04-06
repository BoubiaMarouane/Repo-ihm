package edu.polytech.repo_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IdeeRecettes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idee_recettes);
    }

    public void returnHomePage(View view) {
        Intent intent = new Intent(IdeeRecettes.this, MainActivity.class);
        startActivity(intent);
    }
}