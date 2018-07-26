package com.example.ksn_dev.syncserverworkshop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ksn_dev.syncserverworkshop.R;
import com.example.ksn_dev.syncserverworkshop.adapter.ProductAdapter;
import com.example.ksn_dev.syncserverworkshop.dao.ProductItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseFullCRUDActivity extends AppCompatActivity {
    private Spinner spnnProductType;
    private EditText edtProductName;
    private Button btnAdd;
    private final String[] PRODUCT_TYPE = {
            "Mobile", "Web", "Embedded",
            "Graphic", "Circuit", "IOT"
    };

    private DatabaseReference mProductReff;
    private List<ProductItem> mProductList;
    private ProductAdapter mAdapter;
    private ListView listView;

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
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.spinner_item_product, PRODUCT_TYPE
        );

        btnAdd.setOnClickListener(btnAddClickListener);
        spnnProductType.setAdapter(adapter);
        spnnProductType.setOnItemSelectedListener(spinnerItemSelect);
        spnnProductType.setSelection(0);

        mProductReff = FirebaseDatabase.getInstance().getReference("products");
        mProductList = new ArrayList<>();
    }


    private void addProductList() {
        String proName = edtProductName.getText().toString();
        String proType = spnnProductType.getSelectedItem().toString();
        String id = mProductReff.push().getKey();

        if (!TextUtils.isEmpty(proName)) {
            ProductItem item = new ProductItem(id, proName, proType);
            mProductReff.child(id).setValue(item);

            Toast.makeText(this,
                    "Add new item success",
                    Toast.LENGTH_SHORT).show();
            edtProductName.setText("");
            spnnProductType.setSelection(0);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mProductReff.addValueEventListener(fireBaseValueEventListener);
    }

    AdapterView.OnItemSelectedListener spinnerItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    View.OnClickListener btnAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addProductList();
        }
    };


    ValueEventListener fireBaseValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mProductList.clear();
            for (DataSnapshot proSnapShort : dataSnapshot.getChildren()) {
                ProductItem item = proSnapShort.getValue(ProductItem.class);
                mProductList.add(item);
            }

            mAdapter = new ProductAdapter(FireBaseFullCRUDActivity.this, mProductList);
            listView.setAdapter(mAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
