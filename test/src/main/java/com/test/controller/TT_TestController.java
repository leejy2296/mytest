package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.domain.TestQuestionDTO;
import com.test.service.TT_TestService;

@Controller
public class TT_TestController {

    @Autowired
    private TT_TestService testservice;

    /* 문제 생성 및 수정 페이지 이동 */
    @GetMapping(value = "/insertQuestion.html")
    public String openInsertQuestionPage(@RequestParam(value = "qid", required = false) String qid, Model model) {

        /* qid 넘어온게 없는 경우 신규 문제 생성 */
        if (qid == null) {

            /* count = 데이터베이스에 생성되어있는 문제 수 */
            int count = testservice.countQuestion();

            TestQuestionDTO question = new TestQuestionDTO();

            model.addAttribute("count", count);
            model.addAttribute("question", question);
        } else {

            // qid가 넘어온 경우 수정 처리
            // TestQuestionDTO test = testservice.detailQuestion(qid);
            // model.addAttribute("question", question);
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

}
