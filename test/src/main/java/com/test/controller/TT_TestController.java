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

import com.test.domain.MarkingTestDTO;
import com.test.domain.StudentTestDTO;
import com.test.domain.TestListDTO;
import com.test.domain.TestQuestionDTO;
import com.test.service.TT_TestService;

@Controller
public class TT_TestController {

    @Autowired
    private TT_TestService testservice;

    /* 문제 생성 및 수정 페이지 이동 */
    @GetMapping(value = "/insertQuestion.html")
    public String openInsertQuestionPage(@RequestParam(value = "qid", required = false) String qid, Model model) {

        // qid 넘어온게 없는 경우 신규 문제 생성
        if (qid == null) {

            // count = 데이터베이스에 생성되어있는 문제 수
            int count = testservice.countQuestion();

            TestQuestionDTO question = new TestQuestionDTO();

            model.addAttribute("count", count);
            model.addAttribute("question", question);
        } else {

            // qid가 넘어온 경우 수정 처리
            TestQuestionDTO question = testservice.detailQuestion(qid);
            model.addAttribute("question", question);
        }

        return "/insertQuestion";
    }

    /* 문제 생성 및 수정 */
    @PostMapping(value = "/questionRegister")
    @ResponseBody
    public int questionRegister(@RequestBody List<TestQuestionDTO> test) {
        int result = 0;
        for (TestQuestionDTO dto : test) {
            result = testservice.registerQuestion(dto);
        }
        return result;
    }

    /* 문제집 생성 페이지 이동 */
    @GetMapping(value = "/insertTestList.html")
    public String toInsertTestListPage(Model model) {
        TestListDTO testlist = new TestListDTO();

        model.addAttribute("testlist", testlist);

        return "/insertTestList";
    }

    /* 문제집 생성 */
    @PostMapping("/testListRegister")
    public int testListRegister(final TestListDTO params) {

        int result = testservice.registerTestList(params);

        if (result == 0) {
            System.out.println("문제집 등록 실패");
        }
        System.out.println("문제집 등록 성공");

        return 0;
    }

    /* 문제집 조회 */
    @PostMapping(value = "/detailTestList.html")
    public String selectAllQuestion(Model model, String TestlistId) {

        List<TestListDTO> list = testservice.detailTestList(TestlistId);

        model.addAttribute("detailTestList", list);

        return "/detailTestList";
    }

    /* 문제상세 조회 */
    @GetMapping(value = "/detailQuestion.html")
    public String detailQuestion(@RequestParam(value = "qid", required = false) String qid, Model model) {
        TestQuestionDTO question = testservice.detailQuestion(qid);

        model.addAttribute("question", question);

        return "/detailQuestion";
    }

    /* 문제 삭제 */
    @PostMapping(value = "/deleteQuestion.html")
    public String deleteQuestion(@RequestParam(value = "qid", required = false) String qid) {
        int deleteResult = testservice.deleteQuestion(qid);

        if (deleteResult == 0) {
            System.out.println("문제 삭제 실패");
        }
        System.out.println("문제 삭제 성공");

        return ""; // 이동 경로 설정
    }

    /* 문제집 문제풀러가기 */
    @PostMapping(value = "/solveTest.html")
    public String viewTest(Model model, String TestlistId) {
        StudentTestDTO student = new StudentTestDTO();
        List<TestQuestionDTO> list = testservice.solveTest(TestlistId);
        model.addAttribute("questionList", list);
        model.addAttribute("student", student);

        return "/solveTest";
    }

    /* 학생 답안 등록 */
    @PostMapping(value = "/studentAnswer")
    @ResponseBody
    public int answerRegister(@RequestBody List<StudentTestDTO> test) {
        int result = 0;
        for (StudentTestDTO dto : test) {
            result = testservice.insertStudentAnswer(dto);
        }
        return result;
    }

    /* 채점창 이동 */
    @PostMapping(value = "/markTest.html")
    public String toMarkAnswer(Model model, String TestlistId, String StudentId, HashMap<String, String> ids) {
        ids.put("tid", TestlistId);
        ids.put("sid", StudentId);
        List<MarkingTestDTO> list = testservice.viewStudentAnswer(ids);
        model.addAttribute("answerList", list);

        return "/markTest";
    }

    /* 채점 전송 */
    @PostMapping(value = "/checkCON")
    @ResponseBody
    public int correctOrNot(@RequestBody List<MarkingTestDTO> list) {
        int result = 0;
        for (MarkingTestDTO dto : list) {
            result = testservice.updateCON(dto);
        }
        return result;
    }
}
