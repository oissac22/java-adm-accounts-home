package com.contasadm.contasadm.entities;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import com.contasadm.contasadm.db.Connection;
import com.contasadm.contasadm.exceptions.HTTPException;

public class Account implements Serializable {
    private String id = "";
    private String name = "";
    private Instant created = Instant.now();
    private Instant update = Instant.now();

    public Account() {}

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        update = Instant.now();
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        update = Instant.now();
        this.name = name;
    }
    
    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
    
    public Instant getUpdate() {
        return update;
    }

    public void setUpdate(Instant update) {
        this.update = update;
    }

    public void load(String id) {
        Object[] props = {id};
        ResultSet rs = Connection.connection().result("SELECT id, name, created, updateDate FROM accounts WHERE id=?", props);
        try {
            if (rs.next()) {
                setId(rs.getString("id"));
                setName(rs.getString("name"));
                setCreated(Instant.parse(rs.getString("created")));
                setUpdate(Instant.parse(rs.getString("updateDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert() {
        if (getId() == null || getId() == "")
            setId(RandomKey.generateRandonKeyOnHex(10));
        if (getName() == null || getName() == "")
            throw new HTTPException("Nome não informado", 401);
        Object[] props = {getId(), getName(), getCreated(), getUpdate()};
        Connection.connection().exec("INSERT INTO accounts (id, name, created, updateDate) VALUES (?, ?, ?, ?)", props);
    }

    public void update() {
        if (getId() == null || getId() == "")
            throw new HTTPException("Id não informado", 401);
        if (getName() == null || getName() == "")
            throw new HTTPException("Nome não informado", 401);
        Object[] props = {getName(), getUpdate(), getId()};
        Connection.connection().exec("UPDATE accounts SET name=?, updateDate=? WHERE id=?", props);
    }

    public void delete() {
        Object[] props = {getId()};
        Connection.connection().exec("DELETE FROM accounts WHERE id=?", props);
    }

}
