package jmu.cdl.tutor.dao;

import jmu.cdl.tutor.pojo.TeachInfo;
import jmu.cdl.tutor.pojo.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeachInfoDao extends CrudRepository<TeachInfo, Integer> {
    /**
     * 根据科目和年级获取教师
     *
     * @param subjectId 科目
     * @param grade 年级
     * @return 教师列表
     */
    @Query("SELECT t.id FROM TeachInfo t WHERE t.subjectId = :subject AND t.grade = :grade")
    List<Teacher> getTeachersNameBySubjectAndGrade(int subjectId, String grade);
}
