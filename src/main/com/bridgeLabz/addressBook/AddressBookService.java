package main.com.bridgeLabz.addressBook;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class AddressBookService {


    public List<Contacts> readAddressBook() throws AddressBookException {
        return new AddressBookDBService().readAddressBook();
    }

    public List<Contacts> peopleInCity(String city) throws AddressBookException {
        return new AddressBookDBService().peopleInCity(city);
    }

    public List<Contacts> peopleInState(String state) throws AddressBookException {
        return new AddressBookDBService().peopleInState(state);
    }

    public List<Contacts> orderByName() throws AddressBookException {
        return new AddressBookDBService().orderByName();
    }

    public List<Contacts> orderByCityStateZip() throws AddressBookException {
        return new AddressBookDBService().orderByCityStateZip();
    }

    public Contacts addContactToAddressBook(String firstName, String lastName, String phone, String email, String address, String city, String state, String zip) throws AddressBookException {
        return new AddressBookDBService().addContactToAddressBook(firstName, lastName, phone, email, address, city, state, zip);
    }


    public List<Contacts> getPeopleByDateAdded(LocalDate startDate, LocalDate endDate) throws AddressBookException {
        return new AddressBookDBService().getPeopleByDateAdded(startDate, endDate);

    }

    public int updateContactToAddressBook(String firstName, String lastName, String phone, String email, String address, String city, String state, String zip) {
        return new AddressBookDBService().updateContactToAddressBook(firstName, lastName, phone, email, address, city, state, zip);

    }

    public Relationship addRelationShip(String relationship) {
        return  new AddressBookDBService().addRelationShip(relationship);
    }
}
