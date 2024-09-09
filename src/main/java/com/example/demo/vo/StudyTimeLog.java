package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyTimeLog {
    private int id;
    private int subjectId;
    private Date startTime;
    private Date endTime;
    private int duration;  // 초 단위의 공부 시간
}