package supermarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Employee{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void showEmployee() {

		Stage window = new Stage();
		window.setTitle("Employee");
		
		//GridPane with 10px padding around edge
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Employee Label
		Label employeeLabel = new Label("Employee Details");
		GridPane.setConstraints(employeeLabel, 1, 0);
		
		//Name Label1 - constraints use(child,column,row)
		
		//Employee Id Label
		Label empIdLabel = new Label ("Employee Id:");
		GridPane.setConstraints(empIdLabel, 0, 1);
		
		//Employee Id Input
		TextField empIdInput = new TextField();
		empIdInput.setPromptText("employee id");
		GridPane.setConstraints(empIdInput, 1, 1);
		
		//Employee First Name label
		Label fnameLabel = new Label("First Name:");
		GridPane.setConstraints(fnameLabel, 0, 2);
		
		//Employee First Name Input
		TextField fnameInput = new TextField();
		fnameInput.setPromptText("First Name");
		GridPane.setConstraints(fnameInput, 1, 2);
		
		//Employee Last Name label
		Label lnameLabel = new Label("Last Name:");
		GridPane.setConstraints(lnameLabel, 0, 3);
		
		//Employee Last Name Input
		TextField lnameInput = new TextField();
		lnameInput.setPromptText("last name");
		GridPane.setConstraints(lnameInput, 1, 3);
		
		//Employee Salary label
		Label empSalaryLabel = new Label("Salary:");
		GridPane.setConstraints(empSalaryLabel, 0, 4);
		
		//Employee Salary Input
        TextField empSalaryInput = new TextField();
		empSalaryInput.setPromptText("salary");
		GridPane.setConstraints(empSalaryInput, 1, 4);
		
		//Employee Phone Number Label
		Label empPhoneNumberLabel = new Label("Phone Number:");
		GridPane.setConstraints(empPhoneNumberLabel, 0, 5);
		
		//Employee Phone Number Input
		TextField empPhoneNumberInput = new TextField();
		empPhoneNumberInput.setPromptText("phone number");
		GridPane.setConstraints(empPhoneNumberInput, 1, 5);
		
		//Employee Email Address label
		Label empEmailAddressLabel = new Label("Email Address:");
		GridPane.setConstraints(empEmailAddressLabel, 0, 6);
		
		//Employee Email Address Input
		TextField empEmailAddessInput = new TextField();
		empEmailAddessInput.setPromptText("email address");
		GridPane.setConstraints(empEmailAddessInput, 1, 6);
		
		//Employee Address Input
		Label empAddressLabel = new Label("Address:");
		GridPane.setConstraints(empAddressLabel, 0, 7);
		
		//Employee Address Input
		TextField empAddressInput = new TextField();
		empAddressInput.setPromptText("address");
		GridPane.setConstraints(empAddressInput, 1, 7);
		
		//Employee Join Date Label
		Label empJoinDateLabel = new Label("Join Date:");
		GridPane.setConstraints(empJoinDateLabel, 0, 8);
		
		//Employee Join Date Input
		DatePicker empJoinDateInput = new DatePicker();
		empJoinDateInput.setPromptText("join date");
		GridPane.setConstraints(empJoinDateInput, 1, 8);
		
		//Employee Date Of Birth label
		Label dobLabel = new Label("Date Of Bith:");
		GridPane.setConstraints(dobLabel,0,9);
		
		//Employee Date Of Birth Input
		DatePicker dobInput = new DatePicker();
		dobInput.setPromptText("date of birth");
		GridPane.setConstraints(dobInput, 1, 9);
		
		//Employee End Date label
		Label empEndDateLabel = new Label("End Date:");
		GridPane.setConstraints(empEndDateLabel, 0, 10);
		
		//Employee End Date Input
		DatePicker empEndDateInput = new DatePicker();
		empEndDateInput.setPromptText("end date");
		GridPane.setConstraints(empEndDateInput, 1, 10);
	
		//New Button
		Button newButton = new Button("New");
		GridPane.setConstraints(newButton, 1, 11);
		
		newButton.setOnAction(e ->{
			empIdInput.clear();
			fnameInput.clear();
			lnameInput.clear();
			empSalaryInput.clear();
			empPhoneNumberInput.clear();
			empEmailAddessInput.clear();
			empAddressInput.clear();
			empJoinDateInput.getEditor().clear();
			dobInput.getEditor().clear();
			empEndDateInput.getEditor().clear();
		});
				
	    
	    //Save Button
	    Button saveButton = new Button("Save");
	    GridPane.setConstraints(saveButton, 2, 12);
	    saveButton.setOnAction(e -> {
	    	String eid = empIdInput.getText();
	    	String fname = fnameInput.getText();
			String lname = lnameInput.getText();
			String salary = empSalaryInput.getText();
			String pnumber = empPhoneNumberInput.getText();
			String eaddress = empEmailAddessInput.getText();
			String address = empAddressInput.getText();
			String joindate = empJoinDateInput.getEditor().getText();
			String dateofbirth = dobInput.getEditor().getText();
			String enddate = empEndDateInput.getEditor().getText();
			
			if (eid.equals("")) {
				JOptionPane.showMessageDialog(null, "Add The Employee Id");
			}
			else if(fname.equals("")) {
				JOptionPane.showMessageDialog(null, "Add First Name");
			}
			else if(lname.equals("")){
				JOptionPane.showMessageDialog(null, "Add A Last Name");
			}
			else if(salary.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Salary");
			}
			else if(pnumber.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Phone Number");
			}
			else if(eaddress.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Email Address");
			}
			else if(address.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Address");
			}
			else if(joindate.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Join Date");
			}
			else if(dateofbirth.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Date Of Birth");
			}
			else {
			
			PreparedStatement ps;
			String query = "INSERT INTO employees_table (e_id, e_fname, e_lname, e_salary, e_pnumber, e_eaddress, e_address, e_jdate, e_dob, e_edate) VALUES(?,?,?,?,?,?,?,?,?,?)";
			try {
				ps = MyConnection.getConnection().prepareStatement(query);
				ps.setString(1, eid);
				ps.setString(2, fname);
				ps.setString(3, lname);
				ps.setString(4, salary);
				ps.setString(5, pnumber);
				ps.setString(6, eaddress);
				ps.setString(7, address);
				ps.setString(8, joindate);
				ps.setString(9, dateofbirth);
				ps.setString(10, enddate);
				
				if(ps.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null, "New Employee Added");
					
				}
			}
			catch(SQLException ex) {
				Logger.getLogger(Employee.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
	    });
	    
	    //Back Button
	    Button backButton = new Button("Back");
	    GridPane.setConstraints(backButton, 3, 0);
	    backButton.setOnAction(e -> {
	    	window.close();
	    	Home.showHome();
	    });
	    
	    //Delete Button
	    Button deleteButton = new Button("Delete");
	    GridPane.setConstraints(deleteButton, 1, 12);
	    deleteButton.setOnAction(e ->{
	    	
	    	String eid = empIdInput.getText();
			PreparedStatement ps;
			
			String query = "delete from employees_table where e_id =?";
			
			try {
				ps = MyConnection.getConnection().prepareStatement(query);
				ps.setString(1, eid);
				
				if(ps.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null,"Delete Successful");
				}
				else {
					JOptionPane.showMessageDialog(null, "No Such Employee");
				}
				
			}
			catch(SQLException ex) {
				Logger.getLogger(Customer.class.getName()).log(Level.SEVERE,null,ex);
			}
			
			empIdInput.clear();
			fnameInput.clear();
			lnameInput.clear();
			empSalaryInput.clear();
			empPhoneNumberInput.clear();
			empEmailAddessInput.clear();
			empAddressInput.clear();
			empJoinDateInput.getEditor().clear();
			dobInput.getEditor().clear();
			empEndDateInput.getEditor().clear();
			
	    });
	    //TableView 
	    ObservableList<ObservableList> data;
	    TableView tableview = null;
		tableview = new TableView();
		GridPane.setConstraints(tableview, 5, 0);
			Statement st = null;
			ResultSet rs;
			String driver = "com.mysql.cj.jdbc.Driver";
			String url ="jdbc:mysql://localhost:3306/shop";
			String user = "root";
			String pass = "1234";
			Connection conn = null;
			
			data = FXCollections.observableArrayList();
			
			try {
				Class.forName(driver);
			    conn = DriverManager.getConnection(url, user, pass);
				st = conn.createStatement();
				String query = "SELECT * FROM employees_table";
					
				rs = st.executeQuery(query);
				
				for(int i=0; i<rs.getMetaData().getColumnCount();i++) {
					final int j = i;
					TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
					col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
		                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
		                    return new SimpleStringProperty(param.getValue().get(j).toString());
		                    }
					  });

		            tableview.getColumns().addAll(col); 
		        }
				while(rs.next()){
	                ObservableList<String> row = FXCollections.observableArrayList();
	                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
	                    row.add(rs.getString(i));
	                }    
	                data.add(row);
				}
				tableview.setItems(data);
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		
		//Add everything to grid
		grid.getChildren().addAll(tableview, deleteButton, employeeLabel, empIdLabel, empIdInput, fnameLabel, fnameInput, lnameLabel, lnameInput,
				empSalaryLabel, empSalaryInput, empPhoneNumberLabel, empPhoneNumberInput, empEmailAddressLabel, empEmailAddessInput, empAddressLabel,
				empAddressInput, empJoinDateLabel, empJoinDateInput,dobLabel,dobInput,empEndDateLabel,empEndDateInput,  newButton, saveButton, backButton);
		
		Scene scene = new Scene(grid,1200,600);
		window.setScene(scene);
		window.show();
		
	}
}
