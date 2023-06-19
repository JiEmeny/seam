package com.example.seam.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seam.R;
import com.example.seam.databinding.AdapterrootnoticeBinding;
import com.example.seam.pojo.Notice;
import com.example.seam.util.onClick;

import java.util.List;

/**
 * 通知列表适配器
 *
 * @e-mail:2036573698@qq.com
 */
public class AdapterRootNotice extends RecyclerView.Adapter<AdapterRootNotice.ViewHolder> {
    private List<Notice.RowsBean> rowsBeans;
    private Context context;

    public AdapterRootNotice(List<Notice.RowsBean> rowsBeans, Context context) {
        this.rowsBeans = rowsBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRootNotice.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterrootnoticeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapterrootnotice, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRootNotice.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.binding.setR(rowsBeans.get(position));
        holder.binding.state.setText(rowsBeans.get(position).getState() == 0 ? "未查看" : "已查看");
        holder.binding.constraintLayout.setOnClickListener(v -> {
            onClick.Click(position);
        });
    }

    @Override
    public int getItemCount() {
        return rowsBeans == null ? 0 : rowsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AdapterrootnoticeBinding binding;

        public ViewHolder(@NonNull AdapterrootnoticeBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public onClick onClick;

    public void click(onClick onClicks) {
        this.onClick = onClicks;
    }
}
