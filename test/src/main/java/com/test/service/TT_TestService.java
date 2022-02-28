package com.test.service;

import java.util.HashMap;
import java.util.List;

import com.test.domain.MarkingTestDTO;
import com.test.domain.StudentTestDTO;
import com.test.domain.TestListDTO;
import com.test.domain.TestQuestionDTO;

public interface TT_TestService {

    public int countQuestion();

    public List<TestListDTO> detailTestList(String tid);

    public int registerQuestion(TestQuestionDTO params);

    public int registerTestList(TestListDTO params);

    public TestQuestionDTO detailQuestion(String qid);

    public int deleteQuestion(String qid);

    public List<TestQuestionDTO> solveTest(String tid);

    public int insertStudentAnswer(StudentTestDTO params);

    public List<MarkingTestDTO> viewStudentAnswer(HashMap<String, String> ids);

    public int updateCON(MarkingTestDTO params);
}
