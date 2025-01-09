package jmu.cdl.tutor.controller;

import jakarta.validation.Valid;
import jmu.cdl.tutor.pojo.DTO.ExamDetailDto;
import jmu.cdl.tutor.pojo.DTO.ExamDto;
import jmu.cdl.tutor.pojo.DTO.ExamWithDetailsDto;
import jmu.cdl.tutor.pojo.DTO.IdDto;
import jmu.cdl.tutor.pojo.ResponseMessage;
import jmu.cdl.tutor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/getAllSubject")
    public ResponseMessage<List<Object>> getAllSubject(@Valid @RequestBody IdDto idDto) {
        return ResponseMessage.success(teacherService.getAllSubject(idDto));
    }

    @RequestMapping("/createExam")
    public ResponseMessage<String> createExam(@Valid @RequestBody ExamDto examDto){
        System.out.println("考试时间："+examDto.getExamDate());
        teacherService.createExam(examDto);
        return ResponseMessage.success("考试已发布");
    }

    @RequestMapping("/getExams")
    public ResponseMessage<List<ExamWithDetailsDto>> getExamsByTeacherId(@RequestParam("userId") int teacherId) {
        System.out.println("教师编号"+teacherId);
            List<ExamWithDetailsDto> exams = teacherService.findExamsByTeacherId(teacherId);
        System.out.println("exams数据"+exams);
            return ResponseMessage.success(exams);

    }

    @RequestMapping("/getExamDetails")
    public ResponseMessage<List<ExamDetailDto>> getExamDetails(@RequestParam("examId") int examId){
        List<ExamDetailDto> deatils = teacherService.getExamDetails(examId);
        return ResponseMessage.success(deatils);
    }

    @RequestMapping("/scoreStudent")
    public ResponseMessage<String> scoreStudent(@RequestParam("stuId") int stuId, @RequestParam("score") float score){
        teacherService.scoreStudent(stuId,score);
        return ResponseMessage.success("打分成功");
    }

    @RequestMapping("/getAllStudents")
    public ResponseMessage<List<Object>> getAllStudents(@RequestParam("userId") int teacherId){
        List<Object>  student = teacherService.getAllStudents(teacherId);
        return ResponseMessage.success(student);
    }

    @RequestMapping("/getSubjectByStuId")
    public ResponseMessage<List<String>> getSubjectByStuId(@RequestParam("stuId") int stuId){
        List<String> subjects = teacherService.getSubjectByStuId(stuId);
        return ResponseMessage.success(subjects);
    }
}
