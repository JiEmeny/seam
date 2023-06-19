package com.example.seam.view.ui.root;

import androidx.lifecycle.ViewModelProvider;

import com.example.assist.base.NetworkActivity;
import com.example.seam.databinding.ModifyBinding;
import com.example.seam.viewmodel.Root_Personal_VM;

/**
 * 修改信息通用界面
 *
 * @e-mail:2036573698@qq.com
 */
public class Modify extends NetworkActivity<ModifyBinding> {
    private Root_Personal_VM model;

    @Override
    protected void onCreate() {
        int id = this.getIntent().getExtras().getInt("id");
        int uid = this.getIntent().getExtras().getInt("uid");
        model = new ViewModelProvider(this).get(Root_Personal_VM.class);
        binding.back.setOnClickListener(v -> {
            finish();
        });
        // 标题栏显示内容
        Show(id);
        // 保存
        binding.save.setOnClickListener(v -> {
            // 修改内容
            Amend(String.valueOf(uid), id);
        });
    }

    /**
     * 修改内容
     *
     * @param id
     */
    private void Amend(String uid, int id) {
        switch (id) {
            case 2:
                model.ModifyMsg(uid, "", binding.amend.getText().toString(), "", "", "", "");
                finish();
                break;
            case 3:
                model.ModifyMsg(uid, "", "", binding.amend.getText().toString(), "", "", "");
                finish();
                break;
            case 4:
                model.ModifyMsg(uid, "", "", "", binding.amend.getText().toString(), "", "");
                finish();
                break;
            case 5:
                model.ModifyMsg(uid, "", "", "", "", binding.amend.getText().toString(), "");
                finish();
                break;
            case 6:
                model.ModifyMsg(uid, "", "", "", "", "", binding.amend.getText().toString());
                finish();
                break;
            default:
                finish();
                break;
        }
    }

    /**
     * 标题栏显示内容
     *
     * @param id
     */
    private void Show(int id) {
        switch (id) {
            case 2:
                binding.header.setText("更改姓名");
                break;
            case 3:
                binding.header.setText("更改性别");
                break;
            case 4:
                binding.header.setText("更改联系电话");
                break;
            case 5:
                binding.header.setText("更改地址");
                break;
            case 6:
                binding.header.setText("更改身份证号");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onObserveData() {
    }
}
