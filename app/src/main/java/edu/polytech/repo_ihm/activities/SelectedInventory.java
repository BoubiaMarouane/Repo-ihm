package edu.polytech.repo_ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.InventoriesSingleton;
import edu.polytech.repo_ihm.datas.InventoryFactory;
import edu.polytech.repo_ihm.datas.Product;
import edu.polytech.repo_ihm.fragments.ProductListFragment;

public class SelectedInventory extends AppCompatActivity {

    private int inventoryId;

    // Barcode scanner part //
    private final String SEPARATOR = "&";
    private final int PRODUCT_NAME = 0;
    private final int PRODUCT_DATE_EXP = 1;
    private final int PRODUCT_QTY = 2;
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(this, "Erreur, Interruption du scan", Toast.LENGTH_LONG).show();
                } else {
                    String res =  result.getContents();
                    String pName = res.split(SEPARATOR)[PRODUCT_NAME];
                    String pDate = res.split(SEPARATOR)[PRODUCT_DATE_EXP];
                    String pQty = res.split(SEPARATOR)[PRODUCT_QTY];
                    ((EditText) findViewById(R.id.et_name_product)).setText(pName);
                    ((EditText) findViewById(R.id.et_product_date)).setText(pDate);
                    ((EditText) findViewById(R.id.et_product_qty)).setText(pQty);
                    Toast.makeText(this, "Scan OK " + pName, Toast.LENGTH_LONG).show();

                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_inventory);
        inventoryId = getIntent().getIntExtra("IV_ID", 0);
        generateFragListProducts();
        TextView title = findViewById(R.id.title);
        title.setText(getIv(inventoryId).getName());


    }

    void generateFragListProducts() {
        Fragment frag = new ProductListFragment();
        Bundle args = new Bundle();
        args.putInt("IV_ID", inventoryId);
        frag.setArguments(args);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.frag_product_list, frag);
        ft.addToBackStack(null);
        ft.commit();
    }


    public void submit(View view) {
        String pName = ((EditText) findViewById(R.id.et_name_product)).getText().toString();
        String pQty = ((EditText) findViewById(R.id.et_product_qty)).getText().toString();
        String pDate = ((EditText) findViewById(R.id.et_product_date)).getText().toString();
        Product newP = new Product(pName, Integer.parseInt(pQty), pDate);
        getIv(inventoryId).addProduct(newP);
        generateFragListProducts();
    }

    public InventoryFactory getIv(int id) {
        return InventoriesSingleton.getInstance().getById(id);
    }



    public void back(View view) {
        Intent intent = new Intent(SelectedInventory.this, MyInventoriesActivity.class);
        startActivity(intent);
    }


    public void handleScanner(View view) {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
        options.setPrompt("Scan en cours");
        options.setCameraId(0);  // Use a specific camera of the device
        options.setBeepEnabled(true);
        options.setBarcodeImageEnabled(true);
        barcodeLauncher.launch(options);
    }
}





