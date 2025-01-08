package jmu.cdl.tutor.service;

import jmu.cdl.tutor.pojo.DTO.ExamDetailDto;
import jmu.cdl.tutor.pojo.DTO.ExamDto;
import jmu.cdl.tutor.pojo.DTO.ExamWithDetailsDto;
import jmu.cdl.tutor.pojo.DTO.IdDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    public List<Object> getAllSubject(IdDto idDto);

    public void createExam(ExamDto examDto);

    List<ExamWithDetailsDto> findExamsByTeacherId(int teacherId);

    List<ExamDetailDto> getExamDetails(int examId);

    void scoreStudent(int stuId, float score);

    List<Object> getAllStudents(int teacherId);

    List<String> getSubjectByStuId(int stuId);
}
