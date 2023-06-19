package com.example.seam.view.ui.root;

import android.graphics.Color;

import androidx.lifecycle.ViewModelProvider;

import com.example.assist.base.NetworkActivity;
import com.example.seam.databinding.NoticedatailsBinding;
import com.example.seam.pojo.Notice;
import com.example.seam.viewmodel.RootNoticeVM;

/**
 * 通知详情页
 *
 * @e-mail:2036573698@qq.com
 */
public class NoticeDatails extends NetworkActivity<NoticedatailsBinding> {
    private RootNoticeVM model;
    private int id;

    @Override
    protected void onCreate() {
        model = new ViewModelProvider(this).get(RootNoticeVM.class);
        id = this.getIntent().getExtras().getInt("id");
        // 修改通知查看状态
        model.ModifySeeType(id, 1);
    }

    @Override
    protected void onObserveData() {
        binding.toolbar.header.setText("详情");
        binding.toolbar.finish.setText("删除");
        // 删除通知
        binding.toolbar.finish.setOnClickListener(v -> {
            model.DelNotice(id);
            finish();
        });
        binding.toolbar.finish.setBackgroundColor(Color.RED);
        binding.toolbar.back.setOnClickListener(v -> {
            finish();
        });
        model.My(id);
        // 获得数据显示view上
        model.noticeMutableLiveData.observe(this, notice -> {
            // 防止NullPointerException错误，将该错误抛出
            try {
                Notice.RowsBean rowsBean = notice.getRows().get(0);
                binding.setR(rowsBean);
                binding.type.setText(rowsBean.getType() == 3 ? "通知类型：教师通知" : "通知类型：校园通知");
                binding.uname.setText(rowsBean.getUserid() == 1 ? "发布者：管理员" : "发布者：教师");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
    }
}
