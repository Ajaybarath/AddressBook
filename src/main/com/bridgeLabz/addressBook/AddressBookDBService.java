package main.com.bridgeLabz.addressBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbook_service?useSSL=false";
        String userName = "root";
        String password = "ajaybarath1999";
        Connection connection;

        System.out.println("Connecting to database : " + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successfull : " + connection);
        return connection;

    }

    public List<Contacts> readAddressBook() throws AddressBookException {


        try {
            String sql = "Select * from address_book";
            List<Contacts> contactsList = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactsList = this.getAddressData(resultSet);

            return contactsList;
        } catch (SQLException throwables) {
            throw new AddressBookException(throwables.getMessage());
        }


    }

    public List<Contacts> peopleInCity(String city) throws AddressBookException {
        try {
            String sql = "select * from address_book where city = " + city;
            List<Contacts> contactsList = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactsList = this.getAddressData(resultSet);

            return contactsList;

        } catch (AddressBookException e) {
            throw new AddressBookException(e.getMessage());
        } catch (SQLException throwables) {
            throw new AddressBookException(throwables.getMessage());
        }
    }


    private List<Contacts> getAddressData(ResultSet resultSet) throws AddressBookException {
        List<Contacts> contactsList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                contactsList.add(new Contacts(id, firstName, lastName, address, city, state, zip, email));
            }
        } catch (SQLException throwables) {
            throw new AddressBookException(throwables.getMessage());
        }
        return contactsList;
    }

    public List<Contacts> peopleInState(String state) throws AddressBookException {
        try {
            String sql = "select * from address_book where state = " + state;
            List<Contacts> contactsList = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactsList = this.getAddressData(resultSet);

            return contactsList;

        } catch (AddressBookException e) {
            throw new AddressBookException(e.getMessage());
        } catch (SQLException throwables) {
            throw new AddressBookException(throwables.getMessage());
        }
    }


    public List<Contacts> orderByName() throws AddressBookException {
        try {
            String sql = "select * from address_book where order by name ";
            List<Contacts> contactsList = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactsList = this.getAddressData(resultSet);

            return contactsList;

        } catch (AddressBookException e) {
            throw new AddressBookException(e.getMessage());
        } catch (SQLException throwables) {
            throw new AddressBookException(throwables.getMessage());
        }
    }

    public List<Contacts> orderByCityStateZip() throws AddressBookException {

        try {
            String sql = "select * from address_book order by city, state, zip";
            List<Contacts> contactsList = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactsList = this.getAddressData(resultSet);

            return contactsList;

        } catch (AddressBookException e) {
            throw new AddressBookException(e.getMessage());
        } catch (SQLException throwables) {
            throw new AddressBookException(throwables.getMessage());
        }
    }
}
