package com.contasadm.contasadm.interfaces;

import java.sql.ResultSet;

public interface IConnection {
    public void exec(String query, Object[] props);
    public ResultSet result(String query, Object[] props);
}
