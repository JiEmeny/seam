    /**
     * 用于登录
     */
    @PostMapping("/JiMoney/index/login")
    {"username":"","password":""}
     /**
     * 根据username获取user
     */
    @GetMapping("/get_user_username{username}")
    /**
     * 获取全部用户
     */
    @GetMapping("/JiMoney/index/get_user")
    /**
     * 修改密码
     */
    @PutMapping("/JiMoney/index/set_password")
    {"oldpassword":"","newpassword":""}
    /**
     * 根据用户名修改用户权限
     */
    @PutMapping("/JiMoney/index/set_type")
    {"username":"","type":""}
    /**
     * 注册账户
     */
    @PostMapping("/JiMoney/index/set_user")
    {"username","","password":""}
    /**
     * 查询用户信息
     */
    @GetMapping("/JiMoney/users/get_users/{id}")
    /**
     * 根据id修改用户信息
     */
    @PutMapping("/JiMoney/users/modify")
    {"id":"","avatar":"","name":"","sex":"","phone":"","address":"","idcard":""}
    /**
     * 删除用户根据username
     */
    @DeleteMapping("/JiMoney/users/userdel")
    {"username":""}
    /**
     * 查询类型说明
     */
    @GetMapping("/JiMoney/mold/get_mold/{id}")
    /**
     * 查询全部上课时间
     */
    @GetMapping("/JiMony/time/get_time")
    /**
     * 查询课程
     */
    @GetMapping("/JiMony/course/get_course/{id}")
    /**
     * 添加课程
     */
    @PostMapping("/JiMony/course/set_course")
    {"courses":""}
    /**
     * 删除课程
     */
    @DeleteMapping("/JiMony/course/delete_course")
    {"id":""}
    /**
     * 修改课程
     */
    @PutMapping("/JiMony/course/modify_course")
    {"id":"","courses":""}
    /**
     * 添加班级
     */
    @PostMapping("/JiMoney/grade/set_grade")
    {"grades":""}
    /**
     * 删除班级
     */
    @DeleteMapping("/JiMoney/grade/del_grade")
    {"id":""}
    /**
     * 修改班级
     */
    @PutMapping("/JiMoney/grade/modify_grade")
    {"id":"","grades":""}
    /**
     * 查询班级
     */
    @GetMapping("/JiMoney/grade/get_grade")
    /**
     * 添加教室
     */
    @PostMapping("/JiMoney/classrooms/add_classrooms")
    {"classrooms":""}
    /**
     * 删除教室
     */
    @DeleteMapping("/JiMoney/classrooms/del_classrooms")
    {"id":""}
    /**
     * 修改教室
     */
    @PutMapping("/JiMoney/classrooms/modify_classrooms")
    {"id":"","classrooms":""}
    /**
     * 查询教室
     */
    @GetMapping("/JiMoney/classrooms/inquiry_classrooms/{id}")
    /**
     * 添加通知
     */
    @PostMapping("/JiMoney/notice/add_notice")
    {"state":"","title":"","content":"","userid":"","type":""}
    /**
     * 根据id修改查看状态
     */
    @PutMapping("/JiMoney/notice/modify_see_type")
    {"id":"","state":""}
    /**
     * 根据id修改通知状态
     */
    @PutMapping("/JiMoney/notice/modify_notice_type")
    {"id":"","type":""}
    /**
     * 根据id删除通知
     */
    @DeleteMapping("/JiMoney/notice/del_notice")
    {"id":""}
    /**
     * 查询通知（根据id、查询全部）
     */
    @GetMapping("/JiMoney/notice/my/{id}")
    /**
     * 添加作业
     */
    @PostMapping("/JiMoney/task/add_task")
    {"title":"","content":"","userid":"","answer":"","time":"","timeon":"","endtime":"","curriculummanagementid":""}
    /**
     * 根据id删除作业
     */
    @DeleteMapping("/JiMoney/task/del_task")
    {"id":""}
    /**
     * 根据id修改作业
     */
    @PutMapping("/JiMoney/task/job_change")
    {"title":"","content":"","answer":"","time":"","tomeon":"","endtime":"","id":""}
    /**
     * 查询作业
     */
    @GetMapping("/JiMoney/task/get_task/{id}")
    /**
     * 添加签到
     */
    @PostMapping("/JiMony/coursecheck/add_coursecheck")
    {"userid":"","signed":"","curriculummanagementid":""}

    /**
     * 根据id修改签到状态
     */
    @PutMapping("/JiMony/coursecheck/nodify_coursecheck")
    {"signed":"","id":""}
    /**
     * 查询签到
     */
    @GetMapping("/JiMony/coursecheck/look_register/{id}")
    /**
     * 查询课程
     */
    @GetMapping("/JiMony/curriculummanagement/sel_course/{id}")
    /**
     * 添加课程
     */
    @PostMapping("/JiMony/curriculummanagement/add_course")
    {"course":"","weeks":"","ms":"","userid":"","classroomid":"","gradeid":""}
    /**
     * 修改课程
     */
    @PutMapping("/JiMony/curriculummanagement/nodify_course")
    {"weeks":"","course":"","ms":"","userid":"","classroomid":"","gradeid":"","id":""}
    /**
     * 删除课程
     */
    @DeleteMapping("/JiMony/curriculummanagement/del_course")
    {"id":""}
    /**
     * 文件上传通用接口
     * File
     */
    @PostMapping("/JiMony/file/postfile")
    /**
     * 通用查找文件
     */
    @GetMapping("/JiMony/file/getfile/{id}")
    package com.example.seam.controller;
    /**
     * 查询全部意见反馈
     */
    @GetMapping("/JiMony/feedback/get_feedback")
    /**
     * 新增意见看反馈
     */
    @PostMapping("/JiMony/feedback/add_feedback")
    /**
     * 获取全部服务
     */
    @GetMapping("/JiEmony/serve/get_serve")
