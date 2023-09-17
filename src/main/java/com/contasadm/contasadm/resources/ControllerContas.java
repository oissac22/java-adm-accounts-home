package com.contasadm.contasadm.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contasadm.contasadm.entities.Account;
import com.contasadm.contasadm.entities.AccountsList;
import com.contasadm.contasadm.entities.HTTPExceptionResponse;
import com.contasadm.contasadm.exceptions.HTTPException;

@RestController
@RequestMapping("/accounts")
public class ControllerContas {
    
    @GetMapping({"", "/"})
    public ResponseEntity<AccountsList> accountsList() {
        AccountsList list = new AccountsList();
        list.loadList();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/")
    public ResponseEntity<Object> InsertAccount(@RequestBody Account account) {
        try {
            account.insert();
        } catch (HTTPException e) {
            return ResponseEntity.ok().body(new HTTPExceptionResponse(e));
        }
        return ResponseEntity.ok().body("success");
    }

}
