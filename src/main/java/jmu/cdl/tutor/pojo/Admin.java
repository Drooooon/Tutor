package jmu.cdl.tutor.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Table(name = "tb_admin")
@Data
@Entity
public class Admin {
    @Id
    @NotNull
    @JsonProperty("id")
    int id;

    @NotBlank
    @JsonProperty("name")
    String name;

    @NotBlank
    @JsonProperty("email")
    String email;
}
