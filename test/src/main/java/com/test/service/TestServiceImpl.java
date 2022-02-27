package com.test.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.TestDTO;
import com.test.mapper.TestMapper;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testmapper;

    @Override
    public boolean insertTestList(TestDTO params) {
        int queryResult = 0;

        queryResult = testmapper.insertTestList(params);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public int insertQuestion(TestDTO params) {
        int queryResult = 0;

        if (params.getQuestionId() == null) {
            queryResult = testmapper.insertQuestion(params);
        } else {
            queryResult = testmapper.updateQuestion(params);
        }
        return queryResult;
    }

    @Override
    public List<TestDTO> getQuestionList(String tid) {

        List<TestDTO> questionList = Collections.emptyList();

        questionList = testmapper.selectAllQuestion(tid);

        return questionList;
    }

    @Override
    public List<TestDTO> getTestList() {

        List<TestDTO> testlist = Collections.emptyList();

        testlist = testmapper.selectTestList();

        return testlist;
    }

    @Override
    public TestDTO detailQuestion(String qid) {
        return testmapper.selectQuestion(qid);
    }

    @Override
    public boolean deleteQuestion(String qid) {
        int queryResult = 0;

        TestDTO test = testmapper.selectQuestion(qid);

        if (test != null) {
            queryResult = testmapper.deleteQuestion(qid);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<TestDTO> viewTest(String tid) {
        List<TestDTO> list = Collections.emptyList();

        list = testmapper.viewTest(tid);

        return list;
    }

    @Override
    public int insertAnswer(TestDTO params) {
        int queryResult = 0;

        queryResult = testmapper.insertAnswer(params);

        return queryResult;
    }

    @Override
    public List<TestDTO> viewAnswer(HashMap<String, String> ids) {
        List<TestDTO> list = Collections.emptyList();

        list = testmapper.viewAnswer(ids);

        return list;
    }

    @Override
    public int updateCON(TestDTO params) {
        int queryResult = 0;

        queryResult = testmapper.updateCON(params);

        return queryResult;
    }

    @Override
    public int countQuestion() {
        int result = 0;

        result = testmapper.countQuestion();

        return result;
    }

}
