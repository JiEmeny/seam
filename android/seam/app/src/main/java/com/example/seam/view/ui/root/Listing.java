package com.example.seam.view.ui.root;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assist.base.NetworkActivity;
import com.example.seam.databinding.ListingBinding;
import com.example.seam.pojo.Course;
import com.example.seam.pojo.CourseCheck;
import com.example.seam.pojo.Register;
import com.example.seam.view.adapter.AdapterListing;
import com.example.seam.viewmodel.Root_Check_VM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签到列表
 *
 * @e-mail:2036573698@qq.com
 */
public class Listing extends NetworkActivity<ListingBinding> {
    private Root_Check_VM model;
    private Map<String, String> map;

    @Override
    protected void onCreate() {
        model = new ViewModelProvider(this).get(Root_Check_VM.class);
        map = new HashMap<>();
    }

    @Override
    protected void onObserveData() {
        binding.toolbar.header.setText("签到列表");
        binding.toolbar.back.setOnClickListener(v -> {
            finish();
        });
        // 获取全部签到列表
        GetData();
    }

    /**
     * 获取全部签到列表
     */
    private void GetData() {
        // 签到列表
        model.LookRegister();
        model.registerMutableLiveData.observe(this, register -> {
            List<Register.RowsBean> rows = register.getRows();
            for (Register.RowsBean row : rows) {
                // 课程列表
                model.SelCourseById(String.valueOf(row.getCurriculummanagementid()));
                model.courseCheckMutableLiveData.observe(this, courseCheck -> {
                    CourseCheck.RowsBean rowsBean = courseCheck.getRows().get(0);
                    // 课程管理列表
                    model.GetCourse(rowsBean.getCourse());
                    model.courseMutableLiveData.observe(this, course -> {
                        Course.RowsBean rowsBean1 = course.getRows().get(0);
                        map.put(String.valueOf(row.getId()), rowsBean1.getCourses());
                        if (row.getId() == rows.get(rows.size() - 1).getId()) {
                            // 执行RecyclerView
                            RV(map, rows);
                        }
                    });
                });
            }
        });
    }

    /**
     * 执行RecyclerView
     *
     * @param map
     * @param rows
     */
    private void RV(Map<String, String> map, List<Register.RowsBean> rows) {
        AdapterListing adapterRootCheck = new AdapterListing(map, rows, this);
        binding.recyclerView.setAdapter(adapterRootCheck);
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(manager);
    }
}
