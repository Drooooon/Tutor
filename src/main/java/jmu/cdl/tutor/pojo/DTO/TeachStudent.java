package jmu.cdl.tutor.pojo.DTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachStudent {
    private  String name;
    private Integer studentId;
    private String grade;
}
