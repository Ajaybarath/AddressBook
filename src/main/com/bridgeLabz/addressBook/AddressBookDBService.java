package main.com.bridgeLabz.addressBook;

import java.sql.*;
import java.time.LocalDate;
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
            String sql = "Select * from address_book;";
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

    public Contacts addContactToAddressBook(String firstName, String lastName, String phone, String email, String address, String city, String state, String zip) {

        int contact_id = -1;
        Connection connection = null;
        Contacts contacts = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {
            String sql = String.format("insert into address_book (first_name, last_name, phone_number, email, date) value('%s', %s, '%s', '%s');", firstName, lastName, phone, email, LocalDate.now());
            int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) contact_id = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {

            String sql = String.format("insert into address (address, city, state, zip) value ('%s', %s, '%s', '%s');", address, city, state, zip);
            int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                contacts = new Contacts(contact_id, firstName, lastName, city, state, address, zip, phone);
            }
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

        try {
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return contacts;

    }

    public List<Contacts> getPeopleByDateAdded(LocalDate startDate, LocalDate endDate) throws AddressBookException {

        try {
            String sql = "select * from address_book where date between '" + startDate + "' and '" + endDate + "'";
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
