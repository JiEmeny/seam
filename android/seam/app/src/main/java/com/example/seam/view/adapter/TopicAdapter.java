package com.example.seam.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assist.base.BaseApp;
import com.example.seam.R;
import com.example.seam.databinding.LayoutTopicItemBinding;
import com.example.seam.pojo.Serve;

import java.util.List;

/**
 * 工具类适配器
 *
 * @e-mail:2036573698@qq.com
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {
    private List<Serve.RowsBean> rowsBeans;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private int columnCount = 5;

    public TopicAdapter(Context context, List<Serve.RowsBean> rowsBeans) {
        this.rowsBeans = rowsBeans;
        this.context = context;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutTopicItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_topic_item, parent, false);
        return new TopicViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, final int position) {
        final Serve.RowsBean rowsBean = rowsBeans.get(position);
        holder.binding.title.setText(rowsBean.getName());
        Glide.with(context)
                .load(BaseApp.uri + rowsBean.getIngurl())
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.binding.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onTopicItemClick(rowsBean);
                }
            }
        });
        // 设置屏幕宽度
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        params.width = screenWidth / columnCount;
        holder.itemView.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return rowsBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        private final LayoutTopicItemBinding binding;

        public TopicViewHolder(LayoutTopicItemBinding view) {
            super(view.getRoot());
            this.binding = view;
        }
    }

    public interface OnItemClickListener {
        public void onTopicItemClick(Serve.RowsBean rowsBean);
    }
}
