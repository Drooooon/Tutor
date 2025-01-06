package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Data
public class SubjectsDto {
    @NotNull
    @JsonProperty("subjects")
    List<String> Subjects;

    @NotNull
    @JsonProperty("id")
    int stuId;

}
