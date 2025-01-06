package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StuSubjectDto {
    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotBlank
    @JsonProperty("subject")
    private String subject;

    @NotBlank
    @JsonProperty("grade")
    private String grade;



}
