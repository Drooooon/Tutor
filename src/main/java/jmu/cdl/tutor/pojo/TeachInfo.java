package jmu.cdl.tutor.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private int id; // 教学信息ID

    @Column(name = "teacher__id")
    private int teacherId; // 教师ID

    @Column(name = "subject_id")
    private int subjectId;  // 科目ID

    @Column(name = "grade")
    private String grade;   // 年级

}

