package edu.polytech.repo_ihm.activities.ideeRecettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.activities.MainActivity;

public class IdeeRecettesActivity extends AppCompatActivity {

    String[] receiptsList = {"Recette1", "Recette2", "Recette3", "Recette4"};

    TextView tvReceiptName;
    ProgressBar progressBar;
    Button bGetReceipt;

    IdeeRecettesController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idee_recettes);
        tvReceiptName = (TextView) findViewById(R.id.tvReceiptName);
        //progressBar = findViewById(R.id.progressBar);
        bGetReceipt = (Button) findViewById(R.id.bGetReceipt);
        controller =  new IdeeRecettesController(this);

        bGetReceipt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                controller.suggestReceipt();
            }
        });
    }



    public void returnHomePage(View view) {
        Intent intent = new Intent(IdeeRecettesActivity.this, MainActivity.class);
        startActivity(intent);
    }





}