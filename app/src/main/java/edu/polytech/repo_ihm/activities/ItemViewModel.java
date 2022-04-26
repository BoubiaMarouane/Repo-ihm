package edu.polytech.repo_ihm.activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.polytech.repo_ihm.datas.Inventory;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<Inventory> selectedItem = new MutableLiveData<Inventory>();
    public void selectItem(Inventory item) {
        selectedItem.setValue(item);
    }
    public LiveData<Inventory> getSelectedItem() {
        return selectedItem;
    }
}