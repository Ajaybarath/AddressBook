package main.com.bridgeLabz.addressBook;

import com.opencsv.bean.CsvBindByName;

public class Contacts {

	@CsvBindByName
	String firstName;
	
	@CsvBindByName
	String lastName;
	
	@CsvBindByName
	String city;
	
	@CsvBindByName
	String state;
	
	@CsvBindByName
	String address;
	
	@CsvBindByName
	String zip;
	
	@CsvBindByName
	String phoneNumber;

	int id;

	public Contacts(String firstName, String lastName, String city, String state, String address, String zip, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.address = address;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contacts(int id, String firstName, String lastName, String city, String state, String address, String zip,
					String phoneNumber) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.address = address;
		this.zip = zip;
		this.phoneNumber = phoneNumber;

	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getAddress() {
		return address;
	}

	public String getZip() {
		return zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Contacts [firstName=" + firstName + ", lastName=" + lastName + ", city=" + city + ", state=" + state
				+ ", address=" + address + ", zip=" + zip + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
	

}
