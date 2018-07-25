package com.example.ksn_dev.syncserverworkshop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ksn_dev.syncserverworkshop.R;

public class FireBaseFullCRUDActivity extends AppCompatActivity {
    private Spinner spnnProductType;
    private EditText edtProductName;
    private Button btnAdd;
    private final String[] PRODUCT_TYPE = {
            "Mobile", "Web", "Embedded",
            "Graphic", "Circuit", "IOT"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_full_crud);

        initInstance();
    }

    private void initInstance() {
        edtProductName = findViewById(R.id.edit_ProductName);
        spnnProductType = findViewById(R.id.spinner_ProductType);
        btnAdd = findViewById(R.id.button_Add);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.spinner_item_product
        );

        /*spnnProductType.setAdapter(adapter);
        spnnProductType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
            }
        });
        spnnProductType.setSelection(0);*/
    }
}
