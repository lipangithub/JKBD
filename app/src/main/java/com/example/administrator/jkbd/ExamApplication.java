package com.example.administrator.jkbd;

import android.app.Application;
import android.util.Log;

import com.example.administrator.jkbd.bean.Question;
import com.example.administrator.jkbd.bean.Result;
import com.example.administrator.jkbd.bean.item;
import com.example.administrator.jkbd.biz.ExamBiz;
import com.example.administrator.jkbd.biz.IExamBiz;
import com.example.administrator.jkbd.utils.OkHttpUtils;
import com.example.administrator.jkbd.utils.ResultUtils;

import java.util.List;

/**
 * Created by 李攀 on 2017/6/30.
 */

public class ExamApplication extends Application {
    public static String LOAD_EXAM_INFO="load_axam_info";
    public static String LOAD_EXAM_QUESTION="load_axam_question";
    public static String LOAD_DATA_SUCCESS="load_data_success";

    item mitem;
    List<Question> mQuestionList;
    private static ExamApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public static ExamApplication getInstance(){
        return instance;
    }


    public item getMitem() {
        return mitem;
    }

    public void setMitem(item mitem) {
        this.mitem = mitem;
    }

    public List<Question> getmQuestionList() {
        return mQuestionList;
    }

    public void setmQuestionList(List<Question> mQuestionList) {
        this.mQuestionList = mQuestionList;
    }
}
