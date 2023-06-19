package com.example.seam.util.retrofit;

import com.example.seam.pojo.ClassRooms;
import com.example.seam.pojo.Course;
import com.example.seam.pojo.CourseCheck;
import com.example.seam.pojo.Files;
import com.example.seam.pojo.Grade;
import com.example.seam.pojo.Login;
import com.example.seam.pojo.Mold;
import com.example.seam.pojo.Notice;
import com.example.seam.pojo.PassWord;
import com.example.seam.pojo.Register;
import com.example.seam.pojo.Serve;
import com.example.seam.pojo.SetFeedBack;
import com.example.seam.pojo.Tasks;
import com.example.seam.pojo.Times;
import com.example.seam.pojo.User;
import com.example.seam.pojo.Users;
import com.example.seam.view.ui.root.FeedBack;

import java.util.Map;
import java.util.Objects;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * @e-mail:2036573698@qq.com
 */
public interface RetrofitServiceUtil {
    /**
     * 根据id查询users
     *
     * @param id
     * @return Call<Users>
     */
    @GET("JiMoney/users/get_users/{id}")
    Call<Users> GetUsers(@Path("id") String id);

    /**
     * 查询users
     *
     * @return Call<Users>
     */
    @GET("JiMoney/users/get_users")
    Call<Users> GetUsers();

    /**
     * 根据username获取user
     *
     * @param username
     * @return Call<User>
     */
    @GET("JiMoney/index/get_user_username/{username}")
    Call<User> GetUserUsername(@Path("username") String username);

    /**
     * 根据username获取user
     *
     * @return Call<User>
     */
    @GET("JiMoney/index/get_user_username")
    Call<User> GetUserUsername();

    /**
     * 登录
     *
     * @return token
     */
    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("JiMoney/index/login")
    Call<Login> Login(@Body Login.RowsBean.UserBean userBean);

    /**
     * 修改密码
     *
     * @param map（oldpassword、newpassword）
     */
    @PUT("JiMoney/index/set_password")
    Call<PassWord> ChangePassword(@Body Map<String, String> map);

    /**
     * 添加意见反馈
     *
     * @param map（title，content）
     */
    @POST("JiMony/feedback/add_feedback")
    Call<SetFeedBack> SetFeedBack(@Body Map<String, String> map);

    /**
     * 根据id修改用户信息
     *
     * @param map（avatar、name、sex、phone、address、idcard）
     */
    @PUT("JiMoney/users/modify")
    Call<Users> ModifyMsg(@Body Map<String, String> map);

    /**
     * 上传文件通用接口
     *
     * @param file
     * @return uri
     */
    @Multipart
    @POST("JiMony/file/postfile")
    Call<Files> UpLoad(@Part MultipartBody.Part file);

    /**
     * 查询通知
     *
     * @param id（可有可无）
     * @return Call<Notice>
     */
    @GET("JiMoney/notice/my/{id}")
    Call<Notice> MyById(@Path("id") String id);

    /**
     * 查询通知
     *
     * @return Call<Notice>
     */
    @GET("JiMoney/notice/my")
    Call<Notice> My();

    /**
     * 通过id查询通知
     *
     * @return Call<Notice>
     */
    @GET("JiMoney/notice/my/{id}")
    Call<Notice> My(@Path("id") Integer id);

    /**
     * 根据id修改查看状态
     *
     * @param map（id，state）
     */
    @PUT("JiMoney/notice/modify_see_type")
    Call<Notice> ModifySeeType(@Body Map<String, Integer> map);

    /**
     * 添加通知
     *
     * @param map（state、title、content、userid、type）
     */
    @POST("JiMoney/notice/add_notice")
    Call<Notice> AddNotice(@Body Map<String, String> map);

    /**
     * 根据id删除通知
     *
     * @param map（id）
     */
    @HTTP(method = "DELETE", path = "JiMoney/notice/del_notice", hasBody = true)
    Call<Notice> DelNotice(@Body Map<String, Integer> map);

    /**
     * 查询全部签到
     *
     * @return Call<Register>
     */
    @GET("JiMony/coursecheck/look_register")
    Call<Register> LookRegister();

    /**
     * 根据id查询课程
     *
     * @param id
     * @return Call<CourseCheck>
     */
    @GET("JiMony/curriculummanagement/sel_course/{id}")
    Call<CourseCheck> SelCourseById(@Path("id") String id);

    /**
     * 根据id查询课程
     *
     * @param id
     * @return Call<Course>
     */
    @GET("JiMony/course/get_course/{id}")
    Call<Course> GetCourse(@Path("id") Integer id);

    /**
     * 查询课程
     *
     * @return Call<Course>
     */
    @GET("JiMony/course/get_course")
    Call<Course> GetCourses();

    /**
     * 获取全部服务
     *
     * @return Call<Serve>
     */
    @GET("/JiEmony/serve/get_serve")
    Call<Serve> GetServe();
    /**
     * 查询全部上课时间
     *
     * @returnCall<Times>
     */
    @GET("JiMony/time/get_time")
    Call<Times> GetTime();
    /**
     * ============================================================
     */
    /**
     * 根据id修改课程
     *
     * @param map（id、courses）
     */
    @PUT("JiMony/course/modify_course")
    Call<Course> ModifyCourse(@Body Map<String, String> map);

    /**
     * 根据id修改签到状态
     *
     * @param map（signed、id）
     */
    @PUT("JiMony/coursecheck/nodify_coursecheck")
    Call<Register> ModifyCourseCheck(@Body Map<String, Integer> map);

    /**
     * 根据id获取users
     *
     * @param id
     * @return Call<Users>
     */
    @GET("JiMoney/users/get_users/{id}")
    Call<Users> GetUsersBuId(@Path("id") String id);

    /**
     * 添加签到
     *
     * @param map（userid、signed、curriculummanagementid）
     */
    @POST("JiMony/coursecheck/add_coursecheck")
    Call<Object> AddCoursecheck(@Body Map<String, Integer> map);

    /**
     * 通用查找文件，不带参数可以查全部
     *
     * @param id
     * @return Call<Objects>
     */
    @GET("JiMony/file/getfile/{id}")
    Call<Objects> GetFile(@Path("id") String id);

    /**
     * 查询类型说明
     *
     * @param id
     * @return Call<Mold>
     */
    @GET("JiMoney/mold/get_mold/{id}")
    Call<Mold> GetMold(@Path("id") String id);

    /**
     * 获取全部用户
     *
     * @return Call<User>
     */
    @GET("JiMoney/index/get_user")
    Call<User> GetUser();

    /**
     * 查询课程
     *
     * @return Call<CourseCheck>
     */
    @GET("JiMony/curriculummanagement/sel_course")
    Call<CourseCheck> SelCourse();

    /**
     * 查询全部意见反馈
     *
     * @return Call<FeedBack>
     */
    @GET("JiMony/feedback/get_feedback")
    Call<FeedBack> GetFeedBack();

    /**
     * 根据用户名行该用户权限
     *
     * @param map（username、type）
     */
    @PUT("JiMoney/index/set_type")
    Call<User> SetType(@Body Map<String, String> map);

    /**
     * 注册账户
     *
     * @param map（username、password）
     */
    @POST("JiMoney/index/set_user")
    Call<User> SetUser(@Body Map<String, String> map);

    /**
     * 根据username删除user
     *
     * @param map（username）
     */
    @HTTP(method = "DELETE", path = "JiMoney/users/userdel", hasBody = true)
    Call<User> UserDel(@Body Map<String, String> map);



    /**
     * 查询课程
     *
     * @return Call<Course>
     */
    @GET("JiMony/course/get_course")
    Call<Course> GetCourse();

    /**
     * 添加课程
     *
     * @param map（courses）
     */
    @POST("JiMony/course/set_course")
    Call<Course> SetCourse(@Body Map<String, String> map);

    /**
     * 根据id删除课程
     *
     * @param map（id）
     */
    @HTTP(method = "DELETE", path = "JiMony/course/delete_course", hasBody = true)
    Call<Course> DeleteCourse(@Body Map<String, String> map);

    /**
     * 添加班级
     *
     * @param map（grades）
     */
    @POST("JiMoney/grade/set_grade")
    Call<Grade> SetGrade(@Body Map<String, String> map);

    /**
     * 查询班级
     *
     * @return Call<Grade>
     */
    @GET("JiMoney/grade/get_grade")
    Call<Grade> GetGrade();

    /**
     * 删除班级
     *
     * @param map（id）
     */
    @HTTP(method = "DELETE", path = "JiMoney/grade/del_grade", hasBody = true)
    Call<Grade> DelGrade(@Body Map<String, Integer> map);

    /**
     * 根据id修改班级
     *
     * @param map（id、grades）
     */
    @PUT("JiMoney/grade/modify_grade")
    Call<Grade> ModifyGrade(@Body Map<String, Integer> map);

    /**
     * 添加教室
     *
     * @param map（classrooms）
     */
    @POST("JiMoney/classrooms/add_classrooms")
    Call<ClassRooms> AddClassrooms(@Body Map<String, String> map);

    /**
     * 删除教室
     *
     * @param map（id）
     */
    @HTTP(method = "DELETE", path = "JiMoney/classrooms/del_classrooms", hasBody = true)
    Call<ClassRooms> DelClassrooms(@Body Map<String, String> map);

    /**
     * 修改教室
     *
     * @param map（id、classrooms）
     */
    @PUT("JiMoney/classrooms/modify_classrooms")
    Call<ClassRooms> ModifyClassrooms(@Body Map<String, String> map);

    /**
     * 根据id查询教室
     *
     * @param id
     * @return Call<ClassRooms>
     */
    @GET("JiMoney/classrooms/inquiry_classrooms/{id}")
    Call<ClassRooms> InquiryClassroomsById(@Path("id") Integer id);

    /**
     * 查询全部教室
     *
     * @return Call<ClassRooms>
     */
    @GET("JiMoney/classrooms/inquiry_classrooms")
    Call<ClassRooms> InquiryClassrooms();

    /**
     * 添加作业
     *
     * @param map（title、content、userid、answer、time、timeon、endtime、curriculummanagementid）
     */
    @POST("JiMoney/task/add_task")
    Call<Tasks> AddTask(@Body Map<String, String> map);

    /**
     * 根据id删除作业
     *
     * @param map（id）
     */
    @HTTP(method = "DELETE", path = "JiMoney/task/del_task", hasBody = true)
    Call<Tasks> DelTask(@Body Map<String, String> map);

    /**
     * 根据id修改作业
     *
     * @param map（title、content、answer、time、tomeon、endtime、id）
     */
    @PUT("JiMoney/task/job_change")
    Call<Tasks> JobChange(@Body Map<String, String> map);

    /**
     * 查询作业
     *
     * @param id
     * @return Call<Tasks>
     */
    @GET("JiMoney/task/get_task/{id}")
    Call<Tasks> GetTask(@Path("id") Integer id);

    /**
     * 查询作业
     *
     * @return Call<Tasks>
     */
    @GET("JiMoney/task/get_task/")
    Call<Tasks> GetTask();

    /**
     * 添加课程
     *
     * @param map（course、weeks、ms、userid、classroomid、gradeid）
     */
    @POST("JiMony/curriculummanagement/add_course")
    Call<Course> AddCourse(@Body Map<String, String> map);

    /**
     * 修改课程
     *
     * @param map（weeks、course、ms、userid、classroomid、gradeid、id）
     */
    @PUT("JiMony/curriculummanagement/nodify_course")
    Call<Course> NodifyCourse(@Body Map<String, String> map);

    /**
     * 删除课程
     *
     * @param map（id）
     */
    @HTTP(method = "DELETE", path = "JiMony/curriculummanagement/del_course", hasBody = true)
    Call<Course> DelCourse(@Body Map<String, String> map);

    /**
     * 根据id修改通知状态
     *
     * @param map（id、type）
     */
    @PUT("JiMoney/notice/modify_notice_type")
    Call<Notice> ModifyNoticeYype(@Body Map<String, String> map);
}
