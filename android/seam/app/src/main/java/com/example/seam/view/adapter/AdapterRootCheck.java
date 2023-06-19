package com.example.seam.view.adapter;

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
 * 未签到适配器
 *
 * @e-mail:2036573698@qq.com
 */
public class AdapterRootCheck extends RecyclerView.Adapter<AdapterRootCheck.ViewHolder> {
    private Map<String, String> map;
    private Context context;
    private List<Register.RowsBean> rows;

    public AdapterRootCheck(Map<String, String> map, List<Register.RowsBean> rows, Context context) {
        this.rows = rows;
        this.map = map;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRootCheck.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterrootcheckBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapterrootcheck, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRootCheck.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.binding.setMap(map);
        holder.binding.setRows(rows.get(position));
        holder.binding.checkIn.setOnClickListener(v -> {
            onClick.Click(rows.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return map == null ? 0 : map.size();
    }

    /**
     * 将map中的该项移除
     *
     * @param position
     */
    public void removeItemByPosition(int position) {
        map.remove(String.valueOf(position));
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AdapterrootcheckBinding binding;

        public ViewHolder(@NonNull AdapterrootcheckBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public com.example.seam.util.onClick onClick;

    public void click(com.example.seam.util.onClick onClick) {
        this.onClick = onClick;
    }
}
