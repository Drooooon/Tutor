package jmu.cdl.tutor.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jmu.cdl.tutor.pojo.DTO.StatusDto;
import jmu.cdl.tutor.pojo.DTO.StuSubjectDto;
import jmu.cdl.tutor.pojo.Teacher;

import java.util.List;

public interface AdminService {

    List<Integer> getTeacherIdsByStatus(@NotBlank String status);

    List<Teacher> getTeachersByIds(List<Integer> ids);

    void processTeacher(int id, String status);

    List<Integer> getStudentIds();

    List<StuSubjectDto> getStudents(@Valid StatusDto statusDto);
}
