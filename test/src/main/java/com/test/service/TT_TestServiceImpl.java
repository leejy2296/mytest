package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.TestQuestionDTO;
import com.test.mapper.TT_TestMapper;

@Service
public class TT_TestServiceImpl implements TT_TestService {

    @Autowired
    private TT_TestMapper testmapper;

    // 문제 등록
    @Override
    public int registerQuestion(TestQuestionDTO params) {
        int result = 0;

        /* params.getTestQuestionId가 null이면 등록기능 */
        if (params.getTestQuestionId() == null) {
            result = testmapper.registerQuestion(params);
        } else {
            // null이 아니면 수정기능
            // result = testmapper.updateQuestion(params);
        }

        return result;
    }

    // 문제 개수 체크
    @Override
    public int countQuestion() {
        int result = 0;

        result = testmapper.countQuestion();

        return result;
    }

}
