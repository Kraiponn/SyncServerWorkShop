package com.example.ksn_dev.syncserverworkshop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ksn_dev.syncserverworkshop.R;
import com.example.ksn_dev.syncserverworkshop.dao.SkillItemCollectionDao;
import com.example.ksn_dev.syncserverworkshop.dao.SkillItemDao;

import java.util.List;

public class SkillDataAdapter
        extends RecyclerView.Adapter<SkillDataAdapter.SkillViewHolder>{
    private SkillItemCollectionDao Dao;

    public void setmDao(SkillItemCollectionDao Dao) {
        this.Dao = Dao;
    }

    public SkillDataAdapter() {
    }

    public SkillDataAdapter(SkillItemCollectionDao mDao) {
        this.Dao = mDao;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(
                R.layout.rx_retrofit_custom_item, parent, false
        );
        SkillViewHolder vHolder = new SkillViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        SkillItemDao item = Dao.getData().get(position);
        holder.tvId.setText(String.valueOf(item.getId()));
        holder.tvTitle.setText(item.getTitle());
        holder.tvDetail.setText(item.getDetail());
    }

    @Override
    public int getItemCount() {
        if (Dao.getData() == null) {
            Log.d("Dao", "-- NULL NULL --");
            return 0;
        } else if (Dao.getData().size() == 0) {
            Log.d("Dao", "-- Zero 0 --");
            return 0;
        } else {
            Log.d("Dao", "Size " + Dao.getData().size());
            return Dao.getData().size();
        }
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId;
        public TextView tvTitle;
        public TextView tvDetail;

        public SkillViewHolder(View cv) {
            super(cv);
            tvId = cv.findViewById(R.id.text_id);
            tvTitle = cv.findViewById(R.id.text_title);
            tvDetail = cv.findViewById(R.id.text_detail);
        }
    }

}
