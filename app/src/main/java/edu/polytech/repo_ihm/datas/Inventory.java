package edu.polytech.repo_ihm.datas;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory extends InventoryFactory {

    private String endDate;
    private List<String> sharedMails;
    private boolean isShared = false;

    public Inventory(int id, String name) {
        super(id, name);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public boolean isShared() {
        return isShared;
    }

    public List<String> getSharedMails() {
        return sharedMails;
    }

    public void setSharedMails(List<String> sharedMails) {
        this.sharedMails = sharedMails;
    }

    public String getMailsSeparated(){
        String s = "";
        for(String ss : sharedMails){
            s += ss + ";";
        }
        return s.substring(0,s.length());
    }

    public String getEndDate() {
        return endDate;
    }
}
