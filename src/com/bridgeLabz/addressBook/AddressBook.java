package com.bridgeLabz.addressBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.naming.spi.StateFactory;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBook implements AddressBookInterface {

//	static Contacts[] contactList = new Contacts[5];
	static Map<String, Contacts> contactList = new HashMap<>();

	public static final String ADDRESS_BOOK_PATH = "addressBook.txt";
	public static final String ADDRESS_BOOK_CSV_FILE = "addressBook.csv";
	public static final String ADDRESS_BOOK_JSON_FILE = "addressBook.json";

	static int contactsCount = 0;

	public static void main(String args[]) throws IOException, AddressBookException {

		Scanner s = new Scanner(System.in);

		AddressBook addressbook = new AddressBook();

		int input;
		int flag = 0;

		while (flag == 0) {

			System.out.println("Enter 1 to add");
			System.out.println("Enter 2 to edit");
			System.out.println("Enter 3 to delete");
			System.out.println("Enter 4 to get number of person in a city");
			System.out.println("Enter 5 to get number of person in a state");
			System.out.println("Enter 6 to get people in a city");
			System.out.println("Enter 7 to get people in a state");
			System.out.println("Enter 8 to sort Address book by name");
			System.out.println("Enter 9 to sort Address book by name");
			System.out.println("Enter 10 to read Address book");
			System.out.println("Enter 11 to read Address book csv file");
			System.out.println("Enter 12 to read Address book json file");
			System.out.println("Enter 13 to exit");

			input = s.nextInt();

			switch (input) {
			case (1):
				addressbook.addContact();
				break;
			case (2):
				addressbook.editContact();
				break;
			case (3):
				addressbook.deleteContact();
				break;
			case (4):
				addressbook.getCountByCity();
				break;
			case (5):
				addressbook.getCountByState();
				break;
			case (6):
				addressbook.getPeopleInCity();
				break;
			case (7):
				addressbook.getPeopleInState();
				break;
			case (8):
				addressbook.sortByPersonName();
				break;
			case (9):
				addressbook.sortByCityStateZip();
				break;
			case (10):
				addressbook.readAddressBook();
				break;
			case (11):
				addressbook.readAddressBookCSVFile();
				break;
			case (12):
				addressbook.readJsonFile();
				break;
			case (13):
				flag = 1;
				break;

			}

			addressbook.writeContactsToAddressBook();
			addressbook.writeContactsToAddressBookCSVFile();
			addressbook.writeToJsonFile();

		}

	}

	public void deleteContact() {
		Scanner s = new Scanner(System.in);

		String firstName;

		System.out.println("Enter the detail to delete data from the address book");

		System.out.print("Enter you first name : ");
		firstName = s.next();

		contactList.remove(firstName);

		System.out.println("Deleted successfully");

	}

	public void addContact() {
		Scanner s = new Scanner(System.in);

		String firstName;
		String lastName;
		String city;
		String state;
		String address;
		String zip;
		String phoneNumber;

		System.out.println("Enter the detail to store in the address book");

		System.out.print("Enter you first name : ");
		firstName = s.next();

		System.out.print("Enter you last name : ");
		lastName = s.next();

		System.out.print("Enter you address : ");
		address = s.next();

		System.out.print("Enter you city : ");
		city = s.next();

		System.out.print("Enter you state : ");
		state = s.next();

		System.out.print("Enter you zip : ");
		zip = s.next();

		System.out.print("Enter you phoneNumber : ");
		phoneNumber = s.next();

		Contacts contacts = new Contacts(firstName, lastName, city, state, address, zip, phoneNumber);

		if (!findContact(firstName, contacts)) {
			contactList.put(firstName, contacts);
			System.out.println("Added successfully");
		} else {
			System.out.println("Name already exists. Please try Again");
		}

	}

	public void editContact() {

		Scanner s = new Scanner(System.in);

		System.out.println("Enter the details to edit");

		String firstName;
		String lastName;
		String city;
		String state;
		String address;
		String zip;
		String phoneNumber;

		System.out.println("Enter the detail to store in the address book");

		System.out.print("Enter you first name : ");
		firstName = s.next();

		System.out.print("Enter you last name : ");
		lastName = s.next();

		System.out.print("Enter you address : ");
		address = s.next();

		System.out.print("Enter you city : ");
		city = s.next();

		System.out.print("Enter you state : ");
		state = s.next();

		System.out.print("Enter you zip : ");
		zip = s.next();

		System.out.print("Enter you phoneNumber : ");
		phoneNumber = s.next();

		Contacts contacts = new Contacts(firstName, lastName, city, state, address, zip, phoneNumber);

		Contacts contact = contactList.get(firstName);

		if (contact != null) {
			contact.firstName = contacts.firstName;
			contact.lastName = contacts.lastName;
			contact.address = contacts.address;
			contact.city = contacts.city;
			contact.state = contacts.state;
			contact.zip = contacts.zip;
			contact.phoneNumber = contacts.phoneNumber;
			System.out.println("Edited successfully");
		} else {
			System.out.println("Name not found");
		}

		return;
	}

	public boolean findContact(String name, Contacts contacts) {

		return contactList.values().stream().anyMatch(contact -> contact.firstName.equalsIgnoreCase(name));

	}

	public void getPeopleInState() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the state name to get the count : ");
		String state = s.next();

		List<String> stateAndPerson = contactList.entrySet().stream()
				.filter(e -> e.getValue().state.equalsIgnoreCase(state)).map(Map.Entry::getKey)
				.collect(Collectors.toList());

		System.out.println(stateAndPerson.toString());

	}

	public void getPeopleInCity() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the city name to get the count : ");
		String city = s.next();

		List<String> cityAndPerson = contactList.entrySet().stream()
				.filter(e -> e.getValue().city.equalsIgnoreCase(city)).map(Map.Entry::getKey)
				.collect(Collectors.toList());

		System.out.println(cityAndPerson.toString());

	}

	public void getCountByState() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the state name to get the count : ");
		String state = s.next();
		long count = contactList.entrySet().stream().filter(e -> e.getValue().state.equalsIgnoreCase(state)).count();

		System.out.println("Threr are " + count + " address from this state");
	}

	public void getCountByCity() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the city name to get the count : ");
		String city = s.next();

		long count = contactList.entrySet().stream().filter(e -> e.getValue().city.equalsIgnoreCase(city)).count();

		System.out.println("Threr are " + count + " address from this city");
	}

	public void sortByPersonName() {
		List<Contacts> contacts = contactList.entrySet().stream()
				.sorted((a1, a2) -> a1.getValue().getFirstName().compareTo(a2.getValue().getFirstName()))
				.map(Map.Entry::getValue).collect(Collectors.toList());

		contacts.forEach(contact -> System.out.println(contact.getFirstName()));

	}

	public void sortByCityStateZip() {

		Comparator<Contacts> cityStateComparator = Comparator.comparing(Contacts::getCity)
				.thenComparing(Contacts::getState);

		List<Contacts> contacts = contactList.entrySet().stream()
				.sorted((a1, a2) -> a1.getValue().getCity().compareTo(a2.getValue().getCity()))
				.sorted((a1, a2) -> a1.getValue().getState().compareTo(a2.getValue().getState()))
				.sorted((a1, a2) -> a1.getValue().getZip().compareTo(a2.getValue().getZip())).map(Map.Entry::getValue)
				.collect(Collectors.toList());

		contacts.forEach(contact -> System.out.println(contact.getFirstName()));
	}

	public void writeContactsToAddressBook() throws AddressBookException {
		StringBuffer stringBuffer = new StringBuffer();
		contactList.values().forEach(contact -> {
			String contactData = contact.toString().concat("\n");
			stringBuffer.append(contactData);
		});

		try {
			Files.write(Paths.get(ADDRESS_BOOK_PATH), stringBuffer.toString().getBytes());
		} catch (IOException e) {
			throw new AddressBookException(e.getMessage());
		}
	}

	public void readAddressBook() throws AddressBookException {
		try {
			Files.lines(new File(ADDRESS_BOOK_PATH).toPath()).forEach(System.out::println);
		} catch (IOException e) {
			throw new AddressBookException(e.getMessage());
		}
	}

	public void writeContactsToAddressBookCSVFile() throws AddressBookException {

		Writer writer = null;
		try {
			try {
				writer = Files.newBufferedWriter(Paths.get(ADDRESS_BOOK_CSV_FILE));
			} catch (IOException e) {
				throw new AddressBookException(e.getMessage());
			}

			StatefulBeanToCsv<Contacts> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			List<Contacts> contacts = contactList.entrySet().stream().map(Map.Entry::getValue)
					.collect(Collectors.toList());

			beanToCsv.write(contacts);
		} catch (CsvDataTypeMismatchException e) {
			throw new AddressBookException(e.getMessage());
		} catch (CsvRequiredFieldEmptyException e) {
			throw new AddressBookException(e.getMessage());
		}
	}

	public void readAddressBookCSVFile() throws AddressBookException {
//		Reader reader = new BufferedReader(new FileReader(ADDRESS_BOOK_CSV_FILE));
//		
//		CsvToBean<Contacts> csvTobean = new CsvToBeanBuilder(reader)
//				.withType(Contacts.class)
//				.withIgnoreLeadingWhiteSpace(true)
//				.build();
//		
//		System.out.println(csvTobean);
//		
//		List<Contacts> list = csvTobean.parse();
//		for(Contacts contact: list) {
//			System.out.println(contact);
//		}

		try (CSVReader reader = new CSVReader(new FileReader(ADDRESS_BOOK_CSV_FILE))) {
			List<String[]> read = reader.readAll();
			read.forEach(x -> {
				System.out.println(Arrays.toString(x));

			});
		} catch (FileNotFoundException e) {
			throw new AddressBookException(e.getMessage());

		} catch (IOException e) {
			throw new AddressBookException(e.getMessage());

		}

	}

	public void writeToJsonFile() throws AddressBookException {
		List<Contacts> list = contactList.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

		Gson gson = new Gson();
		String json = gson.toJson(list);

		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(ADDRESS_BOOK_JSON_FILE);
			fileWriter.write(json);

			fileWriter.close();
		} catch (IOException e) {
			throw new AddressBookException(e.getMessage());

		}

	}

	public void readJsonFile() throws AddressBookException {

		Gson gson = new Gson();

		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(ADDRESS_BOOK_JSON_FILE));
		} catch (FileNotFoundException e) {
			throw new AddressBookException(e.getMessage());

		}
		Contacts contacts[] = gson.fromJson(bufferedReader, Contacts[].class);

		List<Contacts> list = Arrays.asList(contacts);
		list.forEach(x -> {
			System.out.println(x.toString());

		});

	}

}
