package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScoreRequestDto {
    @NotNull(message = "请输入学生ID")
    @JsonProperty("studentId")
    private int studentId;

    @NotBlank(message = "请输入科目名称")
    @JsonProperty("subjectName")
    private String subjectName;
}
