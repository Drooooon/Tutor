package jmu.cdl.tutor.pojo.DTO;

import jmu.cdl.tutor.pojo.Exams;
import jmu.cdl.tutor.pojo.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamWithDetailsDto {
    private Integer examId;
    private String examName;
    private LocalDateTime examDate;
    private String grade;
}
