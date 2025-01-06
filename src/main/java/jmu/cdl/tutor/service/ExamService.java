package jmu.cdl.tutor.service;

import jmu.cdl.tutor.pojo.DTO.ExamScoreDto;
import jmu.cdl.tutor.pojo.DTO.ScoreRequestDto;

import java.util.List;

/**
 * ExamService 接口定义了与考试相关的业务逻辑，包括查询学生的考试成绩等操作。
 */
public interface ExamService {

    /**
     * 获取学生考试成绩
     * 
     * @param scoreRequestDto 包含学生ID和科目名称的 DTO 对象
     * @return 学生考试成绩的列表
     */
    List<ExamScoreDto> getExamScores(ScoreRequestDto scoreRequestDto);
}