package jmu.cdl.tutor.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "tb_exams")
@Entity
@NoArgsConstructor
public class Exams {
    @Id
    @Column(name = "exam_id")
    private int examId;         // 考试ID

    @Column(name = "subject_id")
    private int subjectId;      // 科目ID

    @Column(name = "teacher_id")
    private int teacherId;      // 教师ID

    @Column(name = "grade")
    private String grade;          // 年级

    @Column(name = "exam_date")
    private String examDate;    // 考试日期

    @Column(name = "average_score")
    private float averageScore; // 平均分

    @Column(name = "max_score")
    private float maxScore;     // 最高分

    @Column(name = "min_score")
    private float minScore;     // 最低分

    @Column(name = "exam_name")
    private String examName;    // 考试名称

}
