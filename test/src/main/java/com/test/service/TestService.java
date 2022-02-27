package com.test.service;

import java.util.HashMap;
import java.util.List;

import com.test.domain.TestDTO;

public interface TestService {

    public boolean insertTestList(TestDTO params);

    public int insertQuestion(TestDTO params);

    public List<TestDTO> getTestList();

    public List<TestDTO> getQuestionList(String tid);

    public TestDTO detailQuestion(String qid);

    public boolean deleteQuestion(String qid);

    public List<TestDTO> viewTest(String tid);

    public List<TestDTO> viewAnswer(HashMap<String, String> ids);

    public int insertAnswer(TestDTO params);

    public int updateCON(TestDTO params);

    public int countQuestion();
}
