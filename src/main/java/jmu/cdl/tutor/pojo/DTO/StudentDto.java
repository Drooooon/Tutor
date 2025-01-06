package jmu.cdl.tutor.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {

    @NotBlank(message = "请输入姓名信息")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "请输入年龄信息")
    @JsonProperty("age")
    private int age;

    @JsonProperty("subjects")
    private List<String> subjects;

    @JsonProperty("grade")
    private String grade;

    @JsonProperty("customerId")
    private int customerId;




}
