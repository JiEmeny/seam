package com.example.seam.view.ui.student;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.assist.base.BaseApp;
import com.example.assist.base.NetworkActivity;
import com.example.seam.R;
import com.example.seam.databinding.SDetailedinformationBinding;
import com.example.seam.pojo.User;
import com.example.seam.pojo.Users;
import com.example.seam.util.UpLoadFileUtil;
import com.example.seam.viewmodel.Root_Personal_VM;

import java.util.List;

/**
 * 个人信息
 *
 * @e-mail:2036573698@qq.com
 */
public class S_DetailedInformation extends NetworkActivity<SDetailedinformationBinding> {
    private Root_Personal_VM model;
    private int uid;

    @Override
    protected void onCreate() {
        model = new ViewModelProvider(this).get(Root_Personal_VM.class);
    }

    @Override
    protected void onObserveData() {
        binding.toolbar.header.setText("个人信息");
        binding.toolbar.back.setOnClickListener(v -> {
            finish();
        });
        // 跳转界面，进行修改数据
        Jump();
        // 显示数据
        ShowData();
    }

    /**
     * 显示数据
     */
    private void ShowData() {
        model.setUserByUsername(BaseApp.sp.getString("username", ""));
        model.userMutableLiveData.observe(this, user -> {
            User u = user;
            if (u.getRows().size() > 0) {
                uid = u.getRows().get(0).getId();
                model.setUsersBuId(String.valueOf(uid));
                model.usersMutableLiveData.observe(this, users -> {
                    List<Users.RowsBean> usersRows = users.getRows();
                    if (usersRows.size() > 0) {
                        binding.name.setText(usersRows.get(0).getName());
                        binding.sex.setText(usersRows.get(0).getSex());
                        binding.phone.setText(usersRows.get(0).getPhone());
                        binding.adress.setText(usersRows.get(0).getAddress());
                        binding.idcar.setText(usersRows.get(0).getIdcard());
                        Glide.with(S_DetailedInformation.this)
                                .load(BaseApp.uri + usersRows.get(0).getAvatar())
                                .error(R.drawable.ic_launcher_foreground)
                                .into(binding.avatar);
                    }
                });
            }
        });
    }

    /**
     * 跳转（ 文件管理器、修改（name、sex、phone、adress、idcar））
     */
    private void Jump() {
        // avatar
        binding.cons1.setOnClickListener(v -> {
            // 打开图片文件管理器
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 0);
        });
        // name
        binding.cons2.setOnClickListener(v -> {
            Intent intent = new Intent(S_DetailedInformation.this, S_Modify.class);
            intent.putExtra("id", 2);
            intent.putExtra("uid", uid);
            startActivity(intent);
        });
        // sex
        binding.cons3.setOnClickListener(v -> {
            Intent intent = new Intent(S_DetailedInformation.this, S_Modify.class);
            intent.putExtra("id", 3);
            intent.putExtra("uid", uid);
            startActivity(intent);
        });
        // phone
        binding.cons4.setOnClickListener(v -> {
            Intent intent = new Intent(S_DetailedInformation.this, S_Modify.class);
            intent.putExtra("id", 4);
            intent.putExtra("uid", uid);
            startActivity(intent);
        });
        // adress
        binding.cons5.setOnClickListener(v -> {
            Intent intent = new Intent(S_DetailedInformation.this, S_Modify.class);
            intent.putExtra("id", 5);
            intent.putExtra("uid", uid);
            startActivity(intent);
        });
        // idcar
        binding.cons6.setOnClickListener(v -> {
            Intent intent = new Intent(S_DetailedInformation.this, S_Modify.class);
            intent.putExtra("id", 6);
            intent.putExtra("uid", uid);
            startActivity(intent);
        });
    }

    /**
     * 重新显示该界面刷新数据
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        // 修改之后返回刷新数据
        ShowData();
    }

    /**
     * 调用文件管理器之后运用该方法
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                if (this != null || uri != null) {
                    binding.avatar.setImageURI(uri);
                    DocumentFile documentFile = DocumentFile.fromSingleUri(S_DetailedInformation.this, uri);
                    if (documentFile != null) {
                        Up(UpLoadFileUtil.getFileAbsolutePath(this, uri), documentFile.getName());
                    }
                }
            }
        }
    }

    /**
     * 修改头像
     *
     * @param path
     * @param name
     */
    public void Up(String path, String name) {
        model.UpLoad(path, name);
        model.filesMutableLiveData.observe(this, files -> {
            model.ModifyMsg(String.valueOf(uid), files.getRows(), null, null, null, null, null);
            ShowData();
        });
    }
}
