package jmu.cdl.tutor.dao;

import jmu.cdl.tutor.pojo.Exams;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ExamsDao 接口用于定义对 Exams 实体的数据库操作，包括根据科目ID查询考试ID、根据考试ID查询考试名称和平均分等操作。
 */
@Repository("examsDao")
public interface ExamsDao extends CrudRepository<Exams, Integer> {

    /**
     * 根据科目ID查询考试ID
     * 
     * @param subjectId 科目ID
     * @return 考试ID列表
     */
    @Query("SELECT e.examId FROM Exams e WHERE e.subjectId = :subjectId")
    List<Integer> findExamIdBySubjectId(int subjectId);

    /**
     * 根据考试ID查询考试名称
     * 
     * @param examIds 考试ID列表
     * @return 考试名称列表
     */
    @Query("SELECT e.examName FROM Exams e WHERE e.examId in :examIds")
    List<String> getExamNamesBySubjectId(List<Integer> examIds);

    /**
     * 根据考试ID查询平均分
     * 
     * @param examId 考试ID列表
     * @return 平均分列表
     */
    @Query("SELECT e.averageScore FROM Exams e WHERE e.examId IN :examId")
    List<Float> findAverageScoreByExamId(List<Integer> examId);
}