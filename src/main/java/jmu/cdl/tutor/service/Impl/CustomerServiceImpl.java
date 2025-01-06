package jmu.cdl.tutor.service.Impl;

import jakarta.transaction.Transactional;
import jmu.cdl.tutor.dao.StuSubjectDao;
import jmu.cdl.tutor.dao.StudentDao;
import jmu.cdl.tutor.dao.SubjectDao;
import jmu.cdl.tutor.pojo.DTO.IdDto;
import jmu.cdl.tutor.pojo.DTO.StudentDto;
import jmu.cdl.tutor.pojo.DTO.SubjectsDto;
import jmu.cdl.tutor.pojo.StuSubject;
import jmu.cdl.tutor.pojo.Student;
import jmu.cdl.tutor.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomerServiceImpl 实现了 CustomerService
 * 接口，处理与客户相关的业务逻辑，包括添加学生、管理学生科目、查询学生信息和获取科目名称等操作。
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StuSubjectDao stuSubjectDao;

    @Autowired
    private SubjectDao subjectDao;

    /**
     * 添加学生
     * 
     * @param studentDto 包含学生信息的 DTO 对象
     * @return 添加成功的学生信息
     */
    @Override
    public Student addStudent(StudentDto studentDto) {
        Student student = new Student();
        List<String> subjects = studentDto.getSubjects();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setCustomerId(studentDto.getCustomerId());
        student.setGrade(studentDto.getGrade());
        studentDao.save(student);

        if (subjects != null) {
            for (String subject : subjects) {
                StuSubject stuSubject = new StuSubject();
                stuSubject.setStudentId(student.getId());
                stuSubject.setSubjectId(subjectDao.getIdBySubjectName(subject));
                stuSubjectDao.save(stuSubject);
            }
        }
        return student;
    }

    /**
     * 为学生添加科目
     * 
     * @param subjectsDto 包含学生ID和科目列表的 DTO 对象
     * @return 更新后的学生科目列表
     */
    @Override
    public List<String> addSubject(SubjectsDto subjectsDto) {
        List<String> subjects = subjectsDto.getSubjects();
        int studentId = subjectsDto.getStuId();
        for (String subject : subjects) {
            StuSubject stuSubject = new StuSubject();
            stuSubject.setStudentId(studentId);
            stuSubject.setSubjectId(subjectDao.getIdBySubjectName(subject));
            stuSubjectDao.save(stuSubject);
        }
        return subjectDao.getSubjectNamesByIds(stuSubjectDao.getSubjectIdsByStudentId(studentId));
    }

    /**
     * 为学生删除科目
     * 
     * @param subjectsDto 包含学生ID和科目列表的 DTO 对象
     * @return 更新后的学生科目列表
     */
    @Transactional
    @Override
    public List<String> deleteSubjects(SubjectsDto subjectsDto) {
        List<String> subjects = subjectsDto.getSubjects();
        int studentId = subjectsDto.getStuId();
        for (String subject : subjects) {
            int subjectId = subjectDao.getIdBySubjectName(subject);
            stuSubjectDao.deleteByStudentIdAndSubjectId(studentId, subjectId);
        }
        return subjectDao.getSubjectNamesByIds(stuSubjectDao.getSubjectIdsByStudentId(studentId));
    }

    /**
     * 根据客户ID获取学生列表
     * 
     * @param idDto 包含客户ID的 DTO 对象
     * @return 学生列表
     */
    @Override
    public List<Student> getStudentsByCustomerId(IdDto idDto) {
        List<Integer> studentIds = studentDao.getStudentsIdByCustomerId(idDto.getId());
        List<Student> students = new ArrayList<>();
        if (!studentIds.isEmpty()) {
            for (Integer studentId : studentIds) {
                Student student = studentDao.findById(studentId).orElse(null);
                students.add(student);
            }
        }
        return students;
    }

    /**
     * 获取学生未选科目
     * 
     * @param idDto 包含学生ID的 DTO 对象
     * @return 学生未选科目列表
     */
    @Override
    public List<String> getRestSubjects(IdDto idDto) {
        List<String> all = subjectDao.getAllSubjectNames();
        List<String> subjects = subjectDao.getSubjectNamesByIds(stuSubjectDao.getSubjectIdsByStudentId(idDto.getId()));
        List<String> result = new ArrayList<>(all);
        result.removeAll(subjects);
        return result;
    }

    /**
     * 获取所有科目名称
     * 
     * @return 所有科目名称的列表
     */
    @Override
    public List<String> getAllSubjectNames() {
        return subjectDao.getAllSubjectNames();
    }

    /**
     * 根据学生ID获取学生已选科目
     * 
     * @param idDto 包含学生ID的 DTO 对象
     * @return 学生已选科目列表
     */
    @Override
    public List<String> getSubjectsByStudentId(IdDto idDto) {
        return subjectDao.getSubjectNamesByIds(stuSubjectDao.getSubjectIdsByStudentId(idDto.getId()));
    }
}