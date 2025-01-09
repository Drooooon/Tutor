package jmu.cdl.tutor.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_teach_info")
public class TeachInfo {

    @Id
    @Column(name = "tb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 教学信息ID

    @Column(name = "teacher_id")
    private int teacherId; // 教师ID

    @Column(name = "subject_id")
    private int subjectId;  // 科目ID

    @Column(name = "grade")
    private String grade;   // 年级

}

