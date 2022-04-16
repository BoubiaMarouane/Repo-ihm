package edu.polytech.repo_ihm.account;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.StartActivity;
import edu.polytech.repo_ihm.activities.MainActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText firstnameInput = findViewById(R.id.firstnameInput);
        EditText nameInput = findViewById(R.id.nameInput);
        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText passwordConfirmInput = findViewById(R.id.passwordConfirmInput);

        Button registerButton = findViewById(R.id.register);
        Button cancelButton = findViewById(R.id.cancelRegister);


        registerButton.setOnClickListener((View v) -> {
            registerButton.setEnabled(false);
            cancelButton.setEnabled(false);
            firstnameInput.setEnabled(false);
            nameInput.setEnabled(false);
            emailInput.setEnabled(false);
            passwordInput.setEnabled(false);
            passwordConfirmInput.setEnabled(false);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Chargement en cours, merci de patienter").setTitle("Inscription").setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            @SuppressLint("ApplySharedPref") Thread login = new Thread(() -> {
                AuthenticatorSingleton.getInstance().register(firstnameInput.getText().toString(), nameInput.getText().toString(), emailInput.getText().toString(), passwordInput.getText().toString(), passwordConfirmInput.getText().toString());
                try {
                    AuthenticatorSingleton.getInstance().registerThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                JSONObject response = AuthenticatorSingleton.getInstance().lastRM.getRequestMessage();
                if (response.has("session_token")) {
                    dialog.setMessage("Vous êtes inscrit, redirection vers le menu...");
                    try {
                        AuthenticatorSingleton.getInstance().setCurrentUser((String) response.get("session_token"));
                    } catch (JSONException ignored) {
                    }
                    try {
                        AuthenticatorSingleton.getInstance().setCurrentUserThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    try {
                        editor.putString("session_token", String.valueOf(response.get("session_token")));
                    } catch (JSONException ignored) {
                    }
                    editor.commit();

                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    dialog.cancel();
                    finish();
                }
            });
            login.start();
        });


        cancelButton.setOnClickListener((View v) -> actionBack());

        if (AuthenticatorSingleton.getInstance().isUserLogged())
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                actionBack();
            }
        });
    }

    public void actionBack() {
        startActivity(new Intent(RegisterActivity.this, StartActivity.class));
        finish();
    }

}
