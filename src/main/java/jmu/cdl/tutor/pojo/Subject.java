package jmu.cdl.tutor.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_subject")
public class Subject {
    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;      // 科目ID

    @Column(name = "subject_name")
    private String subjectName; // 科目名称

    @Column(name = "price")
    private float price;        // 价格
}

