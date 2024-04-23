package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Repository.AccountRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import com.example.bankmanagementsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;


    //by customer
    public void addAccount(Integer customerId, Account account){
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null){
            throw new ApiException("customer not found!");
        }
        customer.getAccounts().add(account);
        account.setCustomer(customer);
        accountRepository.save(account);
        customerRepository.save(customer);
    }


    //maybe i will change the name into (deActiveAccount)
    public void updateAccount(Integer accountId, Account account){
        Account acc = accountRepository.findAccountById(accountId);
        if (acc == null){
            throw new ApiException("Account not found!");
        }
        acc.setIsActive(account.getIsActive());
        accountRepository.save(acc);
    }

    public void deleteAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId);
        if (account==null){
            throw new ApiException("account not found!");
        }
        accountRepository.delete(account);
    }


    public List<Account> getAccounts(Integer customerId){
        List<Account> account = accountRepository.findAccountsByCustomerId(customerId);
        if (account.isEmpty()){
            throw new ApiException("the cucstomer has no account!");
        }
        return account;
    }

    public Account getAccount(Integer customerId, Integer accountId){
        Customer customer = customerRepository.findCustomerById(customerId);
        Account account = accountRepository.findAccountById(accountId);
        if (customer==null){
            throw new ApiException("customer not found!");
        } else if (account==null){
            throw new ApiException("account not found!");
        }
        return account;
    }
}
