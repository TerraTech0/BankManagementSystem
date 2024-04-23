package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import com.example.bankmanagementsystem.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.RectangularShape;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    //customer
    @PostMapping("/add/{userId}")//done
    public ResponseEntity addCustomer(@PathVariable Integer userId, @AuthenticationPrincipal Customer customer){
        logger.info("inside Add Customer!");
        customerService.addCustomer(userId, customer);
        return ResponseEntity.ok().body(new ApiResponse("customer added successfully!"));
    }

    //customer
    @PutMapping("/update/{customerId}")//done
    public ResponseEntity updateCustomer(@PathVariable Integer customerId, @AuthenticationPrincipal Customer customer){
        logger.info("inside Update Customer!");
        customerService.updateCustomer(customerId, customer);
        return ResponseEntity.ok().body(new ApiResponse("customer updated successfully!"));
    }

    //by customer
    @DeleteMapping("/delete/{customerId}")//done
    public ResponseEntity deleteCustomer(@PathVariable Integer customerId){
        logger.info("inside delete Customer!");
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok().body(new ApiResponse("customer deleted successfully!"));
    }

    //by customer
    @GetMapping("/get/{customerId}")//done
    public ResponseEntity getCustomer(@PathVariable Integer customerId){
        logger.info("inside Get Customer!");
        return ResponseEntity.ok().body(customerService.getCustomer(customerId));
    }

    //by customer
    @PostMapping("/create-acccount/{customerId}")//done
    public ResponseEntity createAccount(@PathVariable Integer customerId, @AuthenticationPrincipal Account account ){
        logger.info("inside Create Account!");
        customerService.createAccount(customerId, account);
        return ResponseEntity.ok().body(new ApiResponse("account created successfully!"));
    }

    //by admin
    @PutMapping("/active/{customerId}")//done
    public ResponseEntity activeAccount(@PathVariable Integer customerId){
        logger.info("inside Active Account!");
        customerService.activateAccount(customerId);
        return ResponseEntity.ok().body(new ApiResponse("account active successfully!"));
    }

    //customer
    @GetMapping("/get-account/{accountId}")//done
    public ResponseEntity getAccount(@PathVariable Integer accountId){
        logger.info("inside get Account!");
        return ResponseEntity.ok().body(customerService.getAccount(accountId));
    }

    //customer
    @GetMapping("/get-all-accounts/{customerId}")//done
    public ResponseEntity getCustomerAccounts(@PathVariable Integer customerId){
        logger.info("inside Add Customer!");
        return ResponseEntity.ok().body(customerService.getCustomerAccounts(customerId));
    }

    //customer
    @PutMapping("/deposit/{accountId}/{amount}")//done
    public ResponseEntity depositMoney(@PathVariable Integer accountId, @PathVariable Integer amount){
        logger.info("inside deposit Money!");
        customerService.depositMoney(accountId, amount);
        return ResponseEntity.ok().body(new ApiResponse(("deposit successful!")));
    }

    //customer
    @PutMapping("/transfer/{fromAccount}/{toAccount}/{amount}")//done
    public ResponseEntity transferFunds(@PathVariable Integer fromAccount, @PathVariable Integer toAccount, @PathVariable Integer amount){
        logger.info("inside transfer funds!");
        customerService.transferFunds(fromAccount, toAccount, amount);
        return ResponseEntity.ok().body(new ApiResponse(("transfer successful!")));
    }


    //admin
    @PutMapping("/block-account/{accountId}")//done
    public ResponseEntity blockAccount(@PathVariable Integer accountId){
        logger.info("inside block account!");
        customerService.blockAccount(accountId);
        return ResponseEntity.ok().body(new ApiResponse(("account blocked successfully!")));
    }












}
