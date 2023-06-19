package com.example.seam.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seam.R;
import com.example.seam.databinding.RecyclerviewItemBinding;
import com.example.seam.pojo.Home_RV;

import java.util.ArrayList;

/**
 * 瀑布流适配器
 *
 * @e-mail:2036573698@qq.com
 */
public class AdapterHomeRecyclerView extends RecyclerView.Adapter<AdapterHomeRecyclerView.ViewHolder> {
    private ArrayList<Home_RV> home_rvs;

    public AdapterHomeRecyclerView(ArrayList<Home_RV> home_rvs) {
        this.home_rvs = home_rvs;
    }

    @NonNull
    @Override
    public AdapterHomeRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeRecyclerView.ViewHolder holder, int position) {
        holder.binding.image.setImageResource(home_rvs.get(position).getImage());
        holder.binding.setRv(home_rvs.get(position));
    }

    @Override
    public int getItemCount() {
        return home_rvs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerviewItemBinding binding;

        public ViewHolder(@NonNull RecyclerviewItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
