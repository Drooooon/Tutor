package jmu.cdl.tutor.dao;

import jakarta.validation.constraints.NotNull;
import jmu.cdl.tutor.pojo.Subject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SubjectDao 接口用于定义对 Subject
 * 实体的数据库操作，包括根据科目名称查询科目ID、获取所有科目名称和根据科目ID列表查询科目名称等操作。
 */
@Repository("subjectDao")
public interface SubjectDao extends CrudRepository<Subject, Integer> {

    /**
     * 根据科目名称查询科目ID
     * 
     * @param name 科目名称
     * @return 科目ID
     */
    @Query("SELECT s.subjectId FROM Subject s WHERE s.subjectName = :name")
    Integer getIdBySubjectName(String name);

    /**
     * 获取所有科目名称
     * 
     * @return 科目名称列表
     */
    @Query("SELECT s.subjectName FROM Subject s")
    List<String> getAllSubjectNames();

    /**
     * 根据科目ID列表查询科目名称
     * 
     * @param ids 科目ID列表
     * @return 科目名称列表
     */
    @Query("SELECT s.subjectName FROM Subject s WHERE s.subjectId IN :ids")
    List<String> getSubjectNamesByIds(List<Integer> ids);

    @Query("SELECT s FROM Subject s")
    List<Subject> findAllSubjects();

    @Transactional
    @Modifying
    @Query("UPDATE Subject s SET s.price = :price WHERE s.subjectId = :id")
    void updatePriceById(int id, double price);
}