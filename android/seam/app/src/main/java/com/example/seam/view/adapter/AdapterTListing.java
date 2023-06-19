package com.example.seam.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seam.R;
import com.example.seam.databinding.AdapterSCheckBinding;
import com.example.seam.databinding.AdapterrootcheckBinding;
import com.example.seam.pojo.Register;

import java.util.List;
import java.util.Map;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seam.R;
import com.example.seam.databinding.AdapterrootcheckBinding;
import com.example.seam.pojo.Register;

import java.util.List;
import java.util.Map;

    /**
     * 全部签到列表适配器
     *
     * @e-mail:2036573698@qq.com
     */
    public class AdapterTListing extends RecyclerView.Adapter<AdapterTListing.ViewHolder> {
        private Map<String, String> map;
        private List<Register.RowsBean> rows;
        private Context context;

        public AdapterTListing(Map<String, String> map, List<Register.RowsBean> rows, Context context) {
            this.map = map;
            this.rows = rows;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            AdapterSCheckBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_s_check, parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.binding.setMap(map);
            holder.binding.setRows(rows.get(position));
            holder.binding.checkIn.setText(rows.get(position).getSigned() == 0 ? "未签到" : "已签到");
        }

        @Override
        public int getItemCount() {
            return map.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final AdapterSCheckBinding binding;

            public ViewHolder(@NonNull AdapterSCheckBinding itemView) {
                super(itemView.getRoot());
                this.binding = itemView;
            }
        }
    }
