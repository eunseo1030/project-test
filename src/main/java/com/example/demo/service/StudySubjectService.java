package com.example.demo.service;

import com.example.demo.repository.StudySubjectRepository;
import com.example.demo.vo.StudySubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudySubjectService {

    @Autowired
    private StudySubjectRepository studySubjectRepository;

    public List<StudySubject> getSubjectsByMemberId(int memberId) {
        return studySubjectRepository.getSubjectsByMemberId(memberId);
    }

    public void createSubject(int memberId, String subjectName) {
        studySubjectRepository.createSubject(memberId, subjectName);
    }
}

	

