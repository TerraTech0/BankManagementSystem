package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import com.example.bankmanagementsystem.Service.EmployeeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;


    //by admin
    @PostMapping("/add/{userId}")//done
    public ResponseEntity addEmployee(@PathVariable Integer userId, @AuthenticationPrincipal Employee employee){
        logger.info("inside Add Employee!");
        employeeService.addEmployee(userId, employee);
        return ResponseEntity.ok().body(new ApiResponse("employee added successfully!"));
    }


    //by admin
    @GetMapping("/get/{emp_id}")//done
    public ResponseEntity getEmployee(@PathVariable Integer emp_id){
        logger.info("inside Get Employee!");
        return ResponseEntity.ok().body(employeeService.getEmployee(emp_id));
    }

    //by admin
    @GetMapping("/get-all")//done
    public ResponseEntity getAllEmployees(){
        logger.info("inside Get All Employees!");
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }


    //by admin
    @PutMapping("/update/{emp_id}")//done
    public ResponseEntity updateEmployee(@PathVariable Integer emp_id, @AuthenticationPrincipal Employee employee){
        logger.info("inside Update Employee!");
        employeeService.updateEmployee(emp_id, employee);
        return ResponseEntity.ok().body(new ApiResponse("employee updated successfully!"));
    }


    //by admin
    @DeleteMapping("/delete/{emp_id}")//done
    public ResponseEntity deleteEmployee(@PathVariable Integer emp_id){
        logger.info("inside Delete Employee!");
        employeeService.deleteEmployee(emp_id);
        return ResponseEntity.ok().body(new ApiResponse("employee deleted successfully!"));
    }








}
