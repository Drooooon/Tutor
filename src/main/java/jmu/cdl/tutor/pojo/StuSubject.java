
package jmu.cdl.tutor.pojo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_stu_subject")
public class StuSubject {
    @Id
    @Column(name = "tb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tbId; // 主键
    @Column(name = "student_id")
    private int studentId; // 学生ID
    @Column(name = "subject_id")
    private int subjectId;  // 科目ID
    @Column(name = "grade")
    private String grade;   // 年级
    @Column(name = "teacher_id")
    private Integer teacherId;     // 对应的教师ID
}