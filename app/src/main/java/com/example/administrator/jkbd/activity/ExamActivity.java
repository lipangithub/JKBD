package com.example.administrator.jkbd.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.jkbd.ExamApplication;
import com.example.administrator.jkbd.R;
import com.example.administrator.jkbd.bean.item;

/**
 * Created by 李攀 on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView tvExamInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initView();
        initData();
    }

    private void initView() {
        tvExamInfo = (TextView) findViewById(R.id.tv_examinfo);
    }

    private void initData() {
       item examInfo =ExamApplication.getInstance().getMitem();
        if (examInfo!=null){
            showData(examInfo);
        }
    }

    private void showData(item examInfo) {
        tvExamInfo.setText(examInfo.toString());
    }
}
