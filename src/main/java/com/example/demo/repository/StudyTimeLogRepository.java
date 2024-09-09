package com.example.demo.repository;

import com.example.demo.vo.StudyTimeLog;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Date;

@Mapper
public interface StudyTimeLogRepository {

    // 공부 기록 시작
    @Insert("INSERT INTO study_time_log (subjectId, startTime) VALUES (#{subjectId}, #{startTime})")
    void startStudy(@Param("subjectId") int subjectId, @Param("startTime") Date startTime);

    // 공부 기록 종료
    @Update("UPDATE study_time_log SET endTime = #{endTime}, duration = #{duration} WHERE id = #{logId}")
    void endStudy(@Param("logId") int logId, @Param("endTime") Date endTime, @Param("duration") int duration);

    // 공부 기록 가져오기
    @Select("SELECT * FROM study_time_log WHERE subjectId = #{subjectId}")
    List<StudyTimeLog> getLogsBySubjectId(@Param("subjectId") int subjectId);

    // 특정 로그 가져오기
    @Select("SELECT * FROM study_time_log WHERE id = #{id}")
    StudyTimeLog getLogById(@Param("id") int id);
}
