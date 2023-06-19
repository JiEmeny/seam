package com.example.seam.pojo;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "课程表")
public class Curriculum {
    @SmartColumn(id = 1, name = "时间",autoMerge = true)
    private String time;
    @SmartColumn(id = 2, name = "节次")
    private String times;
    @SmartColumn(id = 3, name = "星期一",autoMerge = true)
    private String Mon;
    @SmartColumn(id = 4, name = "星期二",autoMerge = true)
    private String Tues;
    @SmartColumn(id = 5, name = "星期三",autoMerge = true)
    private String Wed;
    @SmartColumn(id = 6, name = "星期四",autoMerge = true)
    private String Thur;
    @SmartColumn(id = 7, name = "星期五",autoMerge = true)
    private String Fri;

    public Curriculum() {
    }

    public Curriculum(String time, String times, String mon, String tues, String wed, String thur, String fri) {
        this.time = time;
        this.times = times;
        Mon = mon;
        Tues = tues;
        Wed = wed;
        Thur = thur;
        Fri = fri;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getMon() {
        return Mon;
    }

    public void setMon(String mon) {
        Mon = mon;
    }

    public String getTues() {
        return Tues;
    }

    public void setTues(String tues) {
        Tues = tues;
    }

    public String getWed() {
        return Wed;
    }

    public void setWed(String wed) {
        Wed = wed;
    }

    public String getThur() {
        return Thur;
    }

    public void setThur(String thur) {
        Thur = thur;
    }

    public String getFri() {
        return Fri;
    }

    public void setFri(String fri) {
        Fri = fri;
    }
}
