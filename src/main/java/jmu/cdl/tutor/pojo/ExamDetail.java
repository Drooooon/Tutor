package jmu.cdl.tutor.pojo;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_exam_detail")
public class ExamDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_id")
    private int tb_id; // 主键


    @Column(name = "exam_id")
    private int examId;    // 考试ID

    @Column(name = "student_id")
    private long stuId;    // 学生ID

    @Column(name = "score")
    private float score;   // 分数

}

