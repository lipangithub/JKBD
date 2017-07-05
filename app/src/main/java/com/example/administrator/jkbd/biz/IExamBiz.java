package com.example.administrator.jkbd.biz;

import com.example.administrator.jkbd.bean.Question;

/**
 * Created by 李攀 on 2017/7/2.
 */

public interface IExamBiz {
    void beginExam();
    Question getExam();
    Question getExam(int index);
    Question nextQuestion();
    Question preQuestion();
    int commitExam();
    String getExamIndex();
}
