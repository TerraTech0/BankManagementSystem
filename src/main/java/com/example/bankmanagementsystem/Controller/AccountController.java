package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    //customer
    @PostMapping("/add/{customerId}")//done
    public ResponseEntity addAccount(@PathVariable Integer customerId, @AuthenticationPrincipal Account account){
        logger.info("inside Add Account!");
        accountService.addAccount(customerId, account);
        return ResponseEntity.ok().body(new ApiResponse("account added successfully!"));
    }

    //customer
    @PutMapping("/update/{customerId}")//done
    public ResponseEntity updateAccount(@PathVariable Integer customerId, @AuthenticationPrincipal Account account){
        logger.info("inside update Account!");
        accountService.updateAccount(customerId, account);
        return ResponseEntity.ok().body(new ApiResponse("account updated successfully!"));
    }


    //customer
    @DeleteMapping("/delete/{customerId}")//done
    public ResponseEntity deleteAccount(@PathVariable Integer customerId){
        logger.info("inside Delete Account!");
        accountService.deleteAccount(customerId);
        return ResponseEntity.ok().body(new ApiResponse("account deleted successfully!"));
    }

    //customer
    @GetMapping("/get-all/{customerId}")//done
    public ResponseEntity getAccounts(@PathVariable Integer customerId){
        logger.info("inside get Accounts!");
        return ResponseEntity.ok().body(accountService.getAccounts(customerId));
    }


    //customer
    @GetMapping("/get/{customerId}/{accountId}")//done
    public ResponseEntity getAccount(@PathVariable Integer customerId, @PathVariable Integer accountId){
        logger.info("inside get Account!");
        return ResponseEntity.ok().body(accountService.getAccount(customerId, accountId));
    }


}
