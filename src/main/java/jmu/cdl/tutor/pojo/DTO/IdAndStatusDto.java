package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdAndStatusDto {
    @NotNull
    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("status")
    private String status;
}
