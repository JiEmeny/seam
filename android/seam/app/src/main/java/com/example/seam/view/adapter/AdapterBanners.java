package com.example.seam.view.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seam.pojo.Banners;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 轮播图适配器
 *
 * @e-mail:2036573698@qq.com
 */
public class AdapterBanners extends BannerAdapter<Banners, AdapterBanners.ViewHolder> {
    public AdapterBanners(List<Banners> datas) {
        super(datas);
    }

    @Override
    public AdapterBanners.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindView(AdapterBanners.ViewHolder holder, Banners data, int position, int size) {
        holder.imageView.setImageResource(data.getImage());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            this.imageView = itemView;
        }
    }
}
