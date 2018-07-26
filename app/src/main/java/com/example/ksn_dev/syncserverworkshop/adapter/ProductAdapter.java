package com.example.ksn_dev.syncserverworkshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ksn_dev.syncserverworkshop.R;
import com.example.ksn_dev.syncserverworkshop.dao.ProductItem;

import java.util.List;

public class ProductAdapter extends ArrayAdapter {
    private Context mContext;
    private List<ProductItem> mItem;

    public ProductAdapter(@NonNull Context context, List<ProductItem> items) {
        super(context, 0, items);
        mItem = items;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mItem.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        ViewHolder vHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(
                    R.layout.custom_list_item, parent, false
            );
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ProductItem item = mItem.get(position);
        vHolder.tvProductId.setText(item.getProductId());
        vHolder.tvProductName.setText(item.getProductName());
        vHolder.tvProductType.setText(item.getProductType());

        return convertView;
    }


    public class ViewHolder{
        public TextView tvProductId;
        public TextView tvProductType;
        public TextView tvProductName;

        public ViewHolder(View cv) {
            tvProductId = cv.findViewById(R.id.text_id);
            tvProductType = cv.findViewById(R.id.text_productType);
            tvProductName = cv.findViewById(R.id.text_productName);
        }
    }

}
