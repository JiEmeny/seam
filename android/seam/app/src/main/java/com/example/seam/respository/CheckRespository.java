package com.example.seam.respository;

import androidx.lifecycle.MutableLiveData;

import com.example.seam.pojo.ClassRooms;
import com.example.seam.pojo.Course;
import com.example.seam.pojo.CourseCheck;
import com.example.seam.pojo.Register;
import com.example.seam.util.retrofit.RetrofitServiceUtil;
import com.example.seam.util.retrofit.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @e-mail:2036573698@qq.com
 */
public class CheckRespository {
    // 网络访问工具
    private RetrofitServiceUtil service = RetrofitUtil.retrofitServiceUtil();

    private static final class CheckRespositoryHolder {
        private static final CheckRespository CHECK_RESPOSITORY = new CheckRespository();
    }

    public static CheckRespository getInstance() {
        return CheckRespository.CheckRespositoryHolder.CHECK_RESPOSITORY;
    }

    /**
     * 根据id查询课程
     *
     * @param course
     * @param failed
     * @param id
     */
    public void GetCourse(MutableLiveData<Course> course, MutableLiveData<String> failed, Integer id) {
        service.GetCourse(id).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                course.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
            }
        });
    }

    /**
     * 根据id查询教室
     *
     * @param classRooms
     * @param failed
     * @param id
     */
    public void InquiryClassroomsById(MutableLiveData<ClassRooms> classRooms, MutableLiveData<String> failed, Integer id) {
        service.InquiryClassroomsById(id).enqueue(new Callback<ClassRooms>() {
            @Override
            public void onResponse(Call<ClassRooms> call, Response<ClassRooms> response) {
                classRooms.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ClassRooms> call, Throwable t) {
            }
        });
    }

    /**
     * 根据id查询课程
     *
     * @param courseCheck
     * @param failed
     * @param id
     */
    public void SelCourseById(MutableLiveData<CourseCheck> courseCheck, MutableLiveData<String> failed, String id) {
        service.SelCourseById(id).enqueue(new Callback<CourseCheck>() {
            @Override
            public void onResponse(Call<CourseCheck> call, Response<CourseCheck> response) {
                courseCheck.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CourseCheck> call, Throwable t) {
            }
        });
    }

    /**
     * 根据id进行签到
     *
     * @param register
     * @param failed
     * @param signed
     * @param id
     */
    public void ModifyCourseCheck(MutableLiveData<Register> register, MutableLiveData<String> failed, Integer signed, Integer id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("signed", signed);
        map.put("id", id);
        service.ModifyCourseCheck(map).enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                register.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
            }
        });
    }

    /**
     * 添加签到
     *
     * @param object
     * @param failed
     * @param userid
     * @param signed
     * @param curriculummanagementid
     */
    public void AddCoursecheck(MutableLiveData<Object> object, MutableLiveData<String> failed, int userid, int signed, int curriculummanagementid) {
        Map<String, Integer> map = new HashMap<>();
        map.put("userid", userid);
        map.put("signed", signed);
        map.put("curriculummanagementid", curriculummanagementid);
        service.AddCoursecheck(map).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                object.postValue(response);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
            }
        });
    }

    /**
     * 查询签到
     *
     * @param register
     * @param failed
     */
    public void LookRegister(MutableLiveData<Register> register, MutableLiveData<String> failed) {
        service.LookRegister().enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                register.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
            }
        });
    }
}
