package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginAndDeleteDto {
    @NotNull(message = "id is required")
    @JsonProperty("id")
    private int id;

    @NotBlank(message = "password is required")
    @JsonProperty("password")
    private String password;
}
