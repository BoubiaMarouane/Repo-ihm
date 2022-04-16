package edu.polytech.repo_ihm.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.List;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.InventoryList;
import edu.polytech.repo_ihm.datas.Product;

public class ProductListFragment extends Fragment implements AdapterView.OnItemClickListener {

    List<Product> productList;


    public ProductListFragment() {
        // Required empty public constructor

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            productList = (List<Product>) getArguments().getSerializable("product_list");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_list, container, false);
        ListView lv = v.findViewById(R.id.lv_product_list);
        ProductListAdapter pAdapter = new ProductListAdapter(getContext(), productList);
        lv.setAdapter(pAdapter);
        lv.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    /////////////////// Adapter ///////////////////


    class ProductListAdapter extends ArrayAdapter<String> {

        Context context;
        List<Product> productList;


        ProductListAdapter(Context context,  List<Product> productList) {
            super(context, R.layout.custom_product_list_layout, R.id.tv_product_name, productList.stream().map(Product::getName).toArray(String[]::new));
            this.context = context;
            this.productList = productList;

        }

         @Override
        public int getCount() {
            return 0;
        }

        @Override
        public String getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = li.inflate(R.layout.custom_product_list_layout, viewGroup, false);
            ImageView img = row.findViewById(R.id.img_product);
            TextView pName = row.findViewById(R.id.tv_product_name);
            TextView pQty = row.findViewById(R.id.tv_product_qty);

            Product p = productList.get(i);
            img.setImageResource(p.getImg());
            pName.setText(p.getName());
            pQty.setText(MessageFormat.format("qty: {0}g", p.getQuantity()));
            return row;
        }
    }


}

