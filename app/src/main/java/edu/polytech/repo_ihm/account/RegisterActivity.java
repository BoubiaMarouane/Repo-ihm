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
            builder.setMessage(R.string.loading).setTitle(R.string.registerTitle).setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            runOnUiThread(() -> {

                try {
                    AuthenticatorSingleton.getInstance().register(firstnameInput.getText().toString(), nameInput.getText().toString(), emailInput.getText().toString(), passwordInput.getText().toString(), passwordConfirmInput.getText().toString());
                    AuthenticatorSingleton.getInstance().registerThread.join();
                    JSONObject response = AuthenticatorSingleton.getInstance().lastRM.getRequestMessage();
                    if (response != null && response.has("session_token")) {
                        dialog.setMessage(getString(R.string.register_success));

                        AuthenticatorSingleton.getInstance().setCurrentUser((String) response.get("session_token"));
                        AuthenticatorSingleton.getInstance().setCurrentUserThread.join();

                        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("session_token", String.valueOf(response.get("session_token")));
                        editor.apply();

                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        dialog.cancel();
                        finish();
                    } else {
                        if (passwordInput.getText() != passwordConfirmInput.getText()) {
                            dialog.setMessage(getString(R.string.register_password));
                        } else {
                            dialog.setMessage(getString(R.string.register_mail));
                        }
                        final Timer t = new Timer();
                        t.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                runOnUiThread(() -> {
                                    registerButton.setEnabled(true);
                                    cancelButton.setEnabled(true);
                                    firstnameInput.setEnabled(true);
                                    nameInput.setEnabled(true);
                                    emailInput.setEnabled(true);
                                    passwordInput.setEnabled(true);
                                    passwordConfirmInput.setEnabled(true);
                                });
                                t.cancel();
                            }
                        }, 2000);
                    }
                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
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
