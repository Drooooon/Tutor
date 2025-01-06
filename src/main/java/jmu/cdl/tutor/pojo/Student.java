package jmu.cdl.tutor.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stu_seq")
    @SequenceGenerator(
            name = "stu_seq",
            sequenceName = "student_sequence",
            initialValue = 40000001,
            allocationSize = 1
    )
    @Column(name = "stu_id")
    private int id;        // 学生ID

    @Column(name = "cus_id")
    private int customerId;   // 对应的客户ID

    @Column(name = "name")
    private String name;       // 学生姓名

    @Column(name = "age")
    private int age;           // 年龄

    @Column(name = "grade")
    private String grade;      // 年级（高一, 高二, 高三）

}
