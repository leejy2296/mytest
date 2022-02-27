package com.test.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.test.domain.TestQuestionDTO;

@Mapper
public interface TT_TestMapper {

    public int countQuestion();

    public int registerQuestion(TestQuestionDTO params);

}
