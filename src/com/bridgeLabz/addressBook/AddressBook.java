package com.bridgeLabz.addressBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class AddressBook {

//	static Contacts[] contactList = new Contacts[5];
	static Map<String, Contacts> contactList = new HashMap<>();

	static int contactsCount = 0;

	public static void main(String args[]) {

		Scanner s = new Scanner(System.in);

		int input;
		int flag = 0;

		while (flag == 0) {

			System.out.println("Enter 1 to add");
			System.out.println("Enter 2 to edit");
			System.out.println("Enter 3 to delete");
			System.out.println("Enter 4 to exit");

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
				flag = 1;
				break;

			}
			searchPersonInCityOrState("asd", "asd");

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

		return contactList.containsKey(name);

	}

	public static void searchPersonInCityOrState(String state, String city) {
		List<String> cityAndState = contactList.entrySet().stream().filter(e -> e.getValue().city.startsWith(city))
				.map(Map.Entry::getKey).collect(Collectors.toList());
		
		cityAndState = contactList.entrySet().stream().filter(e -> e.getValue().city.startsWith(state))
				.map(Map.Entry::getKey).collect(Collectors.toList());
		
		System.out.println(cityAndState.toString());

	}

}
