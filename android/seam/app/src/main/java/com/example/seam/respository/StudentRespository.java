package com.example.seam.respository;

import androidx.lifecycle.MutableLiveData;

import com.example.seam.pojo.Course;
import com.example.seam.pojo.Curriculum;
import com.example.seam.pojo.Times;
import com.example.seam.util.retrofit.RetrofitServiceUtil;
import com.example.seam.util.retrofit.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRespository {
    // 网络访问工具
    private RetrofitServiceUtil service = RetrofitUtil.retrofitServiceUtil();

    private static final class StudentRespositoryHolder {
        private static final StudentRespository ROOT_NOTICE_RESPOSITORY = new StudentRespository();
    }

    public static StudentRespository getInstance() {
        return StudentRespository.StudentRespositoryHolder.ROOT_NOTICE_RESPOSITORY;
    }

    /**
     * 查询全部上课时间
     * @param times
     * @param failed
     */
    public void GetTime(MutableLiveData<Times> times, MutableLiveData<String> failed) {
        service.GetTime().enqueue(new Callback<Times>() {
            @Override
            public void onResponse(Call<Times> call, Response<Times> response) {
                times.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Times> call, Throwable t) {
            }
        });
    }

    /**
     * 查询课程
     *
     * @param course
     * @param failed
     */
    public void GetCourses(MutableLiveData<Course> course, MutableLiveData<String> failed) {
        service.GetCourses().enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                course.postValue(response.body());
            }
            @Override
            public void onFailure(Call<Course> call, Throwable t) {
            }
        });
    }
}
