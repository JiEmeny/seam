package com.example.seam.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.assist.base.BaseViewModel;
import com.example.seam.pojo.Course;
import com.example.seam.pojo.Curriculum;
import com.example.seam.pojo.Times;
import com.example.seam.respository.StudentRespository;

import retrofit2.Call;
import retrofit2.http.GET;

public class Student_VM extends BaseViewModel {
    public MutableLiveData<Course> curriculumMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Times> timesMutableLiveData = new MutableLiveData<>();

    /**
     * 查询课程
     */
    public void GetCourses() {
        StudentRespository.getInstance().GetCourses(curriculumMutableLiveData, failed);
    }

    /**
     * 查询全部上课时间
     */
    public void GetTime() {
        StudentRespository.getInstance().GetTime(timesMutableLiveData, failed);
    }
}
