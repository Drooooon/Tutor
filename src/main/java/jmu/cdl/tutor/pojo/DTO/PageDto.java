package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageDto {
    @NotNull
    @Min(value = 1, message = "Page size must be at least 1")
    @JsonProperty("page")
    private int page;

    @NotNull
    @JsonProperty("size")
    @Min(value = 0, message = "Page number must be non-negative")
    private int size;
}
