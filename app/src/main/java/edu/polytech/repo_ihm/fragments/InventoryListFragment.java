package edu.polytech.repo_ihm.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.activities.SelectedInventory;
import edu.polytech.repo_ihm.datas.Inventory;
import edu.polytech.repo_ihm.datas.InventoryList;


public class InventoryListFragment extends Fragment {


    public InventoryListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);
        ListView lv = view.findViewById(R.id.lv_inventory);
        InventoryListAdapter adapter = new InventoryListAdapter(getActivity(), InventoryList.getInstance().getInventories());
        lv.setAdapter(adapter);
        return view;
    }


}

class InventoryListAdapter extends BaseAdapter {
    private final List<Inventory> inventories;
    private final Context context;

    InventoryListAdapter(Context context, List<Inventory> inventories) {
        this.inventories = inventories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return inventories.size();
    }

    @Override
    public Object getItem(int i) {
        return inventories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.inventory_list, parent, false);

        TextView pName = view.findViewById(R.id.inventoryName);

        Inventory p = inventories.get(i);
        pName.setText(p.getName());
        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, SelectedInventory.class);
            intent.putExtra("IV_ID", p.getId());
            context.startActivity(intent);
        });
        return view;
    }

}
