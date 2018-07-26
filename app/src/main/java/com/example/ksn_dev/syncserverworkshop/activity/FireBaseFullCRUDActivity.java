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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBaseFullCRUDActivity extends AppCompatActivity {
    private Spinner spnnProductType;
    private EditText edtProductName;
    private Button btnAdd;
    private final String[] PRODUCT_TYPE = {
            "Mobile", "Web", "Embedded",
            "Graphic", "Circuit", "IOT"
    };

    private DatabaseReference mProductReff;

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
                this, R.layout.spinner_item_product, PRODUCT_TYPE
        );

        spnnProductType.setAdapter(adapter);
        spnnProductType.setOnItemSelectedListener(spinnerItemSelect);
        spnnProductType.setSelection(0);

        mProductReff = FirebaseDatabase.getInstance().getReference("products");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mProductReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    AdapterView.OnItemSelectedListener spinnerItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}
