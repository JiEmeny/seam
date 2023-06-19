package com.example.seam.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.assist.base.BaseViewModel;
import com.example.seam.pojo.ClassRooms;
import com.example.seam.pojo.Course;
import com.example.seam.pojo.CourseCheck;
import com.example.seam.pojo.Register;
import com.example.seam.respository.CheckRespository;

/**
 * @e-mail:2036573698@qq.com
 */
public class Root_Check_VM extends BaseViewModel {
    public MutableLiveData<Register> registerMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Object> objectMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Course> courseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ClassRooms> classRoomsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<CourseCheck> courseCheckMutableLiveData = new MutableLiveData<>();

    /**
     * 根据id查询课程
     *
     * @param id
     */
    public void GetCourse(Integer id) {
        CheckRespository.getInstance().GetCourse(courseMutableLiveData, failed, id);
    }

    /**
     * 根据id查询教室
     *
     * @param id
     */
    public void InquiryClassroomsById(Integer id) {
        CheckRespository.getInstance().InquiryClassroomsById(classRoomsMutableLiveData, failed, id);
    }

    /**
     * 根据id查询课程
     *
     * @param id
     */
    public void SelCourseById(String id) {
        CheckRespository.getInstance().SelCourseById(courseCheckMutableLiveData, failed, id);
    }

    /**
     * 添加签到
     *
     * @param userid
     * @param signed
     * @param curriculummanagementid
     */
    public void AddCoursecheck(int userid, int signed, int curriculummanagementid) {
        CheckRespository.getInstance().AddCoursecheck(objectMutableLiveData, failed, userid, signed, curriculummanagementid);
    }

    /**
     * 根据id进行签到
     *
     * @param signed
     * @param id
     */
    public void ModifyCourseCheck(Integer signed, Integer id) {
        CheckRespository.getInstance().ModifyCourseCheck(registerMutableLiveData, failed, signed, id);
    }

    /**
     * 查询签到
     */
    public void LookRegister() {
        CheckRespository.getInstance().LookRegister(registerMutableLiveData, failed);
    }
}
