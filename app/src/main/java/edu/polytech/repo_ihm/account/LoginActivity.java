package edu.polytech.repo_ihm.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import edu.polytech.repo_ihm.MainActivity;
import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.StartActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginButton);
        Button cancelLoginButton = findViewById(R.id.cancelLogin);

        loginButton.setOnClickListener((View v) -> {
            loginButton.setEnabled(false);
            cancelLoginButton.setEnabled(false);
            emailInput.setEnabled(false);
            passwordInput.setEnabled(false);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Chargement en cours, merci de patienter").setTitle("Connexion").setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            Thread login = new Thread(() -> {
                AuthenticatorSingleton.getInstance().logIn(emailInput.getText().toString(), passwordInput.getText().toString());
                try {
                    AuthenticatorSingleton.getInstance().loginThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                JSONObject response = AuthenticatorSingleton.getInstance().lastRM.getRequestMessage();
                if (response.has("session_token")) {
                    dialog.setMessage("Identifiants correcte, redirection vers le menu...");
                    try {
                        AuthenticatorSingleton.getInstance().setCurrentUser((String) response.get("session_token"));
                    } catch (JSONException ignored) {
                    }
                    try {
                        AuthenticatorSingleton.getInstance().setCurrentUserThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            });
            login.start();
        });

        cancelLoginButton.setOnClickListener((View v) -> startActivity(new Intent(LoginActivity.this, StartActivity.class)));

        if (AuthenticatorSingleton.getInstance().isUserLogged())
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }


}
