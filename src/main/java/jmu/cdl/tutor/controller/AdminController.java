package jmu.cdl.tutor.controller;

import jakarta.validation.Valid;
import jmu.cdl.tutor.dao.StuSubjectDao;
import jmu.cdl.tutor.dao.StudentDao;
import jmu.cdl.tutor.pojo.DTO.*;
import jmu.cdl.tutor.pojo.ResponseMessage;
import jmu.cdl.tutor.pojo.Student;
import jmu.cdl.tutor.pojo.Subject;
import jmu.cdl.tutor.pojo.Teacher;
import jmu.cdl.tutor.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * AdminController 处理与管理员相关的请求，包括获取教师信息、处理教师申请、获取学生信息、分配教师、获取科目信息和更新科目价格等。
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentDao studentDao;

    /**
     * 根据教师状态获取教师列表
     *
     * @param statusDto 包含教师状态的 DTO 对象
     * @return 教师列表
     */
    @PostMapping("getTeachersByStatus")
    public ResponseMessage<List<Teacher>> getTeachers(@Valid @RequestBody StatusDto statusDto) {
        List<Integer> ids = adminService.getTeacherIdsByStatus(statusDto.getStatus());
        List<Teacher> teachers = adminService.getTeachersByIds(ids);
        return ResponseMessage.success(teachers);
    }

    /**
     * 处理教师申请
     *
     * @param idDto 包含教师ID和状态的 DTO 对象
     * @return 处理结果信息
     */
    @PutMapping("processTeacher")
    public ResponseMessage<String> processTeacher(@Valid @RequestBody IdAndStatusDto idDto) {
        adminService.processTeacher(idDto);
        return ResponseMessage.success("处理完成");
    }

    /**
     * 获取学生列表
     *
     * @param statusDto 包含学生状态的 DTO 对象
     * @return 学生列表
     */
    @PostMapping("getStudents")
    public ResponseMessage<List<StuSubjectDto>> getStudents(@Valid @RequestBody StatusDto statusDto) {
        List<StuSubjectDto> results = adminService.getStudents(statusDto);
        if (results.isEmpty()) {
            return ResponseMessage.failed(results);
        }
        return ResponseMessage.success(results);
    }

    /**
     * 为学生分配教师
     *
     * @param assignTeacherDto 包含学生ID和教师ID的 DTO 对象
     * @return 分配结果信息
     */
    @PutMapping("assignTeacher")
    public ResponseMessage<String> assignTeacherForStudent(@Valid @RequestBody AssignTeacherDto assignTeacherDto) {
        String message = adminService.assignTeacher(assignTeacherDto);
        return ResponseMessage.success(message);
    }

    /**
     * 获取所有科目信息
     *
     * @return 科目信息列表
     */
    @GetMapping("getSubjectInfo")
    public ResponseMessage<List<Subject>> getSubjectInfo() {
        List<Subject> subjects = adminService.getSubjectInfo();
        return ResponseMessage.success(subjects);
    }

    /**
     * 更新科目价格
     *
     * @param priceDto 包含科目名称和新价格的 DTO 对象
     * @return 更新结果信息
     */
    @PutMapping("updateSubjectPrice")
    public ResponseMessage<String> updateSubjectPrice(@Valid @RequestBody PriceDto priceDto) {
        String message = adminService.updatePrice(priceDto);
        return ResponseMessage.success(message);
    }
}