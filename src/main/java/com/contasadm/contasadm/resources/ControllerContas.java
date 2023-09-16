package com.contasadm.contasadm.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contasadm.contasadm.entities.Account;

@RestController
@RequestMapping("/accounts")
public class ControllerContas {
    
    @GetMapping({"", "/"})
    public ResponseEntity<Account> accountsList() {
        Account account = new Account("valid_id", "Conta de luz");
        return ResponseEntity.ok().body(account);
    }

}
