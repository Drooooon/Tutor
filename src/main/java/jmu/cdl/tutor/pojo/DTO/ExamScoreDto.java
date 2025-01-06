package jmu.cdl.tutor.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamScoreDto {
    private String examName;
    private double score;
    private double average;
}
