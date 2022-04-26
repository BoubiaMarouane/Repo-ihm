package edu.polytech.repo_ihm.account;

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

import java.util.Timer;
import java.util.TimerTask;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.StartActivity;
import edu.polytech.repo_ihm.activities.MainActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginButton);
        Button cancelButton = findViewById(R.id.cancelLogin);

        loginButton.setOnClickListener((View v) -> {
            loginButton.setEnabled(false);
            cancelButton.setEnabled(false);
            emailInput.setEnabled(false);
            passwordInput.setEnabled(false);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.loading).setTitle(R.string.login).setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            runOnUiThread(() -> {
                AuthenticatorSingleton.getInstance().logIn(emailInput.getText().toString(), passwordInput.getText().toString());
                try {
                    AuthenticatorSingleton.getInstance().logInThread.join();
                    JSONObject response = AuthenticatorSingleton.getInstance().lastRM.getRequestMessage();
                    if (response != null && response.has("session_token")) {
                        dialog.setMessage(getString(R.string.login_correct));
                        AuthenticatorSingleton.getInstance().setCurrentUser((String) response.get("session_token"));
                        AuthenticatorSingleton.getInstance().setCurrentUserThread.join();

                        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("session_token", String.valueOf(response.get("session_token")));
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        dialog.cancel();
                        finish();
                    } else {
                        dialog.setMessage(getString(R.string.login_incorrect));
                        final Timer t = new Timer();
                        t.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                runOnUiThread(() -> {
                                    loginButton.setEnabled(true);
                                    cancelButton.setEnabled(true);
                                    emailInput.setEnabled(true);
                                    passwordInput.setEnabled(true);
                                });
                                t.cancel();
                            }
                        }, 1000);
                    }
                } catch (InterruptedException | JSONException e) {
                    e.printStackTrace();
                }

            });
        });

        cancelButton.setOnClickListener((View v) -> actionBack());

        if (AuthenticatorSingleton.getInstance().isUserLogged())
            startActivity(new Intent(LoginActivity.this, MainActivity.class));


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                actionBack();
            }
        });
    }


    public void actionBack() {
        startActivity(new Intent(LoginActivity.this, StartActivity.class));
        finish();
    }


}
