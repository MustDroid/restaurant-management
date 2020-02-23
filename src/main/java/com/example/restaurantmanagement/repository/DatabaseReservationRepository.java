package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.entity.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseReservationRepository implements ReservationRepository {
    private Connection conn;

    public DatabaseReservationRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Reservation> getReservations() {
        List<Reservation> reservations = new ArrayList<>();

        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM reservations");
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                reservations.add(getReservationFromResultSet(rs));
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return reservations;
    }

    @Override
    public boolean makeReservation(Reservation reservation) {
        boolean result = false;
        int idTable = -1;
        // Offnunszeiten - 11:00 - 23:00
        String query = "SELECT T.id FROM `tables` T\n" +
                "WHERE NOT EXISTS\n" +
                "\t(SELECT id\n" +
                "     FROM `reservations` R\n" +
                "     WHERE T.id = R.idTable\n" +
                "     AND ? > R.openFrom\n" +
                "     AND ? < R.openTo\n" +
                "     AND ? < R.reservationTo\n" +
                "     AND ? > R.openFrom\n" +
                "     AND ? > R.reservationFrom\n" +
                "    )\n" +
                "AND T.numSeats >= ? AND T.numSeats <= ?*2\n" +
                "ORDER BY T.numSeats ASC " +
                "LIMIT 1";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setTimestamp(1,reservation.getOpenFrom());
            st.setTimestamp(2,reservation.getOpenTo());
            st.setTimestamp(3, reservation.getReservationFrom());
            st.setTimestamp(4, reservation.getReservationTo());
            st.setInt(5, reservation.getNumberOfSeats());
            st.setInt(6, reservation.getNumberOfSeats());
            ResultSet rs = st.executeQuery();

            if(rs.next()) {
                result = true;
                idTable = rs.getInt(1);
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } // kviri

        if(result == false) {
            return false;
        }

        String insertQuery = "INSERT INTO reservations (idCustomer, idTable, numberOfSeats, reservationFrom, reservationTo, openFrom, openTo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = conn.prepareStatement(insertQuery);
            st.setInt(1, reservation.getIdCustomer());
            st.setInt(2, idTable);
            st.setInt(3, reservation.getNumberOfSeats());
            st.setTimestamp(4, reservation.getReservationFrom());
            st.setTimestamp(5, reservation.getReservationTo());
            st.setTimestamp(6,reservation.getOpenFrom());
            st.setTimestamp(7,reservation.getOpenTo());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return true;
    }

    private Reservation getReservationFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idCustomer = rs.getInt("idCustomer");
        int idTable = rs.getInt("idTable");
        int numberOfSeats = rs.getInt("numberOfSeats");
        Timestamp reservationFrom = rs.getTimestamp("reservationFrom");
        Timestamp reservationTo = rs.getTimestamp("reservationTo");
        Timestamp openFrom = rs.getTimestamp("openFrom");
        Timestamp openTo = rs.getTimestamp("openTo");

        return new Reservation(id, idCustomer, idTable, numberOfSeats, reservationFrom, reservationTo, openFrom, openTo);
    }

   /* @Override
    public void makeReservation(int idCustomer, int numberOfSeats, Date reservationFrom, Date reservationTo) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM tables T where NOT EXISTS (SELECT id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
