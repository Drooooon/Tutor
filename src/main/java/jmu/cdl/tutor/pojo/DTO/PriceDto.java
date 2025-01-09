package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PriceDto {
    @NotNull
    @JsonProperty("id")
    int id;

    @NotNull
    @JsonProperty("price")
    Double price;
}
