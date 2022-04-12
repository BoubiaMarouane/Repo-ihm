package edu.polytech.repo_ihm.activities.ideeRecettes;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IdeeRecettesModel {

    // utiliser API pour recette
    String[] receiptsList = {"Recette1", "Recette2", "Recette3", "Recette4"};

    private IdeeRecettesController controller;

    public IdeeRecettesModel(IdeeRecettesController controller){
        this.controller = controller;
    }

    public void suggestReceipt(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        final String[] receiptName = {""};
        //before executing background task

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //background work here

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                receiptName[0] = receiptsList[new Random().nextInt(receiptsList.length)];

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //
                        controller.onReceiptSuggested(receiptName[0]);
                    }
                });
            }
        });
    }


}
