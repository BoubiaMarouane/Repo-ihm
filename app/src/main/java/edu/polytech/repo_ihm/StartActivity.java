package edu.polytech.repo_ihm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.repo_ihm.account.AuthenticatorSingleton;
import edu.polytech.repo_ihm.account.LoginActivity;
import edu.polytech.repo_ihm.account.RegisterActivity;

public class StartActivity extends AppCompatActivity {
    public static String API_HOST = "http://47a0-109-210-108-136.eu.ngrok.io";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        AuthenticatorSingleton.getInstance();

        Button login = findViewById(R.id.gotoLoginButton);
        Button register = findViewById(R.id.gotoRegisterButton);
        login.setOnClickListener((View v) -> {
            Intent i = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(i);
        });
        register.setOnClickListener((View v) -> {
            Intent i = new Intent(StartActivity.this, RegisterActivity.class);
            startActivity(i);
        });
    }
}