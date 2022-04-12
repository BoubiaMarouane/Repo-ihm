package edu.polytech.repo_ihm.activities.ideeRecettes;

import android.view.View;

public class IdeeRecettesController {

    public IdeeRecettesModel model;

    public IdeeRecettesActivity ideeRecettesActivity;

    public IdeeRecettesController(IdeeRecettesActivity ideeRecettesActivity){
        this.ideeRecettesActivity = ideeRecettesActivity;
        model = new IdeeRecettesModel(this);
    }
    public void suggestReceipt(){
        //this.ideeRecettesActivity.progressBar.setVisibility(View.VISIBLE);
        model.suggestReceipt();
    }

    public void onReceiptSuggested(String receiptName){
        this.ideeRecettesActivity.tvReceiptName.setText(receiptName);
        //this.ideeRecettesActivity.progressBar.setVisibility(View.INVISIBLE);

    }

}
