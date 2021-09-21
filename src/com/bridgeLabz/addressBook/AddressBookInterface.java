package com.bridgeLabz.addressBook;

import java.io.IOException;

public interface AddressBookInterface {

	public void deleteContact();

	public void addContact();

	public void editContact();

	public boolean findContact(String name, Contacts contacts);

	public void getPeopleInState();

	public void getPeopleInCity();

	public void getCountByState();

	public void getCountByCity();

	public void sortByPersonName();

	public void sortByCityStateZip();

	public void writeContactsToAddressBook()  throws AddressBookException ;

	public void readAddressBook() throws AddressBookException;

	public void writeContactsToAddressBookCSVFile() throws AddressBookException;

	public void readAddressBookCSVFile() throws AddressBookException;

	public void writeToJsonFile() throws AddressBookException;

	public void readJsonFile() throws AddressBookException;

}
