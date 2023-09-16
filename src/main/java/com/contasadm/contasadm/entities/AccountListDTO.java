package com.contasadm.contasadm.entities;

import java.io.Serializable;

public class AccountListDTO implements Serializable {
    private String id = "";
    private String name = "";

    public AccountListDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
