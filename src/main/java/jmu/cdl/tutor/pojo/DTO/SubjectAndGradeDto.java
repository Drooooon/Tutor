package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectAndGradeDto {
    @NotBlank
    @JsonProperty("subject")
    private String subject;

    @NotBlank
    @JsonProperty("grade")
    private String grade;
}
