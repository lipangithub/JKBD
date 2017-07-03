package com.example.administrator.jkbd.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.jkbd.ExamApplication;
import com.example.administrator.jkbd.R;
import com.example.administrator.jkbd.bean.Question;
import com.example.administrator.jkbd.bean.item;
import com.example.administrator.jkbd.biz.ExamBiz;
import com.example.administrator.jkbd.biz.IExamBiz;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.media.CamcorderProfile.get;
import static java.lang.System.load;

/**
 * Created by 李攀 on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView tvExamInfo,tvExamTitle,tv0p1,tv0p2,tv0p3,tv0p4,tvLoad;
    LinearLayout layoutLoading;
    ImageView mImageView;
    ProgressBar dialog;
    IExamBiz biz;
    boolean isLoadExamInfo=false;
    boolean isLoadQuestions=false;

    boolean isLoadExamInfoReceiver=false;
    boolean isLoadQuestionsReceiver=false;

    LoadExamBroadcast  mLoadExamBroadcast;
    LoadQuestionBroadcast mLoadQuestionBroadcast;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        mLoadExamBroadcast=new LoadExamBroadcast();
        mLoadQuestionBroadcast= new LoadQuestionBroadcast();
        setListener();
        biz=new ExamBiz();
        initView();
        loadData();
    }

    private void setListener() {
        registerReceiver(mLoadExamBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_INFO));
        registerReceiver(mLoadQuestionBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_QUESTION));
    }

    private void loadData() {
        layoutLoading.setEnabled(false);
        dialog.setVisibility(View.VISIBLE);
        tvLoad.setText("下载数据...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                biz.beginExam();
            }
        }).start();
    }

    private void initView() {
        layoutLoading= (LinearLayout) findViewById(R.id.layout_loading);
        dialog= (ProgressBar) findViewById(R.id.load_dialog);
        tvExamInfo = (TextView) findViewById(R.id.tv_examinfo);
        tvExamTitle = (TextView) findViewById(R.id.tv_exam_title);
        tv0p1 = (TextView) findViewById(R.id.tv_op1);
        tv0p2 = (TextView) findViewById(R.id.tv_op2);
        tv0p3 = (TextView) findViewById(R.id.tv_op3);
        tv0p4 = (TextView) findViewById(R.id.tv_op4);
        tvLoad = (TextView) findViewById(R.id.tv_load);
        mImageView = (ImageView) findViewById(R.id.im_exam_image);
        layoutLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void initData() {
        if (isLoadExamInfoReceiver && isLoadQuestionsReceiver){
            if (isLoadExamInfo && isLoadQuestions){
                layoutLoading.setVisibility(View.GONE);
                item item =ExamApplication.getInstance().getMitem();
                if (item !=null){
                    showData(item);
                }
                List<Question> questionList = ExamApplication.getInstance().getmQuestionList();
                if (questionList !=null ){
                    showExam(questionList);
                }
            }else{
                layoutLoading.setEnabled(true);
                dialog.setVisibility(View.GONE);
                tvLoad.setText("下载失败,点击重新下载");

            }
        }

    }

    private void showExam(List<Question> questionList) {
        Question question = questionList.get(0);
        if (question !=null){
            tvExamTitle.setText(question.getQuestion());
            tv0p1.setText(question.getItem1());
            tv0p2.setText(question.getItem2());
            tv0p3.setText(question.getItem3());
            tv0p4.setText(question.getItem4());
            Picasso.with(ExamActivity.this)
                    .load(question.getUrl())
                    .into(mImageView);


        }
    }

    private void showData(item item) {
        tvExamInfo.setText(item.toString());
    }
         @Override
                 protected void onDestroy(){
            super.onDestroy();
             if (mLoadExamBroadcast!=null){
                 unregisterReceiver(mLoadExamBroadcast);

        }
             if (mLoadQuestionBroadcast!=null){
                 unregisterReceiver(mLoadQuestionBroadcast);

             }
    }


    class LoadExamBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess=intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS,false);
            Log.e("LoadExamBroadcast","LoadExamBroadcast,isSuccess="+isSuccess);
            if (isSuccess){
                isLoadExamInfo=true;
            }
            isLoadExamInfoReceiver=true;
            initData();
        }
    }
    class LoadQuestionBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess=intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS,false);
            Log.e("LoadQuestionBroadcast","LoadQuestionBroadcast,isSuccess="+isSuccess);
            if (isSuccess){
                isLoadQuestions=true;
            }
            isLoadQuestionsReceiver=true;
            initData();
        }
    }
}
