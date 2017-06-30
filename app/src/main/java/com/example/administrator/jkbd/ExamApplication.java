package com.example.administrator.jkbd;

import android.app.Application;
import android.util.Log;

import com.example.administrator.jkbd.bean.Question;
import com.example.administrator.jkbd.bean.item;
import com.example.administrator.jkbd.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by 李攀 on 2017/6/30.
 */

public class ExamApplication extends Application {
    item mitem;
    List<Question>mExamList;
    private static ExamApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

        initData();
    }
    public static ExamApplication getInstance(){
        return instance;
    }

    private void initData() {
        OkHttpUtils<item> utils=new OkHttpUtils<>(instance);
        String uri="http://101.251.196.90:8080/JztkServer/examInfo";
        utils.url(uri)
                .targetClass(item.class)
                .execute(new OkHttpUtils.OnCompleteListener<item>(){

                    @Override
                    public void onSuccess(item result) {
                        Log.e("main","result="+result);
                        mitem=result;

                    }

                    @Override
                    public void onError(String error) {
                        Log.e("main","error="+error);

                    }
                });
    }

    public item getMitem() {
        return mitem;
    }

    public void setMitem(item mitem) {
        this.mitem = mitem;
    }

    public List<Question> getmExamList() {
        return mExamList;
    }

    public void setmExamList(List<Question> mExamList) {
        this.mExamList = mExamList;
    }
}
