package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.StartActivity;
import edu.polytech.repo_ihm.account.AuthenticatorSingleton;
import edu.polytech.repo_ihm.account.LoginActivity;
import edu.polytech.repo_ihm.activities.ideeRecettes.IdeeRecettesActivity;

public class MainActivity extends AppCompatActivity {

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
            AuthenticatorSingleton.getInstance().resetInstance();
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            finish();
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