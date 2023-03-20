package pl.dmcs.iwamzelazko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.iwamzelazko.model.Account;
import pl.dmcs.iwamzelazko.model.Student;
import pl.dmcs.iwamzelazko.repository.AccountRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountRESTController {
    private AccountRepository accountRepository;

    @Autowired
    public AccountRESTController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> findAccount(@PathVariable("id") long id){
        Account account = accountRepository.findById(id);
        if(account == null){
            System.out.println("Account with id " + id + " not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Account> deleteAccount(@PathVariable("id") long id){
        Account account = accountRepository.findById(id);
        if(account == null){
            System.out.println("Unable to delete. Account with id " + id + " not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
        accountRepository.delete(account);
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable("id") long id){
        account.setId(id);
        accountRepository.save(account);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Account> updatePartOfAccount(@RequestBody Map<String, Object> updates, @PathVariable("id") long id){
        Account account = accountRepository.findById(id);
        if(account == null){
            System.out.println("Account with id " + id + " not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
        if(updates.containsKey("accountName")){
            account.setAccountName((String) updates.get("accountName"));
        }
        if(updates.containsKey("student")){
            account.setStudent((Student) updates.get("student"));
        }
        accountRepository.save(account);
        return new ResponseEntity<Account>(HttpStatus.OK);
    }
}
