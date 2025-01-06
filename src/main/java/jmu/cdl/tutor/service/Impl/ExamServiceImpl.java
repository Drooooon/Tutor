package jmu.cdl.tutor.service.Impl;

import jmu.cdl.tutor.dao.ExamDetailDao;
import jmu.cdl.tutor.dao.ExamsDao;
import jmu.cdl.tutor.dao.SubjectDao;
import jmu.cdl.tutor.pojo.DTO.ExamScoreDto;
import jmu.cdl.tutor.pojo.DTO.ScoreRequestDto;
import jmu.cdl.tutor.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ExamServiceImpl 实现了 ExamService 接口，处理与考试相关的业务逻辑，包括查询学生的考试成绩等操作。
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamsDao examsDao;

    @Autowired
    private ExamDetailDao examDetailDao;

    @Autowired
    private SubjectDao subjectDao;

    /**
     * 获取学生考试成绩
     * 
     * @param scoreRequestDto 包含学生ID和科目名称的 DTO 对象
     * @return 学生考试成绩的列表
     */
    @Override
    public List<ExamScoreDto> getExamScores(ScoreRequestDto scoreRequestDto) {
        int subjectId = subjectDao.getIdBySubjectName(scoreRequestDto.getSubjectName());
        List<Integer> examIds = examsDao.findExamIdBySubjectId(subjectId);
        List<String> examNames = examsDao.getExamNamesBySubjectId(examIds);
        List<Float> scores = examDetailDao.findScoresByExamIdAndStudentId(examIds, scoreRequestDto.getStudentId());
        List<Float> averages = examsDao.findAverageScoreByExamId(examIds);
        List<ExamScoreDto> result = new ArrayList<>();

        for (int i = 0; i < examIds.size(); i++) {
            String examName = examNames.get(i);
            Double score = i < scores.size() ? scores.get(i).doubleValue() : null;
            Double average = i < averages.size() ? averages.get(i).doubleValue() : null;
            result.add(new ExamScoreDto(examName, score, average));
        }

        return result;
    }
}