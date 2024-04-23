package com.example.bankmanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "please fill the username!")
    @Size(min = 4, max = 10, message = "length must be between 4 and 10 characters!")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

    @NotEmpty(message = "please fill the password!")
    @Size(min = 6, message = "length of password must be more than 6 characters!")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @NotEmpty(message = "please fill the name!")
    @Size(min = 2, max = 20, message = "length of name must be between 2 and 20 characters!")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "please fill the email!")
    @Email
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotEmpty(message = "please fill the role!")
    @Pattern(regexp = "^CUSTOMER|EMPLOYEE|ADMIN$")
    @Column(columnDefinition = "varchar(20) not null check(role='CUSTOMER' or role='EMPLOYEE' or role='ADMIN')")
    private String role; //CUSTOMER, EMPLOYEE, ADMIN!



//    //Relation , User can be an employee or a customer (OneToOne)
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    @PrimaryKeyJoinColumn
//    private Employee employee;
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    @PrimaryKeyJoinColumn
//    private Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
