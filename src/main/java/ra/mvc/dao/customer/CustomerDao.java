package ra.mvc.dao.customer;

import ra.mvc.model.Customer;
import ra.mvc.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDao implements ICustomerDao {
    @Override
    public List findAll() {
        List<Customer> list = new ArrayList<>();
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("select *from customer");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"));
                list.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Customer findById(Integer id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("select  * from customer where id=?");
            callableStatement.setInt(1, id);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"));
                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return null;
    }

    @Override
    public void create(Customer customer) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("INSERT into customer(name,address,email)values (?,?,?)");
            callableStatement.setString(1, customer.getName());
            callableStatement.setString(2, customer.getAddress());
            callableStatement.setString(3, customer.getEmail());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }

    }

    @Override
    public void update(Customer customer) {

        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("update customer set name=?,address=?,email=? where id=?");

            callableStatement.setString(1, customer.getName());
            callableStatement.setString(2, customer.getAddress());
            callableStatement.setString(3, customer.getEmail());
            callableStatement.setInt(4, customer.getId());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }

    }


    @Override
    public void delete(Integer id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("delete from customer where id=?");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }


}
