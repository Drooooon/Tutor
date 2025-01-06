package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePasswordDto {
    @NotNull
    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("oldPassword")
    private String oldPassword;

    @NotBlank
    @JsonProperty("newPassword")
    private String newPassword;

}
