package com.contasadm.contasadm.db;

import com.contasadm.contasadm.interfaces.IConnection;

public class Connection {
    private static IConnection conn;

    public static IConnection connection() {
        if (conn != null)
            return conn;
        conn = new ConnectionSQLite();
        return conn;
    }
}
