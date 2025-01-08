package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.aspectj.weaver.ast.Not;

@Data
public class StatusDto {
    @NotBlank
    @JsonProperty("status")
    private String status;

    @NotNull
    @JsonProperty("page")
    private int page;

    @NotNull
    @JsonProperty("size")
    private int size;
}
