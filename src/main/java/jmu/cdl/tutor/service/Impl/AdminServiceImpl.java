package jmu.cdl.tutor.service.Impl;

import jmu.cdl.tutor.dao.*;
import jmu.cdl.tutor.pojo.DTO.*;
import jmu.cdl.tutor.pojo.StuSubject;
import jmu.cdl.tutor.pojo.Student;
import jmu.cdl.tutor.pojo.Subject;
import jmu.cdl.tutor.pojo.Teacher;
import jmu.cdl.tutor.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Teacher> getTeachersByIds(List<Integer> ids) {
        return teacherDao.getTeachersByIds(ids);
    }

    /**
     * 处理教师申请，更新教师状态
     * @param idAndStatusDto 包含教师ID和状态的 DTO 对象
     */
    @Override
    public void processTeacher(IdAndStatusDto idAndStatusDto) {
        teacherDao.updateStatusById(idAndStatusDto.getId(), idAndStatusDto.getStatus());
    }

    /**
     * 获取所有学生ID
     * @return 学生ID列表
     */
    @Override
    public List<Integer> getStudentIds() {
        return stuSubjectDao.getStudentIds();
    }

    /**
     * 根据学生状态获取学生信息
     * @param statusDto 包含学生状态的 DTO 对象
     * @return 学生信息列表
     */
    @Override
    public List<StuSubjectDto> getStudents(StatusDto statusDto) {
        List<Integer> ids = getStudentIds();
        List<StuSubjectDto> result = new ArrayList<>();
        for (int id : ids) {
            Optional<Student> student = studentDao.findById(id);
            if (student.isPresent()) {
                List<Integer> subjectIds = stuSubjectDao.getNullTeacherSubjectIdsByStudentId(id);
                List<String> subjects = subjectDao.getSubjectNamesByIds(subjectIds);
                for (String subject : subjects) {
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

    /**
     * 获取所有科目信息
     * @return 科目信息列表
     */
    @Override
    public List<Subject> getSubjectInfo() {
        return subjectDao.findAllSubjects();
    }

    /**
     * 更新科目价格
     * @param priceDto 包含科目名称和新价格的 DTO 对象
     * @return 更新结果信息
     */
    @Override
    public String updatePrice(PriceDto priceDto) {
        int id = subjectDao.getIdBySubjectName(priceDto.getName());
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
        int subjectId = assignTeacherDto.getSubjectId();
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