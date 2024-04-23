package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.AccountRepository;
import com.example.bankmanagementsystem.Repository.UserRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;


    //add customer!
    public void addCustomer(Integer userId, Customer customer){
        User user = userRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("user not found!");
        }
        customer.setUser(user);
        user.setCustomer(customer);
        user.setRole("CUSTOMER");
        customerRepository.save(customer);
        userRepository.save(user);
    }





    //update Customer
    public void updateCustomer(Integer customerId, Customer customer){
        Customer cus = customerRepository.findCustomerById(customerId);
        if (cus == null){
            throw new ApiException("customer not found!");
        }
        cus.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(cus);
    }

    public void deleteCustomer(Integer customerId){
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null){
            throw new ApiException("customer not found!");
        }
        customerRepository.delete(customer);
    }

    public Customer getCustomer(Integer customerId){
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null){
            throw new ApiException("customer not found!");
        }
        return customer;
    }


    //method to Create a new bank account via customer!
    public Account createAccount(Integer customerId, Account account) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found!");
        }
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    //method to Active a bank account
    public Account activateAccount( Integer accountId) {
            Account account = accountRepository.findAccountById(accountId);
            if (account == null) {
                throw new ApiException("Account not found!");
            }
            account.setIsActive(true);
            return accountRepository.save(account);
    }

    //method to View account details
    public Account getAccount(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found!");
        }
        return account;
    }

    //method to List user's accounts
    public Set<Account> getCustomerAccounts(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found!");
        }
        return customer.getAccounts();
    }

    //method to Deposit and withdraw money
    public Account depositMoney(Integer accountId, Integer amount) {
        // similar for withdrawal
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found!");
        }
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    //method to Transfer funds between accounts
    //while the customer may have many accounts so i should mention the account id!(fromAccount)
    public void transferFunds(Integer fromAccountId, Integer toAccountId, Integer amount) {
        Account fromAccount = accountRepository.findAccountById(fromAccountId);
        Account toAccount = accountRepository.findAccountById(toAccountId);
        if (fromAccount == null || toAccount == null) {
            throw new ApiException("Account not found!");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }





    //method to Block bank account via admin!
    public Account blockAccount(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found!");
        }
        account.setIsActive(false);
        return accountRepository.save(account);
    }
}
