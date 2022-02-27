package com.test.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.domain.TestDTO;

@Mapper
public interface TestMapper {

    public int insertTestList(TestDTO params);

    public int insertQuestion(TestDTO params);

    public TestDTO selectQuestion(String qid);

    public List<TestDTO> selectTestList();

    public List<TestDTO> selectAllQuestion(String tid);

    public int updateQuestion(TestDTO params);

    public int deleteQuestion(String qid);

    public List<TestDTO> viewTest(String tid);

    public List<TestDTO> viewAnswer(HashMap<String, String> ids);

    public int insertAnswer(TestDTO params);

    public int updateCON(TestDTO params);

    public int countQuestion();
}
