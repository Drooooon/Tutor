package jmu.cdl.tutor.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jmu.cdl.tutor.pojo.DTO.*;
import jmu.cdl.tutor.pojo.Subject;
import jmu.cdl.tutor.pojo.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * AdminService 接口定义了与管理员相关的业务逻辑，包括获取教师信息、处理教师申请、获取学生信息、分配教师、获取科目信息和更新科目价格等操作。
 */
public interface AdminService {

    /**
     * 根据教师状态获取教师ID列表
     * @param status 教师状态
     * @return 教师ID列表
     */
    List<Integer> getTeacherIdsByStatus(@NotBlank String status);

    /**
     * 根据教师ID列表获取教师信息
     * @param ids 教师ID列表
     * @return 教师信息列表
     */
    Page<Teacher> getTeachersByIds(List<Integer> ids, int page, int size);


    /**
     * 处理教师申请，更新教师状态
     * @param idAndStatusDto 包含教师ID和状态的 DTO 对象
     */
    void processTeacher(IdAndStatusDto idAndStatusDto);

    /**
     * 获取所有学生ID
     * @return 学生ID列表
     */
    List<Integer> getStudentIds();


    /**
     * 根据学生状态获取学生信息
     * @param pageDto 包含学生状态的 DTO 对象
     * @return 学生信息列表
     */
    Page<StudentAssignDto> getStudents(@Valid PageDto pageDto);

    /**
     * 获取所有科目信息
     * @return 科目信息列表
     */
    Page<Subject> getSubjectInfo(PageDto pageDto);

    /**
     * 更新科目价格
     * @param priceDto 包含科目名称和新价格的 DTO 对象
     * @return 更新结果信息
     */
    String updatePrice(@Valid PriceDto priceDto);

    /**
     * 为学生分配教师
     * @param assignTeacherDto 包含学生ID和教师ID的 DTO 对象
     * @return 分配结果信息
     */
    String assignTeacher(@Valid AssignTeacherDto assignTeacherDto);

    void fastAssign();

    void autoAssign(int tableId);

    Page<Integer> getStudentIdPages(Pageable pageable);

    List<TeacherInfoDto> getTeacherBySubjectAndGrade(@Valid SubjectAndGradeDto subjectAndGradeDto);
}