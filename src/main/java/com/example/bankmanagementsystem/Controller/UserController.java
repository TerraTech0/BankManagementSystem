package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank-system")
@RequiredArgsConstructor
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    @PostMapping("/register") //done
    public ResponseEntity register(@RequestBody @Valid User user){
        logger.info("inside Register!");
        userService.register(user);
        return ResponseEntity.ok().body(new ApiResponse("Registered Successfully!"));
    }

    @PostMapping("/login/{username}/{password}")//done
    public ResponseEntity login(@PathVariable String username, @PathVariable String password){
        logger.info("inside Login!");
        userService.login(username, password);
        return ResponseEntity.ok().body(new ApiResponse("Logged In Succuessfully!"));
    }

    @GetMapping("/get-all")//done
    public ResponseEntity getAllUsers(){
        logger.info("inside get all!");
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{username}")//done
    public ResponseEntity deleteUser(@PathVariable String username){
        logger.info("inside delete user!");
        userService.deleteUser(username);
        return ResponseEntity.ok().body(new ApiResponse("User Deleted Successfully!"));
    }

    @PostMapping("/logout")//done
    public ResponseEntity logout(){
        logger.info("inside logout!");
        userService.logout();
        return ResponseEntity.ok().body(new ApiResponse("Logged Out!"));
    }
}
