package test.com.bridgeLabz.addressBook;

import main.com.bridgeLabz.addressBook.AddressBook;
import main.com.bridgeLabz.addressBook.AddressBookDBService;
import main.com.bridgeLabz.addressBook.AddressBookException;
import main.com.bridgeLabz.addressBook.Contacts;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class AddressBookDBIOTest {

    @Test
    public void readDatabaseAndGetEntriesCount() throws SQLException, AddressBookException {

        AddressBook employeePayRollService = new AddressBook();
        List<Contacts> list = employeePayRollService.readAddressBookFromDB();
        long entries = list.size();
        Assert.assertEquals(6, entries);
    }

    @Test
    public void getPeopleByCity() throws SQLException, AddressBookException {

        AddressBookDBService addressBookDBService = new AddressBookDBService();
        List<Contacts> list = addressBookDBService.peopleInCity("karur");
        long entries = list.size();
        Assert.assertEquals(2, entries);
    }

    @Test
    public void getPeopleByState() throws SQLException, AddressBookException {

        AddressBookDBService addressBookDBService = new AddressBookDBService();
        List<Contacts> list = addressBookDBService.peopleInState("karur");
        long entries = list.size();
        Assert.assertEquals(2, entries);
    }

    @Test
    public void orderPeopleByName() throws AddressBookException {

        AddressBookDBService addressBookDBService = new AddressBookDBService();
        List<Contacts> list = addressBookDBService.orderByName();
        long entries = list.size();
        Assert.assertEquals(2, entries);
    }

    @Test
    public void orderCityStateAndZip() throws AddressBookException {

        AddressBookDBService addressBookDBService = new AddressBookDBService();
        List<Contacts> list = addressBookDBService.orderByCityStateZip();
        long entries = list.size();
        Assert.assertEquals(2, entries);
    }

}
