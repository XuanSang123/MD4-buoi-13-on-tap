package ra.mvc.utils;


import ra.mvc.model.Customer;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
//        String sql = "INSERT  INTO customer(name,address,email) values ('hung','HP','a@gmail.com')";
        String sql = "SELECT * FROM customer";
        Connection connection = null;
        connection = ConnectionDB.getConnection();
//
//        try {
////            Statement statement = connection.createStatement();
////            statement.execute(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            ConnectionDB.closeConnection(connection);
//        }
//        System.out.println("ket thuc");
//
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                System.out.println(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }


    }
}
