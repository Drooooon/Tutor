package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherInfoDto {
    @JsonProperty("teacherId")
    private int teacherId;

    @JsonProperty("teacherName")
    private String teacherName;
}
