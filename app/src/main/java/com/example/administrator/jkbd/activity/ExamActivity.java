package com.example.administrator.jkbd.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jkbd.ExamApplication;
import com.example.administrator.jkbd.R;
import com.example.administrator.jkbd.bean.Question;
import com.example.administrator.jkbd.bean.item;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.media.CamcorderProfile.get;
import static java.lang.System.load;

/**
 * Created by 李攀 on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView tvExamInfo,tvExamTitle,tv0p1,tv0p2,tv0p3,tv0p4;
    ImageView mImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initView();
        initData();
    }

    private void initView() {
        tvExamInfo = (TextView) findViewById(R.id.tv_examinfo);
        tvExamTitle = (TextView) findViewById(R.id.tv_exam_title);
        tv0p1 = (TextView) findViewById(R.id.tv_op1);
        tv0p2 = (TextView) findViewById(R.id.tv_op2);
        tv0p3 = (TextView) findViewById(R.id.tv_op3);
        tv0p4 = (TextView) findViewById(R.id.tv_op4);
        //mImageView = (ImageView) findViewById(R.id.im_exam_image);
    }

    private void initData() {
       item item =ExamApplication.getInstance().getMitem();
        if (item !=null){
            showData(item);
        }
      List<Question> questionList = ExamApplication.getInstance().getmQuestionList();
        if (questionList !=null ){
            showExam(questionList);
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
}
