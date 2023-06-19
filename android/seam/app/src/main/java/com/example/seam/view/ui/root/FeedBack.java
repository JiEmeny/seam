package com.example.seam.view.ui.root;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.assist.base.NetworkActivity;
import com.example.seam.databinding.FeedbackBinding;
import com.example.seam.viewmodel.Root_Personal_VM;

import java.util.Objects;

/**
 * 意见反馈
 *
 * @e-mail:2036573698@qq.com
 */
public class FeedBack extends NetworkActivity<FeedbackBinding> {
    private Root_Personal_VM model;

    @Override
    protected void onCreate() {
        model = new ViewModelProvider(this).get(Root_Personal_VM.class);
        binding.toolbar.header.setText("意见反馈");
        binding.toolbar.back.setOnClickListener(v -> {
            finish();
        });
        // 提交意见反馈
        binding.submit.setOnClickListener(v -> {
            model.setFeedBack(binding.title.getText().toString(), binding.content.getText().toString());
            if (model != null) {
                model.setFeedBackMutableLiveData.observe(this, setFeedBack -> {
                    if (Objects.equals(setFeedBack.getCode().toString(), "200")) {
                        Toast.makeText(mContext, "提交成功", Toast.LENGTH_SHORT).show();
                        // 提交成功之后清空输入框
                        binding.title.setText("");
                        binding.content.setText("");
                    }
                });
            }
        });
        // 字数显示
        WordCount();
    }

    /**
     * 显示输入框的字数
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

    @Override
    protected void onObserveData() {
    }
}
