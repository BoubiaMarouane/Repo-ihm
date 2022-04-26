package edu.polytech.repo_ihm.activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.polytech.repo_ihm.datas.InventoryFactory;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<InventoryFactory> selectedItem = new MutableLiveData<InventoryFactory>();
    public void selectItem(InventoryFactory item) {
        selectedItem.setValue(item);
    }
    public LiveData<InventoryFactory> getSelectedItem() {
        return selectedItem;
    }
}