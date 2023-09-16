package com.contasadm.contasadm.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.contasadm.contasadm.exceptions.BDExceptions;
import com.contasadm.contasadm.interfaces.IConnection;

public class AccountsList {

    int index = 0;
    int limit = 10;
    String filter = "";
    private IConnection conn = null;
    
    List<AccountListDTO> list = new ArrayList<>();

    public AccountsList(IConnection conn) {
        this.conn = conn;
    }

    public List<AccountListDTO> getList() {
        return list;
    }

    public void loadList() {
        StringBuffer queryBuffer = new StringBuffer();
        Object[] props = {};
        queryBuffer.append("SELECT id, name FROM accounts ");
        int indexProps = 0;
        if (filter != "") {
            queryBuffer.append("where name like ? ");
            props[indexProps++] = new String("%" + filter + "%");
        }
        queryBuffer.append("ORDER BY name LIMIT ? SKIP ? ");
        ResultSet rs = conn.result(queryBuffer.toString(), props);
        list.clear();
        try {
            while (rs.next()) {
                AccountListDTO ac = new AccountListDTO(rs.getString("id"), rs.getString("name"));
                list.add(ac);
            }
        } catch (SQLException e) {
            throw new BDExceptions(e.getMessage());
        }
    }
    
}
