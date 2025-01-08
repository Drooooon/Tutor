package jmu.cdl.tutor.controller;

import jakarta.validation.Valid;
import jmu.cdl.tutor.pojo.DTO.*;
import jmu.cdl.tutor.pojo.ResponseMessage;
import jmu.cdl.tutor.pojo.Student;
import jmu.cdl.tutor.service.AccountService;
import jmu.cdl.tutor.service.CustomerService;
import jmu.cdl.tutor.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CustomerController 处理与客户相关的请求，包括添加学生、获取科目、管理学生科目和查询学生成绩等。
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ExamService examService;

    /**
     * 处理添加学生请求
     * 
     * @param studentDto 包含学生信息的 DTO 对象
     * @return 添加成功的学生信息
     */
    @PostMapping("addStudent")
    public ResponseMessage<Student> add(@Valid @RequestBody StudentDto studentDto) {
        Student student = customerService.addStudent(studentDto);
        return ResponseMessage.success(student);
    }

    /**
     * 获取所有科目名称
     * 
     * @return 所有科目名称的列表
     */
    @GetMapping("getAllSubject")
    public ResponseMessage<List<String>> getAllSubject() {
        List<String> subjects = customerService.getAllSubjectNames();
        return ResponseMessage.success(subjects);
    }

    /**
     * 根据学生ID获取学生已选科目
     * 
     * @param idDto 包含学生ID的 DTO 对象
     * @return 学生已选科目的列表
     */
    @PostMapping("getSubjectsByStudentId")
    public ResponseMessage<List<String>> getSubjectsByStudentId(@Valid @RequestBody IdDto idDto) {
        List<String> subjects = customerService.getSubjectsByStudentId(idDto);
        return ResponseMessage.success(subjects);
    }

    /**
     * 为学生添加科目
     * 
     * @param subjectsDto 包含学生ID和科目列表的 DTO 对象
     * @return 更新后的学生科目列表
     */
    @PutMapping("addSubject")
    public ResponseMessage<List<String>> addSubject(@Valid @RequestBody SubjectsDto subjectsDto) {
        List<String> result = customerService.addSubject(subjectsDto);
        return ResponseMessage.success(result);
    }

    /**
     * 根据客户ID获取学生列表
     * 
     * @param idDto 包含客户ID的 DTO 对象
     * @return 学生列表
     */
    @PostMapping("getStudentsByCustomerId")
    public ResponseMessage<List<Student>> getStudents(@Valid @RequestBody IdDto idDto) {
        List<Student> students = customerService.getStudentsByCustomerId(idDto);
        return ResponseMessage.success(students);
    }

    /**
     * 为学生删除科目
     * 
     * @param subjectsDto 包含学生ID和科目列表的 DTO 对象
     * @return 更新后的学生科目列表
     */
    @PutMapping("deleteSubjects")
    public ResponseMessage<List<String>> deleteSubjects(@Valid @RequestBody SubjectsDto subjectsDto) {
        List<String> result = customerService.deleteSubjects(subjectsDto);
        return ResponseMessage.success(result);
    }

    /**
     * 获取学生未选科目
     * 
     * @param idDto 包含学生ID的 DTO 对象
     * @return 学生未选科目的列表
     */
    @PostMapping("getRestSubjects")
    public ResponseMessage<List<String>> getRestSubjects(@Valid @RequestBody IdDto idDto) {
        List<String> result = customerService.getRestSubjects(idDto);
        return ResponseMessage.success(result);
    }

    /**
     * 获取学生考试成绩
     * 
     * @param scoreRequestDto 包含学生ID和科目名称的 DTO 对象
     * @return 学生考试成绩的列表
     */
    @PostMapping("getCharts")
    public ResponseMessage<List<ExamScoreDto>> getCharts(@Valid @RequestBody ScoreRequestDto scoreRequestDto) {
        List<ExamScoreDto> result = examService.getExamScores(scoreRequestDto);
        return ResponseMessage.success(result);
    }
}