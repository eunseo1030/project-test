package com.example.demo.controller;

import com.example.demo.service.StudySubjectService;
import com.example.demo.service.StudyTimeLogService;
import com.example.demo.vo.StudySubject;
import com.example.demo.vo.StudyTimeLog;
import com.example.demo.vo.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudyController {

    @Autowired
    private StudySubjectService studySubjectService;

    @Autowired
    private StudyTimeLogService studyTimeLogService;

    // 메인 화면: 과목 목록과 타이머
    @RequestMapping("/usr/study/main")
    public String showMain(HttpServletRequest req, Model model) {
        int memberId = 1;  // 가정된 회원 ID
        List<StudySubject> subjects = studySubjectService.getSubjectsByMemberId(memberId);
        model.addAttribute("subjects", subjects);

        // 타이머 시작 시 필요한 logId를 위해
        StudyTimeLog log = new StudyTimeLog();
        model.addAttribute("log", log);

        return "usr/study/main";
    }

    // 과목 추가
    @PostMapping("/usr/study/createSubject")
    public String createSubject(@RequestParam String subjectName) {
        int memberId = 1;  // 가정된 회원 ID
        studySubjectService.createSubject(memberId, subjectName);
        return "redirect:/usr/study/main";
    }

    // 공부 시작
    @GetMapping("/usr/study/start")
    public String startStudy(@RequestParam int subjectId, Model model) {
        StudyTimeLog log = studyTimeLogService.startStudy(subjectId);
        model.addAttribute("log", log);
        return "redirect:/usr/study/main?logId=" + log.getId();
    }

    // 공부 종료
    @GetMapping("/usr/study/end")
    @ResponseBody
    public String endStudy(@RequestParam int logId) {
        StudyTimeLog log = studyTimeLogService.endStudy(logId);
        return "<script>alert('공부 종료. 총 공부 시간: " + log.getDuration() + "초'); location.href='/usr/study/main';</script>";
    }
}