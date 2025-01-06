package jmu.cdl.tutor.service;

import jakarta.validation.Valid;
import jmu.cdl.tutor.pojo.DTO.IdDto;
import jmu.cdl.tutor.pojo.DTO.StudentDto;
import jmu.cdl.tutor.pojo.DTO.SubjectsDto;
import jmu.cdl.tutor.pojo.Student;

import java.util.List;

/**
 * CustomerService 接口定义了与客户相关的业务逻辑，包括添加学生、管理学生科目、查询学生信息和获取科目名称等操作。
 */
public interface CustomerService {

    /**
     * 添加学生
     * 
     * @param studentDto 包含学生信息的 DTO 对象
     * @return 添加成功的学生信息
     */
    Student addStudent(StudentDto studentDto);

    /**
     * 为学生添加科目
     * 
     * @param subjectsDto 包含学生ID和科目列表的 DTO 对象
     * @return 更新后的学生科目列表
     */
    List<String> addSubject(SubjectsDto subjectsDto);

    /**
     * 为学生删除科目
     * 
     * @param subjectsDto 包含学生ID和科目列表的 DTO 对象
     * @return 更新后的学生科目列表
     */
    List<String> deleteSubjects(SubjectsDto subjectsDto);

    /**
     * 根据客户ID获取学生列表
     * 
     * @param idDto 包含客户ID的 DTO 对象
     * @return 学生列表
     */
    List<Student> getStudentsByCustomerId(IdDto idDto);

    /**
     * 获取学生未选科目
     * 
     * @param idDto 包含学生ID的 DTO 对象
     * @return 学生未选科目列表
     */
    List<String> getRestSubjects(@Valid IdDto idDto);

    /**
     * 根据学生ID获取学生已选科目
     * 
     * @param idDto 包含学生ID的 DTO 对象
     * @return 学生已选科目列表
     */
    List<String> getSubjectsByStudentId(@Valid IdDto idDto);

    /**
     * 获取所有科目名称
     * 
     * @return 所有科目名称的列表
     */
    List<String> getAllSubjectNames();
}