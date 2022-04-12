package edu.polytech.repo_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import edu.polytech.repo_ihm.account.AuthenticatorSingleton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!AuthenticatorSingleton.getInstance().isUserLogged()) {
            Intent i = new Intent(MainActivity.this, StartActivity.class);
            startActivity(i);
        }

    }

}