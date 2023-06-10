package com.example.seam.util.retrofit;

import com.example.seam.pojo.Login;
import com.example.seam.pojo.Mold;
import com.example.seam.pojo.User;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * @FormUrlEncoded 指请求体是一个Form表单，Content-Type=application/x-www-form-urlencoded，需要和参数类注解@Field，@FieldMap搭配使用
 * @Multipart 指请求体是一个支持文件上传的Form表单，Content-Type=multipart/form-data，需要和参数类注解@Part，@PartMap搭配使用（详见下节）
 * @Streaming 指响应体的数据以流的形式返回，如果不使用默认会把数据全部加载到内存，所以下载文件时需要加上这个注解
 */

public interface RetrofitServiceUtil {
    /**
     * 查询类型说明
     *
     * @param id
     * @return
     */
    @GET("JiMoney/mold/get_mold/{id}")
    Call<Mold> GetMold(@Path("id") String id);

    /**
     * 登录用于
     *
     * @return token
     */
    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("JiMoney/index/login")
    Call<Login> Login(@Body Login.RowsBean.UserBean userBean);

    /**
     * 获取全部用户
     */
    @GET("/JiMoney/index/get_user")
    Call<User> GetUser();


    /**
     * 上传文件通用接口
     *
     * @param file
     * @return imageuri
     */
    @Multipart
    @POST("JiMony/file/postfile")
    Call<Object> UpLoad(@Part MultipartBody.Part file);

    /**
     * 通用查找文件
     * 不带参数可以查全部
     *
     * @param id
     * @return
     */
    @GET("JiMony/file/getfile/{id}")
    Map<String, Object> GetFile(@Part("id") String id);

    /**
     * 查询签到
     *
     * @param id
     * @return
     */
    @GET("JiMony/coursecheck/look_register/{id}")
    Map<String, Object> LookRegister(@Part("id") String id);

    /**
     * 查询课程
     *
     * @param id
     * @return
     */
    @GET("JiMony/curriculummanagement/sel_course/{id}")
    Map<String, Object> SelCourse(@Part("id") String id);
}

///**
// * 修改密码
// */
//@PutMapping("/JiMoney/index/set_password")
//    {"oldpassword":"","newpassword":""}
///**
// * 根据用户名修改用户权限
// */
//@PutMapping("/JiMoney/index/set_type")
//    {"username":"","type":""}
///**
// * 注册账户
// */
//@PostMapping("/JiMoney/index/set_user")
//    {"username","","password":""}
///**
// * 查询用户信息
// */
//@GetMapping("/JiMoney/users/get_users/{id}")
///**
// * 根据id修改用户信息
// */
//@PutMapping("/JiMoney/users/modify")
//    {"id":"","avatar":"","name":"","sex":"","phone":"","address":"","idcard":""}
///**
// * 删除用户根据username
// */
//@DeleteMapping("/JiMoney/users/userdel")
//    {"username":""}

///**
// * 查询全部上课时间
// */
//@GetMapping("/JiMony/time/get_time")
///**
// * 查询课程
// */
//@GetMapping("/JiMony/course/get_course/{id}")
///**
// * 添加课程
// */
//@PostMapping("/JiMony/course/set_course")
//    {"courses":""}
///**
// * 删除课程
// */
//@DeleteMapping("/JiMony/course/delete_course")
//    {"id":""}
///**
// * 修改课程
// */
//@PutMapping("/JiMony/course/modify_course")
//    {"id":"","courses":""}
///**
// * 添加班级
// */
//@PostMapping("/JiMoney/grade/set_grade")
//    {"grades":""}
///**
// * 删除班级
// */
//@DeleteMapping("/JiMoney/grade/del_grade")
//    {"id":""}
///**
// * 修改班级
// */
//@PutMapping("/JiMoney/grade/modify_grade")
//    {"id":"","grades":""}
///**
// * 查询班级
// */
//@GetMapping("/JiMoney/grade/get_grade")
///**
// * 添加教室
// */
//@PostMapping("/JiMoney/classrooms/add_classrooms")
//    {"classrooms":""}
///**
// * 删除教室
// */
//@DeleteMapping("/JiMoney/classrooms/del_classrooms")
//    {"id":""}
///**
// * 修改教室
// */
//@PutMapping("/JiMoney/classrooms/modify_classrooms")
//    {"id":"","classrooms":""}
///**
// * 查询教室
// */
//@GetMapping("/JiMoney/classrooms/inquiry_classrooms/{id}")
///**
// * 添加通知
// */
//@PostMapping("/JiMoney/notice/add_notice")
//    {"state":"","title":"","content":"","userid":"","type":""}
///**
// * 根据id修改查看状态
// */
//@PutMapping("/JiMoney/notice/modify_see_type")
//    {"id":"","state":""}
///**
// * 根据id修改通知状态
// */
//@PutMapping("/JiMoney/notice/modify_notice_type")
//    {"id":"","type":""}
///**
// * 根据id删除通知
// */
//@DeleteMapping("/JiMoney/notice/del_notice")
//    {"id":""}
///**
// * 查询通知（根据id、查询全部）
// */
//@GetMapping("/JiMoney/notice/my/{id}")
///**
// * 添加作业
// */
//@PostMapping("/JiMoney/task/add_task")
//    {"title":"","content":"","userid":"","answer":"","time":"","timeon":"","endtime":"","curriculummanagementid":""}
///**
// * 根据id删除作业
// */
//@DeleteMapping("/JiMoney/task/del_task")
//    {"id":""}
///**
// * 根据id修改作业
// */
//@PutMapping("/JiMoney/task/job_change")
//    {"title":"","content":"","answer":"","time":"","tomeon":"","endtime":"","id":""}
///**
// * 查询作业
// */
//@GetMapping("/JiMoney/task/get_task/{id}")
///**
// * 添加签到
// */
//@PostMapping("/JiMony/coursecheck/add_coursecheck")
//    {"userid":"","signed":"","curriculummanagementid":""}
//
///**
// * 根据id修改签到状态
// */
//@PutMapping("/JiMony/coursecheck/nodify_coursecheck")
//    {"signed":"","id":"","curriculummanagementid":""}
//
///**
// * 添加课程
// */
//@PostMapping("/JiMony/curriculummanagement/add_course")
//    {"course":"","weeks":"","ms":"","userid":"","classroomid":"","gradeid":""}
///**
// * 修改课程
// */
//@PutMapping("/JiMony/curriculummanagement/nodify_course")
//    {"weeks":"","course":"","ms":"","userid":"","classroomid":"","gradeid":"","id":""}
///**
// * 删除课程
// */
//@DeleteMapping("/JiMony/curriculummanagement/del_course")
//    {"id":""}
