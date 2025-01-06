package jmu.cdl.tutor.dao;

import jmu.cdl.tutor.pojo.StuSubject;
import jmu.cdl.tutor.pojo.Student;
import jmu.cdl.tutor.pojo.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * StuSubjectDao 接口用于定义对 StuSubject 实体的数据库操作，包括根据学生ID查询科目ID、删除学生科目等操作。
 */
@Repository("stuSubjectDao")
public interface StuSubjectDao extends CrudRepository<StuSubject, Integer> {

    /**
     * 根据学生ID和科目ID删除学生科目
     * 
     * @param studentId 学生ID
     * @param subjectId 科目ID
     */
    void deleteByStudentIdAndSubjectId(int studentId, int subjectId);

    /**
     * 根据学生ID查询科目ID
     * 
     * @param studentId 学生ID
     * @return 科目ID列表
     */
    @Query("SELECT s.subjectId FROM StuSubject s WHERE s.studentId = :studentId")
    List<Integer> getSubjectIdsByStudentId(int studentId);

    /**
     * 根据学生ID删除学生科目
     * 
     * @param id 学生ID列表
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM StuSubject s WHERE s.studentId in :id")
    void deleteStuSubjectById(List<Integer> id);

    /**
     * 获取所有学生
     *
     * @return 学生列表
     */
    @Query("SELECT s.studentId FROM StuSubject s WHERE s.teacherId is null")
    List<Integer> getStudentIds();


    @Query("SELECT s.subjectId FROM StuSubject s WHERE s.studentId = :studentId AND s.teacherId IS NULL")
    List<Integer> getNullTeacherSubjectIdsByStudentId(int id);
}