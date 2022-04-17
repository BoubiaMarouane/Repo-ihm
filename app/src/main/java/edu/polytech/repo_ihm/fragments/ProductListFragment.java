package edu.polytech.repo_ihm.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.InventoryList;
import edu.polytech.repo_ihm.datas.Product;

public class ProductListFragment extends Fragment {

    private static final int SIZE_LIMIT = 3;
    int inventoryId;


    public ProductListFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            inventoryId = getArguments().getInt("IV_ID");
            Toast.makeText(getContext(), "ASSIGN OK " + inventoryId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_list, container, false);
        ListView lv = v.findViewById(R.id.lv_product_list);

        ProductListAdapter pAdapter = new ProductListAdapter(getActivity(), InventoryList.getInstance().get(inventoryId).getProducts());
        lv.setAdapter(pAdapter);

        return v;
    }
}


/////////////////// Adapter ///////////////////


class ProductListAdapter extends BaseAdapter {
    private final List<Product> productList;
    private final Context context;

    ProductListAdapter(Context context, List<Product> products) {
        this.productList = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.product_list, parent, false);
        TextView pName = view.findViewById(R.id.productName);
        Product p = productList.get(i);
        pName.setText(p.getName());
        return view;
    }
}


