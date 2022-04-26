package edu.polytech.repo_ihm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.Inventory;
import edu.polytech.repo_ihm.datas.InventoryList;
import edu.polytech.repo_ihm.datas.Product;
import edu.polytech.repo_ihm.mock.MockData;

public class MyInventoriesActivity extends AppCompatActivity {
    private boolean isShared = false;
    private ItemViewModel viewModel;
    private Inventory selectedIv = null;

    Button bCancelModif;
    Button bSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inventories);


        bCancelModif = findViewById(R.id.b_cancel_modif);
        bSubmit = findViewById(R.id.b_submit_iv);
        // For retreiving the inventory to modify on the IvListFragment
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getSelectedItem().observe(this, iv -> {

            this.selectedIv = iv;
            ((TextView)findViewById(R.id.tv_envie_iv)).setText(String.format("Modification de %s", iv.getName()));
            bCancelModif.setVisibility(View.VISIBLE);
            ((EditText)findViewById(R.id.et_iv_name)).setText(iv.getName());
            if(iv.isShared()){
                ((RadioButton)findViewById(R.id.radioButton)).toggle();
                ((EditText)findViewById(R.id.et_mail_input)).setText(iv.getMailsSeparated());
            }
            ((EditText)findViewById(R.id.et_editTextDate)).setText(iv.getEndDate());
            ((Button)findViewById(R.id.b_submit_iv)).setText(String.format("MODIFIER %s", iv.getName()));
        });


        // Modif is cancelled so we refresh the Act
        bCancelModif.setOnClickListener(v -> {
            finish();startActivity(getIntent());
        });

        bSubmit.setOnClickListener(v -> {
            refreshInventory();
            finish();startActivity(getIntent());
        });

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


    public void refreshInventory(){
        String ivName = ((EditText) findViewById(R.id.et_iv_name)).getText().toString();
        if(ivName.equals("")){
            Toast.makeText(this, "Veuillez renseignez un nom pour l'inventaire", Toast.LENGTH_LONG).show();
            return;
        }
        //On initialise la liste avec au moins le mail de l'user courant
        List<String> mails = new ArrayList<>(MockData.mails);
        if (isShared) {
            String[] tabIvMails = ((EditText) findViewById(R.id.et_mail_input)).getText().toString().split(";");
            mails = Arrays.stream(tabIvMails).collect(Collectors.toList());
        }
        Inventory iv = new Inventory(InventoryList.getInstance().getMaxId()+1, ivName, mails);
        //On check si une date de suppression auto optionnelle à été rentré
        String ivDate = ((EditText) findViewById(R.id.et_editTextDate)).getText().toString();
        if(!ivDate.equals("")){
            iv.setEndDate(ivDate);
        }

        //On modifie
        if(selectedIv != null && bCancelModif.getVisibility() == View.VISIBLE){
            this.selectedIv.setName(ivName);
            this.selectedIv.setSharedMails(mails);
            this.selectedIv.setEndDate(ivDate);
        }
        else {
            //On ajoute le nouvel inventaire
            InventoryList.getInstance().add(iv);
        }
    }
}