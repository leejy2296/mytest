package com.test.service;

import com.test.domain.TestQuestionDTO;

public interface TT_TestService {

    public int countQuestion();

    public int registerQuestion(TestQuestionDTO params);
}
