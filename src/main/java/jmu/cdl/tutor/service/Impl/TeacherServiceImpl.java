package jmu.cdl.tutor.service.Impl;

import jmu.cdl.tutor.dao.ExamsDao;
import jmu.cdl.tutor.dao.SubjectDao;
import jmu.cdl.tutor.dao.TeacherDao;
import jmu.cdl.tutor.pojo.DTO.ExamDetailDto;
import jmu.cdl.tutor.pojo.DTO.ExamDto;
import jmu.cdl.tutor.pojo.DTO.ExamWithDetailsDto;
import jmu.cdl.tutor.pojo.DTO.IdDto;
import jmu.cdl.tutor.pojo.Exams;
import jmu.cdl.tutor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private  ExamsDao examsDao;
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public List<Object> getAllSubject(IdDto idDto) {
        List<Object> subjects = teacherDao.getAllSubject(idDto.getId());

        return subjects;
    }

    @Override
    public void createExam(ExamDto examDto) {
        Exams exams=new Exams();
        exams.setExamDate(examDto.getExamDate());
        exams.setTeacherId(examDto.getTeacherId());
        exams.setGrade(examDto.getGrade());
        exams.setSubjectId(exams.getSubjectId());
        String subjectName = subjectDao.getSubjectNameById(examDto.getSubjectId());
        exams.setExamName(subjectName);
        examsDao.save(exams);
    }

    @Override
    public List<ExamWithDetailsDto> findExamsByTeacherId(int teacherId) {
        List<ExamWithDetailsDto> exams = teacherDao.findExamsByTeacherId(teacherId);
        return exams;
    }

    @Override
    public List<ExamDetailDto> getExamDetails(int examId) {
        List<ExamDetailDto> details = teacherDao.getExamDetails(examId);
        return details;
    }

    @Override
    public void scoreStudent(int stuId, float score) {
        teacherDao.scoreStudent(stuId,score);
    }

    @Override
    public List<Object> getAllStudents(int teacherId) {
        List<Object> student = teacherDao.getAllStudents(teacherId);
        return student;
    }

    @Override
    public List<String> getSubjectByStuId(int stuId) {
        List<String> subjects = teacherDao.getSubjectByStuId(stuId);
        return subjects;
    }
}
