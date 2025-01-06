package jmu.cdl.tutor.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private int id; // 账号ID
    private String name; // 用户名
    private String password; // 密码
    private String userType; // 用户类型 (customer, teacher, admin)


}
