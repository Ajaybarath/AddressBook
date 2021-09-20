package com.bridgeLabz.addressBook;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.naming.spi.StateFactory;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBook {

//	static Contacts[] contactList = new Contacts[5];
	static Map<String, Contacts> contactList = new HashMap<>();

	public static final String ADDRESS_BOOK_PATH = "addressBook.txt";
	public static final String ADDRESS_BOOK_CSV_FILE = "addressBook.csv";

	static int contactsCount = 0;

	public static void main(String args[]) throws IOException {

		Scanner s = new Scanner(System.in);

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
			System.out.println("Enter 11 to exit");

			input = s.nextInt();

			switch (input) {
			case (1):
				addContact();
				break;
			case (2):
				editContact();
				break;
			case (3):
				deleteContact();
				break;
			case (4):
				getCountByCity();
				break;
			case (5):
				getCountByState();
				break;
			case (6):
				getPeopleInCity();
				break;
			case (7):
				getPeopleInState();
				break;
			case (8):
				sortByPersonName();
				break;
			case (9):
				sortByCityStateZip();
				break;
			case (10):
				readAddressBook();
				break;
			case (11):
				flag = 1;
				break;

			}

			writeContactsToAddressBook();
			writeContactsToAddressBookCSVFile();

		}

	}

	private static void deleteContact() {
		Scanner s = new Scanner(System.in);

		String firstName;

		System.out.println("Enter the detail to delete data from the address book");

		System.out.print("Enter you first name : ");
		firstName = s.next();

		contactList.remove(firstName);

		System.out.println("Deleted successfully");

	}

	public static void addContact() {
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

	public static void editContact() {

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

	public static boolean findContact(String name, Contacts contacts) {

		return contactList.values().stream().anyMatch(contact -> contact.firstName.equalsIgnoreCase(name));

	}

	private static void getPeopleInState() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the state name to get the count : ");
		String state = s.next();

		List<String> stateAndPerson = contactList.entrySet().stream()
				.filter(e -> e.getValue().state.equalsIgnoreCase(state)).map(Map.Entry::getKey)
				.collect(Collectors.toList());

		System.out.println(stateAndPerson.toString());

	}

	private static void getPeopleInCity() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the city name to get the count : ");
		String city = s.next();

		List<String> cityAndPerson = contactList.entrySet().stream()
				.filter(e -> e.getValue().city.equalsIgnoreCase(city)).map(Map.Entry::getKey)
				.collect(Collectors.toList());

		System.out.println(cityAndPerson.toString());

	}

	public static void getCountByState() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the state name to get the count : ");
		String state = s.next();
		long count = contactList.entrySet().stream().filter(e -> e.getValue().state.equalsIgnoreCase(state)).count();

		System.out.println("Threr are " + count + " address from this state");
	}

	public static void getCountByCity() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the city name to get the count : ");
		String city = s.next();

		long count = contactList.entrySet().stream().filter(e -> e.getValue().city.equalsIgnoreCase(city)).count();

		System.out.println("Threr are " + count + " address from this city");
	}

	public static void sortByPersonName() {
		List<Contacts> contacts = contactList.entrySet().stream()
				.sorted((a1, a2) -> a1.getValue().getFirstName().compareTo(a2.getValue().getFirstName()))
				.map(Map.Entry::getValue).collect(Collectors.toList());

		contacts.forEach(contact -> System.out.println(contact.getFirstName()));

	}

	public static void sortByCityStateZip() {

		Comparator<Contacts> cityStateComparator = Comparator.comparing(Contacts::getCity)
				.thenComparing(Contacts::getState);

		List<Contacts> contacts = contactList.entrySet().stream()
				.sorted((a1, a2) -> a1.getValue().getCity().compareTo(a2.getValue().getCity()))
				.sorted((a1, a2) -> a1.getValue().getState().compareTo(a2.getValue().getState()))
				.sorted((a1, a2) -> a1.getValue().getZip().compareTo(a2.getValue().getZip())).map(Map.Entry::getValue)
				.collect(Collectors.toList());

		contacts.forEach(contact -> System.out.println(contact.getFirstName()));
	}

	public static void writeContactsToAddressBook() {
		StringBuffer stringBuffer = new StringBuffer();
		contactList.values().forEach(contact -> {
			String contactData = contact.toString().concat("\n");
			stringBuffer.append(contactData);
		});

		try {
			Files.write(Paths.get(ADDRESS_BOOK_PATH), stringBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readAddressBook() {
		try {
			Files.lines(new File(ADDRESS_BOOK_PATH).toPath()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeContactsToAddressBookCSVFile() throws IOException {

		Writer writer = null;
		try {
			writer = Files.newBufferedWriter(Paths.get(ADDRESS_BOOK_CSV_FILE));

			StatefulBeanToCsv<Contacts> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			List<Contacts> contacts = contactList.entrySet().stream().map(Map.Entry::getValue)
					.collect(Collectors.toList());

			beanToCsv.write(contacts);
			System.out.println(contacts.toString());
		} catch (CsvDataTypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

}
