package com.example.bankmanagementsystem.SecurityConfig;


import com.example.bankmanagementsystem.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/bank-system/register", "/api/v1/bank-system/login/{username}/{password}").permitAll()
                .requestMatchers("/api/v1/bank-system/get-all", "/api/v1/bank-system/delete/{username}", "/api/v1/employee/add/{userId}"
                ,"/api/v1/bank-system/get/{emp_id}", "/api/v1/bank-system/update/{emp_id}", "/api/v1/bank-system/delete/{emp_id}","/api/v1/customer/active/{customerId}"
                ,"/api/v1/customer/block-account/{accountId}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/customer/add/{userId}", "/api/v1/customer/update/{customerId}", "/api/v1/customer/delete/{customerId}", "/api/v1/customer/add/{userId}"
                ,"/api/v1/customer/get/{customerId}", "/api/v1/customer/create-acccount/{customerId}", "/api/v1/customer/get-account/{accountId}", "/api/v1/customer/get-all-accounts/{customerId}"
                ,"/api/v1/customer/deposit/{accountId}/{amount}", "/api/v1/customer/transfer/{fromAcount}/{toAccount}/{amount}", "/api/v1/account/**").hasAuthority("CUSTOMER")
                .anyRequest().permitAll()
                .and()
                .logout().logoutUrl("/api/v1/bank-system/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
