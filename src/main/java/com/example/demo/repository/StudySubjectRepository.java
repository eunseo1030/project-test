package com.example.demo.repository;

import com.example.demo.vo.StudySubject;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudySubjectRepository {

    // 회원 ID로 과목 리스트 가져오기
    @Select("SELECT * FROM study_subject WHERE memberId = #{memberId}")
    List<StudySubject> getSubjectsByMemberId(@Param("memberId") int memberId);

    // 새로운 과목 추가
    @Insert("INSERT INTO study_subject (memberId, subject_name, regDate) VALUES (#{memberId}, #{subjectName}, NOW())")
    void createSubject(@Param("memberId") int memberId, @Param("subjectName") String subjectName);
}
