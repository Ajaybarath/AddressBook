package com.bridgeLabz.addressBook;

import java.util.Scanner;

public class AddressBook {

	public static void main(String args[]) {
		
		String firstName;
		String lastName;
		String city;
		String state;
		String address;
		String zip;
		String phoneNumber;
		
		Scanner s = new Scanner(System.in);
		
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
	}
}
