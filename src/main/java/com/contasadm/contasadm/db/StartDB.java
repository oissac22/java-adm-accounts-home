package com.contasadm.contasadm.db;

import com.contasadm.contasadm.interfaces.IConnection;

public class StartDB {
    private static IConnection conn = new ConnectionSQLite();
    private static boolean started = false;

    public static void start() {
        if (started)
            return;
        started = true;
        conn.exec("CREATE TABLE IF NOT EXISTS accounts ( id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, created DATETIME NOT NULL, updateDate DATETIME NOT NULL)", null);
    }

}


// id, name, created, update FROM account