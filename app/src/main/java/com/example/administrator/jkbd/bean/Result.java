package com.example.administrator.jkbd.bean;

import java.util.List;

/**
 * Created by 李攀 on 2017/6/28.
 */

public class Result {

    /**
     * error_code : 0
     * reason : ok
     * result : [{"id":4,"question":"这个标志是何含义？","answer":"2","item1":"禁止自行车通行车道","item2":"非机动车车道","item3":"自行车专用车道","item4":"停放自行车路段","explains":"此图为非机动车车道，别误以为自行车专用车道，没有自行车专用车道这一说。","url":"http://images.juheapi.com/jztk/c1c2subject1/4.jpg"}]
     */

    private int error_code;
    private String reason;
    private List<Question> questions;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }



}
