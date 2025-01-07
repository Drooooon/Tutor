package jmu.cdl.tutor.service.Impl;

import jmu.cdl.tutor.dao.StuSubjectDao;
import jmu.cdl.tutor.dao.StudentDao;
import jmu.cdl.tutor.dao.SubjectDao;
import jmu.cdl.tutor.dao.TeacherDao;
import jmu.cdl.tutor.pojo.DTO.IdAndStatusDto;
import jmu.cdl.tutor.pojo.DTO.StatusDto;
import jmu.cdl.tutor.pojo.DTO.StuSubjectDto;
import jmu.cdl.tutor.pojo.Student;
import jmu.cdl.tutor.pojo.Teacher;
import jmu.cdl.tutor.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StuSubjectDao stuSubjectDao;
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public List<Integer> getTeacherIdsByStatus(String status) {
        return teacherDao.getIdsByStatus(status);
    }

    @Override
    public List<Teacher> getTeachersByIds(List<Integer> ids) {
        return teacherDao.getTeachersByIds(ids);
    }

    @Override
    public void processTeacher(IdAndStatusDto idAndStatusDto) {
        teacherDao.updateStatusById(idAndStatusDto.getId(), idAndStatusDto.getStatus());
    }

    @Override
    public List<Integer> getStudentIds() {
        return stuSubjectDao.getStudentIds();
    }

    @Override
    public List<StuSubjectDto> getStudents(StatusDto statusDto) {
        List<Integer> ids = getStudentIds();
        List<StuSubjectDto> result = new ArrayList<>();
        for(int id:ids){
            Optional<Student> student = studentDao.findById(id);
            if(student.isPresent()){
                List<Integer> subjectIds = stuSubjectDao.getNullTeacherSubjectIdsByStudentId(id);
                List<String> subjects = subjectDao.getSubjectNamesByIds(subjectIds);
                for(String subject:subjects){
                    StuSubjectDto stuSubjectDto = new StuSubjectDto();
                    stuSubjectDto.setGrade(student.get().getGrade());
                    stuSubjectDto.setName(student.get().getName());
                    stuSubjectDto.setSubject(subject);
                    result.add(stuSubjectDto);
                }
            }
        }
        return result;
    }


}
