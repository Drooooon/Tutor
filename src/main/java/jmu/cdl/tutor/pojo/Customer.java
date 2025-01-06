package jmu.cdl.tutor.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="tb_customer")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(
            name = "customer_seq",
            sequenceName = "customer_sequence",
            initialValue = 20000001,
            allocationSize = 1
    )
    @Column(name = "cus_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
}