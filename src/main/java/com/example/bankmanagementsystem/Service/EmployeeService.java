package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.UserRepository;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;


    //add employee by admin using user_id that are exist in the relation
//    public void addEmployee(Integer userId,Employee employee){
//        User user = userRepository.findUserById(userId);
//        if (user == null){
//            throw new ApiException("user not found!");
//        }
//        user.setEmployee(employee);
//        user.setRole("EMPLOYEE");
//        employeeRepository.save(employee);
//        userRepository.save(user);
//
//    }

    public void addEmployee(Integer userId, Employee employee){
        if (userId == null) {
            throw new ApiException("User ID must not be null!");
        }
        User user = userRepository.findUserById(userId);
        if (user == null){
            throw new ApiException("User not found!");
        }
        employee.setUser(user);
        user.setEmployee(employee);
        user.setRole("EMPLOYEE");
        employeeRepository.save(employee);
        userRepository.save(user);
    }




    //update employee << by admin
    public void updateEmployee(Integer emp_id, Employee employee){
        Employee emp = employeeRepository.findEmployeeById(emp_id);
        if (emp == null){
            throw new ApiException("employee not found!");
        }
        emp.setPosition(employee.getPosition());
        emp.setSalary(employee.getSalary());
        employeeRepository.save(emp);
    }

    //delete employee by admin
    public void deleteEmployee(Integer emp_id){
        Employee employee = employeeRepository.findEmployeeById(emp_id);
        if(employee==null){
            throw new ApiException("employee not found!");
        }
        employeeRepository.delete(employee);
    }


    //get employee by admin
    public Employee getEmployee(Integer emp_id){
        if (emp_id == null){
            throw new ApiException("employee id must not be null!");
        }
        Employee employee = employeeRepository.findEmployeeById(emp_id);
        if (employee == null){
            throw new ApiException("employee not found!");
        }
        return employee;
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


}
