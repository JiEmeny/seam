package com.example.seam.view.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.assist.base.BaseApp;
import com.example.assist.base.NetworkActivity;
import com.example.seam.R;
import com.example.seam.databinding.StbyBinding;
import com.example.seam.pojo.Login;
import com.example.seam.util.retrofit.RetrofitServiceUtil;
import com.example.seam.util.retrofit.RetrofitUtil;
import com.example.seam.view.ui.root.Root;
import com.example.seam.view.ui.student.Student;
import com.example.seam.view.ui.teacher.Teacher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class STBY extends NetworkActivity<StbyBinding> {
    SharedPreferences.Editor edit;
    private RetrofitServiceUtil serviceUtil;
    // 倒计时
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate() {
        edit = BaseApp.sp.edit();
        serviceUtil = RetrofitUtil.retrofitServiceUtil();
    }

    @Override
    protected void onObserveData() {
        // 倒计时
        CountDown();
        // 点击跳过倒计时
        binding.countdown.setOnClickListener(v -> {
            // 判断
            Judge();
            // 销毁计时器
            countDownTimer.cancel();
        });
    }

    /**
     * 倒计时
     */
    private void CountDown() {
        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.countdown.setText("跳转\u3000|\u3000" + String.valueOf((int) millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                // 判断
                Judge();
            }
        };
        countDownTimer.start();
    }

    /**
     * 判断
     */
    private void Judge() {
        // 获取sp中存储的username和password
        String username = BaseApp.sp.getString("username", "");
        String password = BaseApp.sp.getString("password", "");
        // 自动登录验证
        if (!username.isEmpty() && !password.isEmpty()) {
            // 如果sp中保存username、password将进行自动登录
            // 初始化Login.RowsBean.UserBean
            com.example.seam.pojo.Login.RowsBean.UserBean userBean = new com.example.seam.pojo.Login.RowsBean.UserBean();
            // 进行存放数据
            userBean.setUsername(username);
            userBean.setPassword(password);
            serviceUtil
                    .Login(userBean)
                    .enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(Call<Login> call, Response<Login> response) {
                            System.out.println("=================" + response.body().toString());
                            System.out.println("=================" + response.body().getRows().toString());
                            System.out.println("=================" + response.body().getRows().getToken());
                            // 保存token
                            edit.putString("token", response.body().getRows().getToken());
                            edit.putString("username", username);
                            edit.putString("password", password);
                            edit.apply();
                            // 根据权限就行跳转页面
                            Jump(response.body().getRows().getUser().get(0).getType());
                        }

                        @Override
                        public void onFailure(Call<com.example.seam.pojo.Login> call, Throwable t) {
                        }
                    });
        } else {
            // 清空sp
            edit.clear();
            edit.commit();
            startActivity(new Intent(STBY.this, com.example.seam.view.ui.Login.class));
            finish();
        }
    }

    /**
     * 根据账号权限就行跳转页面
     *
     * @param type
     */
    private void Jump(int type) {
        switch (type) {
            case 0:
                // 管理人员
                startActivity(new Intent(STBY.this, Root.class));
                finish();
                break;
            case 1:
                // 老师
                startActivity(new Intent(STBY.this, Teacher.class));
                finish();
                break;
            case 2:
                // 学生
                startActivity(new Intent(STBY.this, Student.class));
                finish();
                break;
        }
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        // 销毁计时器
        countDownTimer.cancel();
        super.onDestroy();
    }
}
