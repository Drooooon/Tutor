package jmu.cdl.tutor.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDetailDto {
    private  Integer id;
    private String name;
    private Float score;
}
