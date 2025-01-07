package jmu.cdl.tutor.service.Impl;

import jmu.cdl.tutor.dao.TeacherDao;
import jmu.cdl.tutor.pojo.DTO.IdDto;
import jmu.cdl.tutor.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public List<Object> getAllSubject(IdDto idDto) {
        List<Object> subjects = teacherDao.getAllSubject(idDto.getId());

        return subjects;
    }
}
