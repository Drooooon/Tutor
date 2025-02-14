package jmu.cdl.tutor.service.Impl;

import jmu.cdl.tutor.dao.*;
import jmu.cdl.tutor.pojo.DTO.*;
import jmu.cdl.tutor.pojo.StuSubject;
import jmu.cdl.tutor.pojo.Student;
import jmu.cdl.tutor.pojo.Subject;
import jmu.cdl.tutor.pojo.Teacher;
import jmu.cdl.tutor.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * AdminServiceImpl 实现了 AdminService 接口，处理与管理员相关的业务逻辑，包括获取教师信息、处理教师申请、获取学生信息、分配教师、获取科目信息和更新科目价格等。
 */
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

    @Autowired
    private TeachInfoDao teachInfoDao;

    /**
     * 根据教师状态获取教师ID列表
     * @param status 教师状态
     * @return 教师ID列表
     */
    @Override
    public List<Integer> getTeacherIdsByStatus(String status) {
        return teacherDao.getIdsByStatus(status);
    }

    /**
     * 根据教师ID列表获取教师信息
     * @param ids 教师ID列表
     * @return 教师信息列表
     */
    @Override
    public Page<Teacher> getTeachersByIds(List<Integer> ids, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return teacherDao.getTeachersByIds(ids, pageable);
    }

    /**
     * 处理教师申请，更新教师状态
     * @param idAndStatusDto 包含教师ID和状态的 DTO 对象
     */
    @Override
    public void processTeacher(IdAndStatusDto idAndStatusDto) {
        teacherDao.updateStatusById(idAndStatusDto.getId(), idAndStatusDto.getStatus());
    }

    @Override
    public List<Integer> getStudentIds() {
        return stuSubjectDao.getStudentIds();
    }

    /**
     * 获取所有学生ID
     * @return 学生ID列表
     */
    @Override
    public Page<Integer> getStudentIdPages(Pageable pageable) {
        return stuSubjectDao.getStudentIdPages(pageable);
    }

    @Override
    public List<TeacherInfoDto> getTeacherBySubjectAndGrade(SubjectAndGradeDto subjectAndGradeDto) {
        String grade = subjectAndGradeDto.getGrade();
        String subject = subjectAndGradeDto.getSubject();
        int subjectId = subjectDao.getIdBySubjectName(subject);
        List<Integer> teacherIds = teachInfoDao.getTeacherIdByGradeAndSubjectId(grade, subjectId);
        List<TeacherInfoDto> result = new ArrayList<>();
        for(int teacherId:teacherIds){
            String name = teacherDao.getNameById(teacherId);
            TeacherInfoDto teacherInfoDto = new TeacherInfoDto();
            teacherInfoDto.setTeacherId(teacherId);
            teacherInfoDto.setTeacherName(name);
            result.add(teacherInfoDto);
        }
        return result;
    }

    /**
     * 根据学生状态获取学生信息
     * @param pageDto 包含学生状态的 DTO 对象
     * @return 学生信息列表
     */
    @Override
    public Page<StudentAssignDto> getStudents(PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getPage()-1, pageDto.getSize());
        Page<Integer> ids = getStudentIdPages(pageable);
        List<StudentAssignDto> result = new ArrayList<>();
        for (int id : ids) {
            Optional<Student> student = studentDao.findById(id);
            if (student.isPresent()) {
                List<Integer> subjectIds = stuSubjectDao.getNullTeacherSubjectIdsByStudentId(id);
                List<String> subjects = subjectDao.getSubjectNamesByIds(subjectIds);
                for (String subject : subjects) {
                    StudentAssignDto studentAssignDto = new StudentAssignDto();
                    studentAssignDto.setGrade(student.get().getGrade());
                    studentAssignDto.setName(student.get().getName());
                    studentAssignDto.setSubject(subject);
                    studentAssignDto.setStudentId(id);
                    result.add(studentAssignDto);
                }
            }
        }
        return new PageImpl<>(result, pageable, ids.getTotalElements());
    }

    /**
     * 获取所有科目信息
     * @return 科目信息列表
     */
    @Override
    public Page<Subject> getSubjectInfo(PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getPage()-1, pageDto.getSize());
        return subjectDao.findAllSubjectPages(pageable);
    }

    /**
     * 更新科目价格
     * @param priceDto 包含科目名称和新价格的 DTO 对象
     * @return 更新结果信息
     */
    @Override
    public String updatePrice(PriceDto priceDto) {
        int id = priceDto.getId();
        subjectDao.updatePriceById(id, priceDto.getPrice());
        return "更新成功价格为" + priceDto.getPrice();
    }

    /**
     * 为学生分配教师
     * @param assignTeacherDto 包含学生ID和教师ID的 DTO 对象
     * @return 分配结果信息
     */
    @Override
    public String assignTeacher(AssignTeacherDto assignTeacherDto) {
        int studentId = assignTeacherDto.getStudentId();
        int teacherId = assignTeacherDto.getTeacherId();
        int subjectId = subjectDao.getIdBySubjectName(assignTeacherDto.getSubject());
        stuSubjectDao.updateTeacherIdByStudentIdAndSubjectId(studentId, teacherId, subjectId);
        return "分配成功";
    }

    @Override
    public void fastAssign() {
        List<Integer> tableIds = stuSubjectDao.getTableIdsByTeacherIdNull();
        for(int tableId:tableIds){
            autoAssign(tableId);
        }
    }

    public void autoAssign(int tableId) {
        StuSubject stuSubject = stuSubjectDao.findById(tableId).get();
        String grade = stuSubject.getGrade();
        int subjectId = stuSubject.getSubjectId();
        List<Integer> teacherIds = teachInfoDao.getTeacherIdByGradeAndSubjectId(grade, subjectId);
        int teacherId = stuSubjectDao.getTeacherIdWithMinCount(teacherIds);
        stuSubjectDao.updateTeacherIdByStudentIdAndSubjectId(stuSubject.getStudentId(), teacherId, subjectId);
    }


}