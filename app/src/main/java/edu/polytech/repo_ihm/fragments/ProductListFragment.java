package edu.polytech.repo_ihm.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.Locale;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.InventoriesSingleton;
import edu.polytech.repo_ihm.datas.InventoryFactory;
import edu.polytech.repo_ihm.datas.Product;

public class ProductListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private int inventoryId;
    private EditText pName;
    private EditText pQty;
    private EditText pDate;
    private Button bSubmit;

    private InventoryFactory currentInventory = null;

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey("IV_ID")) {
            currentInventory = InventoriesSingleton.getInstance().getById(getArguments().getInt("IV_ID"));
            pName = getActivity().findViewById(R.id.et_name_product);
            pQty = getActivity().findViewById(R.id.et_product_qty);
            pDate = getActivity().findViewById(R.id.et_product_date);
            bSubmit = getActivity().findViewById(R.id.b_submit);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_list, container, false);
        ListView lv = v.findViewById(R.id.lv_product_list);
        if(currentInventory != null) {
            ProductListAdapter pAdapter = new ProductListAdapter(getActivity(), currentInventory.getProducts());
            lv.setAdapter(pAdapter);
            lv.setOnItemClickListener(this);
            return v;
        } else {
            return null;
        }

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Product p = currentInventory.getProducts().get(i);
        if (currentInventory.containProduct(p.getName())) {
            pName.setText(String.format("%s", p.getName()));
            pQty.setText(String.format("%s", p.getQuantity()));
            pDate.setText(String.format("%s", p.getDateP()));
            bSubmit.setText(String.format("MODIFIER PRODUIT %s", p.getName().toUpperCase(Locale.ROOT)));
        }
    }
}


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


    private InventoryFactory getIv(int id) {
        return InventoriesSingleton.getInstance().getById(id);
    }


}


