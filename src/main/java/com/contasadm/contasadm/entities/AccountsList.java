package com.contasadm.contasadm.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.contasadm.contasadm.db.Connection;
import com.contasadm.contasadm.exceptions.DBExceptions;

public class AccountsList {

    int index = 0;
    int limit = 10;
    String filter = "";
    
    List<AccountListDTO> list = new ArrayList<>();

    public List<AccountListDTO> getList() {
        return list;
    }

    public void loadList() {
        StringBuffer queryBuffer = new StringBuffer();
        Object[] props = new Object[3];
        queryBuffer.append("SELECT id, name FROM accounts ");
        int indexProps = 0;
        if (filter != "") {
            queryBuffer.append("where name like ? ");
            props[indexProps++] = new String("%" + filter + "%");
        }
        queryBuffer.append("ORDER BY name LIMIT ? OFFSET ? ");
        props[indexProps++] = limit;
        props[indexProps++] = index;
        ResultSet rs = Connection.connection().result(queryBuffer.toString(), props);
        list.clear();
        try {
            while (rs.next()) {
                AccountListDTO ac = new AccountListDTO(rs.getString("id"), rs.getString("name"));
                list.add(ac);
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
    }
    
}
