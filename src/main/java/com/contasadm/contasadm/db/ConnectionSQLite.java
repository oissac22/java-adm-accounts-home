package com.contasadm.contasadm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.contasadm.contasadm.exceptions.DBExceptions;
import com.contasadm.contasadm.interfaces.IConnection;

public class ConnectionSQLite implements IConnection {
    static private Connection conn = null;

    private static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    private void propsToStatmant(Object[] props, PreparedStatement statement) throws SQLException {
        if (props != null)
            for (int i = 0; i < props.length; i++) {
                if (props[i] == null)
                    return;
                statement.setObject(i + 1, props[i]);
            }
    }

    @Override
    public void exec(String query, Object[] props) {
        PreparedStatement statement;
        try {
            statement = getConnection().prepareStatement(query);
            propsToStatmant(props, statement);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
    }

    @Override
    public ResultSet result(String query, Object[] props) {
        PreparedStatement statement;
        try{
            statement = getConnection().prepareStatement(query);
            propsToStatmant(props, statement);
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());

        }
    }

}