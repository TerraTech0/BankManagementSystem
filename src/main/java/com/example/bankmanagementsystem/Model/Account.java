package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "please fill account number!")
    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}") // must follow a specific format! (e.g., "XXXX-XXXX-XXXX-XXXX").
    @Column(columnDefinition = "int not null")
    private Integer accountNumber;

    @NotNull(message = "please fill the balance!")
    @Positive(message = "please enter only the positive numbers!")
    @Column(columnDefinition = "boolean not null")
    private Integer balance;

    @AssertFalse
    private Boolean isActive;

    //    //Many Accounts May Have One Customer (ManyToOne)
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    @JsonIgnore
//    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}
