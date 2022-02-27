package com.test.domain;

import lombok.Data;

@Data
public class TestDTO {

//    문제집ID
    private String testlistId;
//    반ID
    private String classId;
//    과목ID
    private String subjectId;
//    학원ID
    private String academyId;
//    문제ID
    private String questionId;
//    문제내용
    private String questionContent;
//    문제의 점수
    private int score;
//    문제의 답
    private String answer;
//    학생의 답
    private String studentAnswer;
//    학생 아이디
    private String studentId;
//    등록된 문제 수
    private int count;

}
