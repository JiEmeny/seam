package com.example.seam.view.fragment.student;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.assist.base.BaseApp;
import com.example.seam.R;
import com.example.seam.databinding.RootPersonalBinding;
import com.example.seam.pojo.User;
import com.example.seam.pojo.Users;
import com.example.seam.view.ui.student.S_ChangePassword;
import com.example.seam.view.ui.student.S_DetailedInformation;
import com.example.seam.view.ui.student.S_FeedBack;
import com.example.seam.view.ui.student.S_Settings;
import com.example.seam.viewmodel.Root_Personal_VM;

import java.util.List;

/**
 * @e-mail:2036573698@qq.com
 */
public class S_Personal extends Fragment {
    private RootPersonalBinding binding;
    private Root_Personal_VM model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RootPersonalBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        View view = binding.getRoot();
        return view;
    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(this).get(Root_Personal_VM.class);
        // 跳转界面
        JumpUI();
        // 显示数据
        ShowData();
    }

    /**
     * 显示数据
     */
    private void ShowData() {
        model.setUserByUsername(BaseApp.sp.getString("username", ""));
        model.userMutableLiveData.observe(getViewLifecycleOwner(), user -> {
            User u = user;
            if (u.getRows().size() > 0) {
                model.setUsersBuId(String.valueOf(u.getRows().get(0).getId()));
                if (model != null) {
                    model.usersMutableLiveData.observe(getViewLifecycleOwner(), users -> {
                        List<Users.RowsBean> usersRows = users.getRows();
                        if (usersRows.size() != 0 || !usersRows.isEmpty()) {
                            binding.nickname.setText(usersRows.get(0).getName());
                            Glide.with(getContext())
                                    .load(BaseApp.uri + usersRows.get(0).getAvatar())
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(binding.headImage);
                        }
                    });
                }
            }
        });
    }

    /**
     * 跳转界面（设置、意见反馈、修改密码）
     */
    private void JumpUI() {
        // 设置
        binding.settings.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), S_Settings.class));
        });
        // 意见反馈
        binding.yjfk.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), S_FeedBack.class));
        });
        // 修改密码
        binding.xgmm.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), S_ChangePassword.class));
        });
        // 详细信息
        binding.constraintLayout.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), S_DetailedInformation.class));
        });
    }

    /**
     * 重新调用该界面刷新数据
     */
    @Override
    public void onResume() {
        super.onResume();
        ShowData();
    }
}
