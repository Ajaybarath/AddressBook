package com.bridgeLabz.addressBook;

import java.util.Scanner;

public class AddressBook {
	
	static Contacts[] contactList = new Contacts[5];
	static int contactsCount = 0;

	public static void main(String args[]) {

		Scanner s = new Scanner(System.in);

		int input;
		int flag = 0;

		while (flag == 0) {
			
			System.out.println("Enter 1 to add");
			System.out.println("Enter 2 to edit");
			System.out.println("Enter 3 to exit");

			input = s.nextInt();

			switch (input) {
			case (1):
				addContact();
				break;
			case (2):
				editContact();
				break;
			case (3):
				flag = 1;
				break;

			}

		}

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
		
		contactList[contactsCount++] = contacts;
		

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
		
		findContact(firstName, contacts);
		
	}
	
	public static void findContact(String name, Contacts contacts) {
		
		for (int i=0;i<contactsCount;i++) {
			
			if (contactList[i].firstName.equals(name)) {
				
				contactList[i].firstName = contacts.firstName;
				contactList[i].lastName = contacts.lastName;
				contactList[i].address = contacts.address;
				contactList[i].city = contacts.city;
				contactList[i].state = contacts.state;
				contactList[i].zip = contacts.zip;
				contactList[i].phoneNumber = contacts.phoneNumber;
				
				return;
			}
			
		}
		
		System.out.println("Name not found try again");
		
	}
	
}
