package test.com.bridgeLabz.addressBook;

import main.com.bridgeLabz.addressBook.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AddressBookDBIOTest {

    @Test
    public void readDatabaseAndGetEntriesCount() throws SQLException, AddressBookException {

        AddressBookService addressBookService = new AddressBookService();
        List<Contacts> list = addressBookService.readAddressBook();
        long entries = list.size();
        Assert.assertEquals(6, entries);
    }

    @Test
    public void getPeopleByCity() throws SQLException, AddressBookException {

        AddressBookService addressBookService = new AddressBookService();

        List<Contacts> list = addressBookService.peopleInCity("karur");
        long entries = list.size();
        Assert.assertEquals(2, entries);
    }

    @Test
    public void getPeopleByState() throws SQLException, AddressBookException {

        AddressBookService addressBookService = new AddressBookService();

        List<Contacts> list = addressBookService.peopleInState("karur");
        long entries = list.size();
        Assert.assertEquals(2, entries);
    }

    @Test
    public void orderPeopleByName() throws AddressBookException {

        AddressBookService addressBookService = new AddressBookService();

        List<Contacts> list = addressBookService.orderByName();
        long entries = list.size();
        Assert.assertEquals(2, entries);
    }

    @Test
    public void orderCityStateAndZip() throws AddressBookException {

        AddressBookService addressBookService = new AddressBookService();

        List<Contacts> list = addressBookService.orderByCityStateZip();
        long entries = list.size();
        Assert.assertEquals(2, entries);
    }

    @Test
    public void addContactIntoAddressBook() throws AddressBookException {

        AddressBookService addressBookService = new AddressBookService();

        Contacts contacts = addressBookService.addContactToAddressBook("Ajay", "Barath", "9087654321", "asd@as.asd", "velayuthampalayam","karur" , "tamil nadu", "123456");

        Assert.assertEquals("Ajay", contacts.getFirstName());
    }

    @Test
    public void updateContactIntoAddressBook() throws AddressBookException {

        AddressBookService addressBookService = new AddressBookService();

        int result = addressBookService.updateContactToAddressBook("Ajay", "Barath", "9087654321", "asd@as.asd", "velayuthampalayam","karur" , "tamil nadu", "123456");

        Assert.assertEquals(1, result);
    }

    @Test
    public void getPeopleByDateAdded() throws SQLException, AddressBookException {

        AddressBookService addressBookService = new AddressBookService();

        List<Contacts> list = addressBookService.getPeopleByDateAdded(LocalDate.parse("2021-01-01"), LocalDate.parse("2021-01-04"));
        long entries = list.size();
        Assert.assertEquals(2, entries);
    }

    @Test
    public void addRelationship() throws AddressBookException {

        AddressBookService addressBookService = new AddressBookService();

        Relationship relationship = addressBookService.addRelationShip("Family");

        Assert.assertEquals("Family", relationship.getName());
    }

    @Test
    public void addRelationshipIdToContacts() throws AddressBookException {

        AddressBookService addressBookService = new AddressBookService();

        Relationship relationship = addressBookService.addRelationShip("Family");

        Assert.assertEquals("Family", relationship.getName());
    }
}
