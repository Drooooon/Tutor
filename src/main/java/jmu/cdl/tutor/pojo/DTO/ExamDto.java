package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ExamDto {
    @NotNull
    @JsonProperty("id")
    Integer teacherId;

    @NotNull
    @JsonProperty("examDate")
    LocalDateTime examDate;

    @NotNull
    @JsonProperty("subjectId")
    Integer subjectId;

    @NotNull
    @JsonProperty("grade")
    String grade;




}
