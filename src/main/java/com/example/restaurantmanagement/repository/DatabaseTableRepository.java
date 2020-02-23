package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.entity.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTableRepository implements TableRepository {
    private Connection conn;

    public DatabaseTableRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Table> getTables() {
        List<Table> tables = new ArrayList<>();

        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM tables");
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                tables.add(getTableFromResultSet(rs));
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return tables;
    }

    @Override
    public int addTable(Table table) {
        int result = -1;
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO tables (numSeats, description)" +
                    " VALUES(?, ?)");
            st.setInt(1, table.getNumSeats());
            st.setString(2, table.getDescription());

            st.executeUpdate();
            st.close();
            result = readLastInsertId();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Table getTableFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int numSeats = rs.getInt("numSeats");
        String description = rs.getString("description");

        return new Table(id,numSeats,description);
    }

    private int readLastInsertId() throws SQLException {
        int result = -1;

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");

        if (rs.next()) {
            result = rs.getInt(1);
        }

        rs.close();
        st.close();
        return result;
    }
}
