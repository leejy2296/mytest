package com.test.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.domain.TestDTO;
import com.test.service.TestService;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    /* 메인 페이지 이동 */
    @GetMapping(value = "/test/main.html")
    public String toMainPage(Model model) {
        TestDTO test = new TestDTO();

        model.addAttribute("test", test);

        return "test/main";
    }

    /* 문제집 생성 페이지 이동 */
    @GetMapping(value = "/test/insertTestList.html")
    public String toInsertTestListPage(Model model) {
        TestDTO test = new TestDTO();

        model.addAttribute("test", test);

        return "test/insertTestList";
    }

    /* 문제 생성 및 수정 페이지 이동 */
    @GetMapping(value = "/test/insertQuestion.html")
    public String toInsertQuestionPage(@RequestParam(value = "qid", required = false) String qid, Model model) {
        if (qid == null) {
            /* qid 넘어온게 없는 경우 신규 문제 생성 */
            TestDTO test = new TestDTO();
            List<TestDTO> list = testService.getTestList();

            model.addAttribute("list", list);
            model.addAttribute("test", test);
        } else {
            /* qid가 넘어온 경우 수정 처리 */
            TestDTO test = testService.detailQuestion(qid);
            model.addAttribute("test", test);
        }

        return "test/insertQuestion";
    }

    /* 문제집 생성 */
    @PostMapping(value = "/test/testListRegister")
    public String testListRegister(final TestDTO params) {
        System.out.println("문제집 등록 진입");
        try {
            boolean result = testService.insertTestList(params);
            System.out.println("문제집 등록성공");
            if (result == false) {
                System.out.println("문제집 등록실패");
            }
        } catch (Exception e) {

        }
        return "redirect:/test/main.html";
    }

    /* 문제집으로 검색 결과 조회 */
    @PostMapping(value = "/test/resultViewQuestion.html")
    public String selectAllQuestion(Model model, String TestlistId) {
        List<TestDTO> result = testService.getQuestionList(TestlistId);
        model.addAttribute("questionList", result);

        return "test/resultViewQuestion";
    }

    /* 문제상세 조회 */
    @GetMapping(value = "/test/detailQuestion.html")
    public String detailQuestion(@RequestParam(value = "qid", required = false) String qid, Model model) {
        TestDTO test = testService.detailQuestion(qid);

        model.addAttribute("test", test);

        return "test/detailQuestion";
    }

    /* 문제 삭제 */
    @PostMapping(value = "/test/deleteQuestion.html")
    public String deleteQuestion(@RequestParam(value = "qid", required = false) String qid) {
        try {
            boolean deleteResult = testService.deleteQuestion(qid);
            if (deleteResult == false) {

            }
        } catch (Exception e) {

        }

        return "redirect:/test/main.html";
    }

    /* 문제집으로 문제 결과 조회 */
    @PostMapping(value = "/test/viewTest.html")
    public String viewTest(Model model, String TestlistId) {
        TestDTO test = new TestDTO();
        List<TestDTO> result = testService.viewTest(TestlistId);
        model.addAttribute("questionList", result);
        model.addAttribute("test", test);

        return "test/viewTest";
    }

    /* 채점창 결과 조회 */
    @PostMapping(value = "/test/markAnswer.html")
    public String toMarkAnswer(Model model, String TestlistId, String StudentId, HashMap<String, String> ids) {
        ids.put("tid", TestlistId);
        ids.put("sid", StudentId);
        List<TestDTO> result = testService.viewTest(TestlistId);
        List<TestDTO> result2 = testService.viewAnswer(ids);
        model.addAttribute("questionList", result);
        model.addAttribute("answerList", result2);

        return "test/markAnswer";
    }

    /*
     * 답안 등록
     */
    @PostMapping(value = "/test/answerRegister")
    @ResponseBody
    public int answerRegister(@RequestBody List<TestDTO> test) {
        int result = 0;
        for (TestDTO dto : test) {
            result = testService.insertAnswer(dto);
        }
        return result;
    }

    /* 문제 생성 및 수정 */
    @PostMapping(value = "/test/questionRegister")
    @ResponseBody
    public int questionRegister(@RequestBody List<TestDTO> test) {
        int result = 0;
        for (TestDTO dto : test) {
            result = testService.insertQuestion(dto);
        }
        return result;
    }

    /* 채점 데이터 전송 */
    @PostMapping(value = "/test/checkCON")
    @ResponseBody
    public int correctOrNot(@RequestBody List<TestDTO> test) {
        int result = 0;
        for (TestDTO dto : test) {
            result = testService.updateCON(dto);
        }
        return result;
    }

}
