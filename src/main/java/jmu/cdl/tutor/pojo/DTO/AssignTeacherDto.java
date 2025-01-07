package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignTeacherDto {
    @NotNull
    @JsonProperty("studentId")
    private int studentId;

    @NotNull
    @JsonProperty("teacherId")
    private int teacherId;
}
