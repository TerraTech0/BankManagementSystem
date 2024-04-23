package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final MyUserDetailsService myUserDetailsService;
    //method to register
    public void register(User user){
        user.setRole("CUSTOMER");
        String hasPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hasPassword);
        userRepository.save(user);

    }

    //method to get all users by admin!
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //method to udpate user by admin! (NO NEED)
//    public void updateUser(Integer id, User user){
//        User u = authRepository.findUserById(id);
//        if (u==null){
//            throw new ApiException("user not found!");
//        }
//        u.setEmail(user.getEmail());
//        authRepository.save(u);
//    }


    //method to delete user by admin!
    public void deleteUser(String username){
        User user = userRepository.findUserByUsername(username);
        if (user==null){
            throw new ApiException("username not found!");
        }
        userRepository.delete(user);
    }


    public UserDetails login(String username, String password){
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        if (new BCryptPasswordEncoder().matches(password, userDetails.getPassword())){
            return userDetails;
        } else {
            throw new ApiException("Invalid username or password!");
        }
    }


    public void logout(){

    }

}
