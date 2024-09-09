package com.example.demo.service;

import com.example.demo.repository.StudyTimeLogRepository;
import com.example.demo.vo.StudyTimeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudyTimeLogService {

    @Autowired
    private StudyTimeLogRepository studyTimeLogRepository;

    public StudyTimeLog startStudy(int subjectId) {
        StudyTimeLog log = new StudyTimeLog();
        log.setSubjectId(subjectId);
        log.setStartTime(new Date());
        studyTimeLogRepository.startStudy(subjectId, log.getStartTime());
        return log;
    }

    public StudyTimeLog endStudy(int logId) {
        StudyTimeLog log = studyTimeLogRepository.getLogById(logId);
        log.setEndTime(new Date());
        int duration = (int) ((log.getEndTime().getTime() - log.getStartTime().getTime()) / 1000); // 초 단위 계산
        studyTimeLogRepository.endStudy(logId, log.getEndTime(), duration);
        log.setDuration(duration);
        return log;
    }

    public List<StudyTimeLog> getLogsBySubjectId(int subjectId) {
        return studyTimeLogRepository.getLogsBySubjectId(subjectId);
    }

    public StudyTimeLog getLogById(int id) {
        return studyTimeLogRepository.getLogById(id);
    }
}
