package edu.polytech.repo_ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.StartActivity;
import edu.polytech.repo_ihm.account.AuthenticatorSingleton;
import edu.polytech.repo_ihm.api.Request;
import edu.polytech.repo_ihm.datas.InventoriesSingleton;
import edu.polytech.repo_ihm.datas.Inventory;
import edu.polytech.repo_ihm.mock.MockData;

public class MyInventoriesActivity extends AppCompatActivity {
    Button bCancelModif;
    private boolean isShared = false;
    private Inventory selectedIv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inventories);

        EditText label = findViewById(R.id.et_iv_name);

        Button createInventory = findViewById(R.id.createInventory);


        bCancelModif = findViewById(R.id.b_cancel_modif);
        // For retreiving the inventory to modify on the IvListFragment
        ItemViewModel viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getSelectedItem().observe(this, iv -> {

            this.selectedIv = (Inventory) iv;
            ((TextView) findViewById(R.id.tv_envie_iv)).setText(String.format("Modification de %s", iv.getName()));
            bCancelModif.setVisibility(View.VISIBLE);
            ((EditText) findViewById(R.id.et_iv_name)).setText(iv.getName());
            if (selectedIv.isShared()) {
                ((RadioButton) findViewById(R.id.radioButton)).toggle();
                ((EditText) findViewById(R.id.et_mail_input)).setText(selectedIv.getMailsSeparated());
            }
            ((EditText) findViewById(R.id.et_editTextDate)).setText(selectedIv.getEndDate());
            ((Button) findViewById(R.id.createInventory)).setText(String.format("MODIFIER %s", iv.getName()));
        });


        // Modif is cancelled so we refresh the Act
        bCancelModif.setOnClickListener(v -> {
            finish();
            startActivity(getIntent());
        });

        createInventory.setOnClickListener(view -> runOnUiThread(() -> {
            Request request = new Request("inventory/create", Request.RequestType.POST, "token", StartActivity.API_KEY, "session_token", AuthenticatorSingleton.getInstance().getCurrentUser().getSessionToken(), "label", label.getText().toString());
            try {
                request.getRequestThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (request.getRequestMessage().getRequestCode() == 200) {
                refreshInventory();
                finish();
                startActivity(getIntent());
            }
        }));

    }

    public void back(View view) {
        Intent intent = new Intent(MyInventoriesActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void setMailInputVisible(View view) {
        findViewById(R.id.et_mail_input).setVisibility(View.VISIBLE);
        this.isShared = true;
    }

    public void setMailInputInvisible(View view) {
        findViewById(R.id.et_mail_input).setVisibility(View.INVISIBLE);
        this.isShared = false;
    }


    public void refreshInventory() {
        String ivName = ((EditText) findViewById(R.id.et_iv_name)).getText().toString();
        if (ivName.equals("")) {
            Toast.makeText(this, "Veuillez renseignez un nom pour l'inventaire", Toast.LENGTH_LONG).show();
            return;
        }
        //On initialise la liste avec au moins le mail de l'user courant
        List<String> mails = new ArrayList<>(MockData.mails);
        if (isShared) {
            String[] tabIvMails = ((EditText) findViewById(R.id.et_mail_input)).getText().toString().split(";");
            mails = Arrays.stream(tabIvMails).collect(Collectors.toList());
        }
        Inventory iv = new Inventory(InventoriesSingleton.getInstance().getMaxId() + 1, ivName);
        //On check si une date de suppression auto optionnelle à été rentré
        String ivDate = ((EditText) findViewById(R.id.et_editTextDate)).getText().toString();
        if (!ivDate.equals("")) {
            iv.setEndDate(ivDate);
        }

        //On modifie
        if (selectedIv != null && bCancelModif.getVisibility() == View.VISIBLE) {
            this.selectedIv.setName(ivName);
            this.selectedIv.setSharedMails(mails);
            this.selectedIv.setEndDate(ivDate);
        } else {
            //On ajoute le nouvel inventaire
            InventoriesSingleton.getInstance().add(iv);
        }
    }
}