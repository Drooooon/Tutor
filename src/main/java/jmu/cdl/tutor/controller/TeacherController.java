package jmu.cdl.tutor.controller;

import jakarta.validation.Valid;
import jmu.cdl.tutor.pojo.DTO.IdDto;
import jmu.cdl.tutor.pojo.ResponseMessage;
import jmu.cdl.tutor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/getAllSubject")
    public ResponseMessage<List<Object>> getAllSubject(@Valid @RequestBody IdDto idDto) {
        return ResponseMessage.success(teacherService.getAllSubject(idDto));
    }


}
