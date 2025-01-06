package jmu.cdl.tutor.dao;

import jmu.cdl.tutor.pojo.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * StudentDao 接口用于定义对 Student 实体的数据库操作，包括根据客户ID查询学生ID、根据学生ID查询学生姓名、删除学生等操作。
 */
@Repository("studentDao")
public interface StudentDao extends CrudRepository<Student, Integer> {

    /**
     * 根据客户ID查询学生ID
     * 
     * @param customerId 客户ID
     * @return 学生ID列表
     */
    @Query("SELECT s.id FROM Student s WHERE s.customerId = :customerId")
    List<Integer> getStudentsIdByCustomerId(int customerId);

    /**
     * 根据学生ID查询学生姓名
     * 
     * @param studentId 学生ID
     * @return 学生姓名
     */
    @Query("SELECT s.name FROM Student s WHERE s.customerId = :studentId")
    String getStudentsNameByStudentId(int studentId);

    /**
     * 根据学生ID查询学生信息
     * 
     * @param studentId 学生ID
     * @return 学生信息
     */
    Optional<Student> findById(int studentId);

    /**
     * 根据客户ID删除学生
     * 
     * @param id 客户ID
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.customerId = :id")
    void deleteStudentById(int id);


}