package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "please fill position!")
    @Column(columnDefinition = "varchar(20) not null")
    private String position;

    @NotNull(message = "please fill salary!")
    @Positive(message = "please enter positive numbers only!")
    @Column(columnDefinition = "int not null")
    private Integer salary;


    //User can be an employee or a customer (OneToOne).
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @MapsId
//    @JsonIgnore
//    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @MapsId
    private User user;
}
