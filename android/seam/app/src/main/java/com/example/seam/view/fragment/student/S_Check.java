package com.example.seam.view.fragment.student;

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

import com.example.seam.databinding.RootCheckBinding;
import com.example.seam.pojo.Course;
import com.example.seam.pojo.CourseCheck;
import com.example.seam.pojo.Register;
import com.example.seam.util.onClick;
import com.example.seam.view.adapter.AdapterRootCheck;
import com.example.seam.view.ui.root.Listing;
import com.example.seam.view.ui.student.T_Listing;
import com.example.seam.viewmodel.Root_Check_VM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签到
 *
 * @e-mail:2036573698@qq.com
 */
public class S_Check extends Fragment {
    private RootCheckBinding binding;
    private Root_Check_VM model;
    private Map<String, String> map = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RootCheckBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.header.setText("签到");
        model = new ViewModelProvider(this).get(Root_Check_VM.class);
        // 获取列表数据
        GetData();
        // 跳转到已经签到的列表
        binding.listing.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), T_Listing.class));
        });
    }

    /**
     * 获取列表数据
     */
    private void GetData() {
        // 获取签到列表表
        model.LookRegister();
        model.registerMutableLiveData.observe(getViewLifecycleOwner(), register -> {
            List<Register.RowsBean> rows = register.getRows();
            // 抛出异常防止NullPointerException
            try {
                for (Register.RowsBean row : rows) {
                    // 根据签到列表获取课程管理列表
                    model.SelCourseById(String.valueOf(row.getCurriculummanagementid()));
                    model.courseCheckMutableLiveData.observe(getViewLifecycleOwner(), courseCheck -> {
                        CourseCheck.RowsBean rowsBean = courseCheck.getRows().get(0);
                        // 根据课程管理列表获取签到的课程名称
                        model.GetCourse(rowsBean.getCourse());
                        model.courseMutableLiveData.observe(getViewLifecycleOwner(), course -> {
                            Course.RowsBean rowsBean1 = course.getRows().get(0);
                            // 判断是否签到了，未签到存放到map中
                            if (row.getSigned() == 0) {
                                map.put(String.valueOf(row.getId()), rowsBean1.getCourses());
                            }
                            // 判断运行RecyclerView
                            if (row.getId() == rows.get(rows.size() - 1).getId()) {
                                // RecyclerView
                                RV(map, rows);
                            }
                        });
                    });
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * RecyclerView
     *
     * @param map
     * @param rows
     */
    private void RV(Map<String, String> map, List<Register.RowsBean> rows) {
        AdapterRootCheck adapterRootCheck = new AdapterRootCheck(map, rows, getContext());
        binding.recyclerView.setAdapter(adapterRootCheck);
        GridLayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(manager);
        // 通过接口实现点击进行签到
        adapterRootCheck.click(new onClick() {
            @Override
            public void Click(int posotopn) {
                // 在map中移除该项
                adapterRootCheck.removeItemByPosition(posotopn);
                // 签到该项
                model.ModifyCourseCheck(1, posotopn);
            }
        });
    }
}
