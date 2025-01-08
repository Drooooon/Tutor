package jmu.cdl.tutor.dao;

import jmu.cdl.tutor.pojo.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TeacherDao 接口用于定义对 Teacher 实体的数据库操作，包括删除教师等操作。
 */
@Repository("teacherDao")
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
    Page<Teacher> getTeachersByIds(List<Integer> ids, Pageable pageable);


    @Modifying
    @Transactional
    @Query("UPDATE Teacher t SET t.status = :status WHERE t.id = :id")
    void updateStatusById(int id, String status);

    @Query("SELECT t.status FROM Teacher t WHERE t.id = :id")
    String getStatusById(int id);
}