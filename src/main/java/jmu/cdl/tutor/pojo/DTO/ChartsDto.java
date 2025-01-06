package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChartsDto {
    @NotBlank
    @JsonProperty("subjectName")
    private String subjectName;

    @NotNull
    @JsonProperty("studentId")
    private int studentId;
}
