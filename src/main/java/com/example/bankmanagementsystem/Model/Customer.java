package com.example.bankmanagementsystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "please fill the phone number!")
    @Pattern(regexp = "^05")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;


//    //User can be an employee or a customer (OneToOne).
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @MapsId
//    @JsonIgnore
//    private User user;
//
//    //One Customer May Have Many Accounts (OneToMany)
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
//    private Set<Account> accounts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Account> accounts;

}
