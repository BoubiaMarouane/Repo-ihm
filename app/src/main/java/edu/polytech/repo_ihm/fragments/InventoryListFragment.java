package edu.polytech.repo_ihm.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.activities.SelectedInventory;
import edu.polytech.repo_ihm.datas.InventoryList;


public class InventoryListFragment extends Fragment implements AdapterView.OnItemClickListener {



    //private Listener listener;

    public InventoryListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inventory_list, container, false);
        ListView lv = view.findViewById(R.id.lv_inventory);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, InventoryList.getInstance().getAllInventoryNames());
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
        return view;
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getContext(), "id" + i,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), SelectedInventory.class);
        intent.putExtra("IV_ID", i);
        startActivity(intent);

    }
}