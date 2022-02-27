package com.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.domain.TestDTO;
import com.test.mapper.TestMapper;

@SpringBootTest
public class MapperTests {

    @Autowired
    private TestMapper testmapper;

    @Test
    public void testOfInsertTest() {
        TestDTO params = new TestDTO();
        params.setClassId("디컨");
        params.setAcademyId("human");
        params.setSubjectId("java");

        int result = testmapper.insertTestList(params);
        System.out.println("결과는 " + result + "입니다.");
    }

    @Test
    public void testOfInsertQuestion() {
        TestDTO params = new TestDTO();
        params.setQuestionContent("2+2은?");
        params.setAnswer("4");
        params.setScore(5);
        params.setTestlistId("t1");
        params.setClassId("디컨");
        params.setAcademyId("human");

        int result = testmapper.insertQuestion(params);
        System.out.println("결과는 " + result + "입니다.");
    }

    @Test
    public void testOfSelectQuestion() {
        String qid = "q1";
        TestDTO test = testmapper.selectQuestion(qid);
        try {
            String testJson = new ObjectMapper().writeValueAsString(test);

            System.out.println("=========================");
            System.out.println(testJson);
            System.out.println("=========================");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectQuestionList() {
        String tid = "t1";
        List<TestDTO> testList = testmapper.selectAllQuestion(tid);
        if (CollectionUtils.isEmpty(testList) == false) {
            for (TestDTO test : testList) {
                System.out.println("=========================");
                System.out.println(test.getQuestionId());
                System.out.println(test.getQuestionContent());
                System.out.println(test.getScore());
                System.out.println("=========================");
            }
        }
    }
}
