package com.example.seam.view.fragment.root;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.seam.databinding.RootNoticeBinding;
import com.example.seam.pojo.Notice;
import com.example.seam.util.onClick;
import com.example.seam.view.adapter.AdapterRootNotice;
import com.example.seam.view.ui.root.AddNotice;
import com.example.seam.view.ui.root.NoticeDatails;
import com.example.seam.viewmodel.RootNoticeVM;

import java.util.Comparator;
import java.util.List;

/**
 * 通知
 *
 * @e-mail:2036573698@qq.com
 */
public class Root_Notice extends Fragment {
    private RootNoticeBinding binding;
    private RootNoticeVM model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RootNoticeBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.header.setText("通知");
        model = new ViewModelProvider(this).get(RootNoticeVM.class);
        // 获取数据
        GetData();
        binding.add.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddNotice.class));
        });

    }

    /**
     * 获取数据，显示到RecyclerView上面
     */
    private void GetData() {
        model.My();
        model.noticeMutableLiveData.observe(getViewLifecycleOwner(), notice -> {
            List<Notice.RowsBean> rows = notice.getRows();
            rows.sort(new Comparator<Notice.RowsBean>() {
                @Override
                public int compare(Notice.RowsBean o1, Notice.RowsBean o2) {
                    return o2.getTime().compareTo(o1.getTime());
                }
            });
            // 让RecyclerView滑动起来更丝滑
            binding.recyclerView.setHasFixedSize(true);
            binding.recyclerView.setNestedScrollingEnabled(false);
            // RecyclerView
            AdapterRootNotice adapterRootNotice = new AdapterRootNotice(rows, getContext());
            binding.recyclerView.setAdapter(adapterRootNotice);
            GridLayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.recyclerView.setLayoutManager(manager);
            adapterRootNotice.click(new onClick() {
                @Override
                public void Click(int posotopn) {
                    Intent intent = new Intent(getContext(), NoticeDatails.class);
                    intent.putExtra("id", rows.get(posotopn).getId());
                    startActivity(intent);
                }
            });
        });
    }

    /**
     * 返回到该界面，刷新数据
     */
    @Override
    public void onStart() {
        super.onStart();
        GetData();
    }
}
