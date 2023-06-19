package com.example.seam.view.ui.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.assist.base.BaseApp;
import com.example.assist.base.NetworkActivity;
import com.example.seam.databinding.ChangePasswordBinding;
import com.example.seam.pojo.Login;
import com.example.seam.view.ui.root.ChangePassword;
import com.example.seam.viewmodel.Root_Personal_VM;

import java.util.Objects;

/**
 * 修改密码
 *
 * @e-mail:2036573698@qq.com
 */
public class S_ChangePassword extends NetworkActivity<ChangePasswordBinding> {
    private Root_Personal_VM model;

    @Override
    protected void onCreate() {
        model = new ViewModelProvider(this).get(Root_Personal_VM.class);

    }

    @Override
    protected void onObserveData() {
        binding.back.setOnClickListener(v -> {
            finish();
        });
        binding.header.setText("修改密码");
        binding.finish.setOnClickListener(v -> {
            // 判断输入框是否为空
            if (binding.old.getText().toString() != null || binding.old.getText().toString() != null || binding.old.getText().toString() != null) {
                // 判断两次新密码是否相同
                if (Objects.equals(binding.new1.getText().toString(), binding.new2.getText().toString())) {
                    // 修改密码提交数据
                    model.setUserByPassword(binding.old.getText().toString(), binding.new1.getText().toString());
                    if (model != null) {
                        model.passWordMutableLiveData.observe(this, p -> {
                            String code = p.getCode();
                            //判断是否修改成功（返回200修改成功）
                            if (Objects.equals(code, "200")) {
                                Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = BaseApp.sp.edit();
                                editor.clear();
                                editor.commit();
                                startActivity(new Intent(S_ChangePassword.this, Login.class));
                                finish();
                            }
                        });
                    }
                } else {
                    Toast.makeText(mContext, "两次新密码不同", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(mContext, "请输入原密码或新密码", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
