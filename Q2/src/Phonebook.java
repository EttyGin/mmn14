/*
 * A class that implements an object that represents a phonebook
 * Last update: 6.12.23
 * Autor: Etty Ginzburg
 */
import java.util.TreeMap;

public class Phonebook{
	
	private TreeMap<String, String> phonebook = new TreeMap<String, String>(); //This object is sorted by definition. key: name, value - number
	
	public Phonebook() { //Phonebook initialization is empty
		phonebook = new TreeMap<String, String>();
	}
	
	public Phonebook(TreeMap<String, String> map) { //A constructor with a parameter
		phonebook = map;
	}
	
	public void add (String name, String phone) throws InvalidException { //Adding a new contact

		if (!vaildPhone(phone)) //Throwing an error if the phone number is invalid
			throw new InvalidException("Invalid phone number");
		
		phonebook.put(name, phone);
	}

	public void delete (String name, String phone) throws InvalidException { //Deleting a contact
		
		if (!phonebook.containsKey(name))  //Throwing an error if there is no such contact
			throw new InvalidException("There is no contact by this name");
		
		phonebook.remove(name);
	}
	
	public void update (String name, String phone) throws InvalidException { //Update phone number by contact name
		
		if (!phonebook.containsKey(name))  //Throwing an error if there is no such contact
			throw new InvalidException("There is no contact by this name");
		
		if (!vaildPhone(phone))
			throw new InvalidException("Invalid phone number");
		
		phonebook.put(name, phone);
	}
	
	public boolean search (String name) { //A boolean method that only checks if the contact is found.
		  
		return phonebook.containsKey(name);

	}
	
	private boolean vaildPhone (String num) { //A method that verifies that the string does represent a valid phone number
		return num.length() == 10 && num.matches("^[0-9]*$");
	}

	public TreeMap<String, String> getPhonebook() {
		return phonebook;
	}

	public void setPhonebook(TreeMap<String, String> phonebook) {
		this.phonebook = phonebook;
	}
}

