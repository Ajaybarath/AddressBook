package test.com.bridgeLabz.addressBook;

import main.com.bridgeLabz.addressBook.AddressBook;
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
}
