package com.contasadm.contasadm.entities;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import com.contasadm.contasadm.interfaces.IConnection;

public class Account implements Serializable {
    private String id = "";
    private String name = "";
    private Instant created = Instant.now();
    private Instant update = Instant.now();
    private IConnection conn = null;

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

    public void load() {
        Object[] props = {this.getId()};
        ResultSet rs = conn.result("SELECT id, name, created, update FROM accounts WHERE id=?", props);
        try {
            if (rs.next()) {
                setId(rs.getString("id"));
                setName(rs.getString("name"));
                setCreated(Instant.parse(rs.getString("created")));
                setUpdate(Instant.parse(rs.getString("update")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert() {
        Object[] props = {getId(), getName(), getCreated(), getUpdate()};
        conn.update("INSERT INTO accounts (id, name, created, update) VALUES (?, ?, ?, ?)", props);
    }

    public void update() {
        Object[] props = {getName(), getUpdate(), getId()};
        conn.update("UPDATE accounts SET name=?, update=? WHERE id=?", props);
    }

    public void delete() {
        Object[] props = {getId()};
        conn.update("DELETE FROM accounts WHERE id=?", props);
    }

}
