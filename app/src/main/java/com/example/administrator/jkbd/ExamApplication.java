package com.example.administrator.jkbd;

import android.app.Application;
import android.util.Log;

import com.example.administrator.jkbd.bean.Question;
import com.example.administrator.jkbd.bean.Result;
import com.example.administrator.jkbd.bean.item;
import com.example.administrator.jkbd.utils.OkHttpUtils;
import com.example.administrator.jkbd.utils.ResultUtils;

import java.util.List;

/**
 * Created by 李攀 on 2017/6/30.
 */

public class ExamApplication extends Application {
    item mitem;
    List<Question> mQuestionList;
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
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                OkHttpUtils<String> utils1=new OkHttpUtils<>(instance);
                String url2="http://101.251.196.90:8080/JztkServer/getQuestions?testType=rand";
                utils1.url(url2)
                        .targetClass(String.class)
                        .execute(new OkHttpUtils.OnCompleteListener<String>(){

                            @Override
                            public void onSuccess(String jsonStr) {
                                Result result = ResultUtils.getListResultFromJson(jsonStr);
                                if(result!=null && result.getError_code()==0){
                                    List<Question> list=result.getQuestions();
                                    if (list!=null && list.size()>0){
                                        mQuestionList =list;
                                    }
                                }
                            }

                            @Override
                            public void onError(String error) {
                                Log.e("main","error="+error);

                            }
                        });
            }
        }).start();


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
