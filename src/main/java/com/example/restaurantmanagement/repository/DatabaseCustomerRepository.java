package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCustomerRepository implements CustomerRepository{
    private Connection conn;

    public DatabaseCustomerRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List getCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM customers");
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                customers.add(getCustomerFromResultSet(rs));
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return customers;
    }

    @Override
    public int addCustomer(Customer customer) {
        int result = -1;
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO customers (firstName, lastName, email)" +
                    " VALUES(?, ?, ?)");
            st.setString(1, customer.getFirstName());
            st.setString(2, customer.getLastName());
            st.setString(3,customer.getEmail());

            st.executeUpdate();
            st.close();
            result = readLastInsertId();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public Customer findById(int id) {
            Customer customer = null;

            try {
                PreparedStatement st = conn.prepareStatement("SELECT * FROM customers WHERE id=?");
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();

                if(rs.next()) {
                    customer = getCustomerFromResultSet(rs);
                }

                rs.close();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return customer;
    }


    private Customer getCustomerFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String email = rs.getString("email");


        return new Customer(id, firstName,lastName,email);
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
