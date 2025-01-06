package jmu.cdl.tutor.dao;

import jmu.cdl.tutor.pojo.ExamDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ExamDetailDao 接口用于定义对 ExamDetail 实体的数据库操作，包括根据考试ID和学生ID查询成绩等操作。
 */
@Repository("examDetailDao")
public interface ExamDetailDao extends CrudRepository<ExamDetail, Integer> {

    /**
     * 根据考试ID和学生ID查询成绩
     * 
     * @param examId    考试ID列表
     * @param studentId 学生ID
     * @return 成绩列表
     */
    @Query("SELECT e.score FROM ExamDetail e WHERE e.examId IN :examId AND e.stuId = :studentId")
    List<Float> findScoresByExamIdAndStudentId(List<Integer> examId, int studentId);
}