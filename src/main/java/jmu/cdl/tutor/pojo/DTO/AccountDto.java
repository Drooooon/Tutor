package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
public class AccountDto {

    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "password is required")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "email is required")
    @JsonProperty("email")
    private String email;

    @JsonProperty("userType")
    private String userType;

}

