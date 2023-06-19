package com.example.seam.view.ui.root;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.ViewModelProvider;

import com.example.assist.base.BaseApp;
import com.example.assist.base.NetworkActivity;
import com.example.seam.databinding.AddnoticeBinding;
import com.example.seam.pojo.User;
import com.example.seam.viewmodel.RootNoticeVM;
import com.example.seam.viewmodel.Root_Personal_VM;

/**
 * 发布通知
 *
 * @e-mail:2036573698@qq.com
 */
public class AddNotice extends NetworkActivity<AddnoticeBinding> {
    private RootNoticeVM model1;
    private Root_Personal_VM model2;

    @Override
    protected void onCreate() {
        model1 = new ViewModelProvider(this).get(RootNoticeVM.class);
        model2 = new ViewModelProvider(this).get(Root_Personal_VM.class);
    }

    @Override
    protected void onObserveData() {
        binding.toolbar.header.setText("发布通知");
        binding.toolbar.finish.setText("发布");
        binding.toolbar.back.setOnClickListener(v -> {
            finish();
        });
        // 显示字数
        WordCount();
        // 发布通知
        binding.toolbar.finish.setOnClickListener(v -> {
            model2.setUserByUsername(BaseApp.sp.getString("username", ""));
            model2.userMutableLiveData.observe(this, user -> {
                User.RowsBean rowsBean = user.getRows().get(0);
                model1.AddNotice(0, binding.title.getText().toString(), binding.content.getText().toString(), rowsBean.getId(), rowsBean.getType());
                finish();
            });
        });
    }

    /**
     * 显示字数
     */
    private void WordCount() {
        // 标题
        binding.title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.wordCount1.setText(s.length() + "/20");
            }
        });
        // 内容
        binding.content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.wordCount2.setText(s.length() + "/200");
            }
        });
    }
}
