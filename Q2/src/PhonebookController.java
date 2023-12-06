/*
 * A class that links the application to the phonebook display
 * Last update: 6.12.23
 * Autor: Etty Ginzburg
 */

import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PhonebookController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button deletePressed;
    @FXML
    private TextField searchField;    
    @FXML 
    private TableView<Map.Entry<String, String>> table;
    @FXML 
    private TableColumn<Map.Entry<String, String>, String> nums;
    @FXML
    private TableColumn<Map.Entry<String, String>, String>  names;
    
    private Phonebook fullPhoneBook = new Phonebook();

    private Phonebook tempPhonebook = new Phonebook(); //Temporary phonebook - for the purpose of the search operation

    private Alert error;
    
    @FXML
    private void initialize() { //Initialization and preparation of the table for work.
    	
        table.setPlaceholder(new Label("There are no contacts to view"));
        names.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        nums.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));
    }
    
    @FXML
    void addPressed(ActionEvent event) { //Adding another contact
    	try {
    		fullPhoneBook.add(nameField.getText(), phoneField.getText());
    		
    		//Initialize the value fields for the next round
        	nameField.setText("");
        	phoneField.setText("");
        	
		} catch (InvalidException e) {
			error = new Alert(AlertType.ERROR, e.getMessage());
			error.showAndWait();
		}
 	
    	viewPhoneBook(fullPhoneBook);

    }

    @FXML
    void deletePressed(ActionEvent event) {//Deleting a contact
    	try {
    		fullPhoneBook.delete(nameField.getText(), phoneField.getText());
    		
    		//Initialize the value fields for the next round
        	nameField.setText("");
        	phoneField.setText("");
        	
		} catch (InvalidException e) {
			error = new Alert(AlertType.ERROR, e.getMessage());
			error.showAndWait();
		}    
    	
    	viewPhoneBook(fullPhoneBook);

    }

    @FXML
    void updatePressed(ActionEvent event) { //Update phone number by contact name
    	try {
    		fullPhoneBook.update(nameField.getText(), phoneField.getText());
    		
    		//Initialize the value fields for the next round
        	nameField.setText("");
        	phoneField.setText("");
        	
		} catch (InvalidException e) {
			error = new Alert(AlertType.ERROR, e.getMessage());
			error.showAndWait();
		}    
    	
    	viewPhoneBook(fullPhoneBook);

    }
    
 //   void searchPressed(ActionEvent event) {
    //	tempPhonebook.setPhonebook(phonebook.search(searchField.getText()));
  //  }
    
    @FXML
    void searchPressed(ActionEvent event) {//Searching a phone number name a specific name
 
    	if (fullPhoneBook.search(searchField.getText()))
			try {
				tempPhonebook.add(searchField.getText(), fullPhoneBook.getPhonebook().get(searchField.getText()));
			} catch (InvalidException e) {
				error = new Alert(AlertType.ERROR, e.getMessage());
				error.showAndWait();
			}
    	viewPhoneBook(tempPhonebook); //Displays the search results as a separate phonebook

    }
    
    @FXML
    void clearPressed(ActionEvent event) { //returns to display the full phone book
   
    	tempPhonebook.getPhonebook().clear();
    	viewPhoneBook(fullPhoneBook);
    	searchField.setText("");

    }

    private void viewPhoneBook(Phonebook phoneBook) { //Displays in a table the phone book that it receives as a parameter
      
    	table.getItems().clear();
        table.getItems().addAll(phoneBook.getPhonebook().entrySet());

    }

}
