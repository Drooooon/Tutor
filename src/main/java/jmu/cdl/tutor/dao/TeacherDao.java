package jmu.cdl.tutor.dao;

import jmu.cdl.tutor.pojo.DTO.ExamDetailDto;
import jmu.cdl.tutor.pojo.DTO.ExamDto;
import jmu.cdl.tutor.pojo.DTO.ExamWithDetailsDto;
import jmu.cdl.tutor.pojo.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TeacherDao 接口用于定义对 Teacher 实体的数据库操作，包括删除教师等操作。
 */
@Repository("TeacherDao")
public interface TeacherDao extends CrudRepository<Teacher, Integer> {

    /**
     * 根据教师ID删除教师
     * 
     * @param id 教师ID
     */
    @Modifying
    @Query("DELETE FROM Teacher t WHERE t.id = :id")
    void deleteTeacherById(int id);

    @Query("SELECT t.id FROM Teacher t WHERE t.status = :status")
    List<Integer> getIdsByStatus(String status);

    @Query("SELECT t FROM Teacher t WHERE t.id IN :ids")
    List<Teacher> getTeachersByIds(List<Integer> ids);


    @Modifying
    @Transactional
    @Query("UPDATE Teacher t SET t.status = :status WHERE t.id = :id")
    void updateStatusById(int id, String status);

    @Query("SELECT t.status FROM Teacher t WHERE t.id = :id")
    String getStatusById(int id);


    // 自定义查询，获取科目对象列表
    @Query("SELECT new Subject(ti.subjectId, s.subjectName,s.price) FROM TeachInfo ti " +
            "JOIN Subject s ON ti.subjectId = s.subjectId " +
            "WHERE ti.teacherId = :teacherId")
    List<Object> getAllSubject(int teacherId);


    @Query("SELECT new jmu.cdl.tutor.pojo.DTO.ExamWithDetailsDto(e.examId, e.examName, e.examDate,e.grade) " +
            "FROM Exams e " +
            "WHERE e.teacherId = :teacherId")
    List<ExamWithDetailsDto> findExamsByTeacherId(int teacherId);

    @Query("SELECT " +
            "    new jmu.cdl.tutor.pojo.DTO.ExamDetailDto(" +
            "        s.id, " + 
            "        s.name, " +
            "        ed.score) " +
            "FROM Exams e " +
            "LEFT JOIN ExamDetail ed ON e.examId = ed.examId " +
            "LEFT JOIN Student s ON s.id = ed.stuId " +
            "WHERE e.examId = :examId")
    List<ExamDetailDto> getExamDetails(int examId);

    @Modifying
    @Transactional
    @Query("UPDATE ExamDetail SET score = :score WHERE stuId = :stuId")
    void scoreStudent(int stuId, float score);

    @Query("SELECT new jmu.cdl.tutor.pojo.DTO.TeachStudent(s.name, s.id, s.grade) " +
            "FROM StuSubject ss " +
            "JOIN Student s ON ss.studentId = s.id " +
            "WHERE ss.teacherId = :teacherId")
    List<Object> getAllStudents(int teacherId);

    @Query("SELECT s.subjectName " +
            "FROM StuSubject ss " +
            "JOIN Subject s ON ss.subjectId = s.subjectId " +
            "WHERE ss.studentId = :stuId")
    List<String> getSubjectByStuId(int stuId);
}