package jmu.cdl.tutor.controller;

import jakarta.validation.Valid;
import jmu.cdl.tutor.dao.StuSubjectDao;
import jmu.cdl.tutor.dao.StudentDao;
import jmu.cdl.tutor.pojo.DTO.*;
import jmu.cdl.tutor.pojo.ResponseMessage;
import jmu.cdl.tutor.pojo.Student;
import jmu.cdl.tutor.pojo.Teacher;
import jmu.cdl.tutor.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentDao studentDao;



    @PostMapping("getTeachersByStatus")
    public ResponseMessage<List<Teacher>> getTeachers(@Valid @RequestBody StatusDto statusDto) {
        List<Integer> ids = adminService.getTeacherIdsByStatus(statusDto.getStatus());
        List<Teacher> teachers = adminService.getTeachersByIds(ids);
        return ResponseMessage.success(teachers);
    }


    @PutMapping("processTeacher")
    public ResponseMessage<String> processTeacher(@Valid @RequestBody IdDto idDto, @Valid @RequestBody StatusDto statusDto) {
        adminService.processTeacher(idDto.getId(), statusDto.getStatus());
        return ResponseMessage.success("处理完成");
    }

    @PostMapping("getStudents")
    public ResponseMessage<List<StuSubjectDto>> getStudents(@Valid @RequestBody StatusDto statusDto) {
        List<StuSubjectDto> results = adminService.getStudents(statusDto);
        if(results.isEmpty()){
            return ResponseMessage.failed(results);
        }
        return ResponseMessage.success(results);
    }

    @PutMapping("assignTeacher")
    public ResponseMessage<String> assignTeacherForStudent(StudentDto studentDto) {
        return ResponseMessage.success("分配完成");

    }




}
