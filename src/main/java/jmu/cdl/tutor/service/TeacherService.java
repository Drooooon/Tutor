package jmu.cdl.tutor.service;

import jmu.cdl.tutor.pojo.DTO.IdDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    public List<Object> getAllSubject(IdDto idDto);
}
