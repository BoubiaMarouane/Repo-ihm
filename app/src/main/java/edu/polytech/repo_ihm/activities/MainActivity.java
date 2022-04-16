package edu.polytech.repo_ihm.activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.StartActivity;
import edu.polytech.repo_ihm.account.AuthenticatorSingleton;
import edu.polytech.repo_ihm.account.LoginActivity;
import edu.polytech.repo_ihm.activities.ideeRecettes.IdeeRecettesActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!AuthenticatorSingleton.getInstance().isUserLogged()) {
            Intent i = new Intent(MainActivity.this, StartActivity.class);
            startActivity(i);
        }





        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener((View v) -> {
            AuthenticatorSingleton.getInstance().logOut(AuthenticatorSingleton.getInstance().getCurrentUser().getSessionToken());
            try {
                AuthenticatorSingleton.getInstance().logOutThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("session_token");
            editor.commit();
            AuthenticatorSingleton.getInstance().resetInstance();
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            finish();
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
            }
        });

    }




    public void goToMesInventaires(View view) {
        Intent intent = new Intent(MainActivity.this, MesInventairesActivity.class);
        startActivity(intent);
    }

    public void goToMarketPlace(View view) {
        Intent intent = new Intent(MainActivity.this, MarketPlaceActivity.class);
        startActivity(intent);
    }

    public void goToAideDosage(View view) {
        Intent intent = new Intent(MainActivity.this, AideDosageActivity.class);
        startActivity(intent);
    }

    public void goToIdeeRecettes(View view) {
        Intent intent = new Intent(MainActivity.this, IdeeRecettesActivity.class);
        startActivity(intent);
    }
    public void goToAlertePeremption(View view) {
        Intent intent = new Intent(MainActivity.this, AlertePeremptionActivity.class);
        startActivity(intent);
    }
}