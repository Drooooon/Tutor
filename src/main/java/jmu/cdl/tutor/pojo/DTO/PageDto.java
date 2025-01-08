package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageDto {
    @NotNull
    @JsonProperty("page")
    private int page;

    @NotNull
    @JsonProperty("size")
    private int size;
}
