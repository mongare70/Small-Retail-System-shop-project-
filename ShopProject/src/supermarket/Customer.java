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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Customer{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void showCustomer() {
		Stage window = new Stage();
		window.setTitle("Customers");
        
		//GridPane with 10px padding around edge
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Customer Label
		Label customerLabel = new Label("Customer Details");
		GridPane.setConstraints(customerLabel, 1, 0);
		
		//Name Label1 - constraints use(child,column,row)
		Label cidLabel = new Label ("Customer Id:");
		GridPane.setConstraints(cidLabel, 0, 1);
		
		//Customer Id Input
		TextField cidInput = new TextField();
		cidInput.setPromptText("Customer Id");
		GridPane.setConstraints(cidInput, 1, 1);
		
		//First Name label
		Label fnameLabel = new Label("First Name:");
		GridPane.setConstraints(fnameLabel, 0, 2);
		
		//Password Input
		TextField fnameInput = new TextField();
		fnameInput.setPromptText("First Name");
		GridPane.setConstraints(fnameInput, 1, 2);
		
		//Last Name label
		Label lnameLabel = new Label("Last Name");
		GridPane.setConstraints(lnameLabel, 0, 3);
		
		//last Name Input
		TextField lnameInput = new TextField();
		lnameInput.setPromptText("last name");
		GridPane.setConstraints(lnameInput, 1, 3);
		
		//Customer Join Date label
		Label cjoindateLabel = new Label("Join Date");
		GridPane.setConstraints(cjoindateLabel, 0, 4);
		
		//Customer Join date Input
        DatePicker cjoindateInput = new DatePicker();
		cjoindateInput.setPromptText("join date");
		GridPane.setConstraints(cjoindateInput, 1, 4);
		
		//Customer Money Spent Label
		Label cmoneyspentLabel = new Label("Money Spent");
		GridPane.setConstraints(cmoneyspentLabel, 0, 5);
		
		
		//Customer Money Spent Input
		Label cmoneyspentInput = new Label();
		GridPane.setConstraints(cmoneyspentInput, 1, 5);
		
		//Customer Address Label
		Label caddressLabel = new Label("Address");
		GridPane.setConstraints(caddressLabel, 0, 6);
		
		//Customer Address Input
		TextField caddressInput = new TextField();
		caddressInput.setPromptText("Address");
		GridPane.setConstraints(caddressInput, 1, 6);
		
		//Customer Phone Number Label
		Label cphoneLabel = new Label("Phone Number");
		GridPane.setConstraints(cphoneLabel, 0, 7);
		
		//Customer Phone Number Input
		TextField cphoneInput = new TextField();
		cphoneInput.setPromptText("phone number");
		GridPane.setConstraints(cphoneInput, 1, 7);
		
		//Customer Email Address label
		Label emailaddressLabel = new Label("Email Address");
		GridPane.setConstraints(emailaddressLabel, 0, 8);
		
		//Customer Email Address Input
		TextField emailaddessInput = new TextField();
		emailaddessInput.setPromptText("email address");
		GridPane.setConstraints(emailaddessInput, 1, 8);
	
		//New Button
		Button newButton = new Button("New");
		GridPane.setConstraints(newButton, 1, 9);
		
		newButton.setOnAction(e ->{
			cidInput.clear();
			fnameInput.clear();
			lnameInput.clear();
			cjoindateInput.getEditor().clear();
			caddressInput.clear();
			cphoneInput.clear();
			caddressInput.clear();
			emailaddessInput.clear();
		});
	    
	    //Save Button
	    Button saveButton = new Button("Save");
	    GridPane.setConstraints(saveButton, 2, 10);
	    
	    saveButton.setOnAction(e -> {
	    	String cid = cidInput.getText();
	    	String fname = fnameInput.getText();
			String lname = lnameInput.getText();
			String jdate = cjoindateInput.getEditor().getText();
			String address = caddressInput.getText();
			String pnumber = cphoneInput.getText();
			String eaddress = emailaddessInput.getText();
			
			if (cid.equals("")) {
				JOptionPane.showMessageDialog(null, "Add The Customer Id");
			}
			else if(fname.equals("")) {
				JOptionPane.showMessageDialog(null, "Add First Name");
			}
			else if(lname.equals("")){
				JOptionPane.showMessageDialog(null, "Add A Last Name");
			}
			else if(jdate.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Join Date");
			}
			else if(address.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Address");
			}
			else if(pnumber.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Phone Number");
			}
			else if(eaddress.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Email Address");
			}
			
			else {
			
			PreparedStatement ps;
			String query = "INSERT INTO customer_table (c_id, c_fname, c_lname, c_jdate, c_address, c_pnumber, c_eaddress) VALUES(?,?,?,?,?,?,?)";
			try {
				ps = MyConnection.getConnection().prepareStatement(query);
				ps.setString(1, cid);
				ps.setString(2, fname);
				ps.setString(3, lname);
				ps.setString(4, jdate);
				ps.setString(5, address);
				ps.setString(6, pnumber);
				ps.setString(7, eaddress);
				
				if(ps.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null, "New Customer Added");
					
				}
			}
			catch(SQLException ex) {
				Logger.getLogger(Customer.class.getName()).log(Level.SEVERE,null,ex);
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
	    GridPane.setConstraints(deleteButton, 1, 10);
	    deleteButton.setOnAction(e ->{
	    	
	    	String customerid = cidInput.getText();
			PreparedStatement ps;
			
			String query = "delete from customer_table where c_id =?";
			
			try {
				ps = MyConnection.getConnection().prepareStatement(query);
				ps.setString(1, customerid);
				
				if(ps.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null,"Delete Successful");
				}
				else {
					JOptionPane.showMessageDialog(null, "No Such Customer");
				}
				
			}
			catch(SQLException ex) {
				Logger.getLogger(Customer.class.getName()).log(Level.SEVERE,null,ex);
			}
			
			cidInput.clear();
			fnameInput.clear();
			lnameInput.clear();
			cjoindateInput.getEditor().clear();
			caddressInput.clear();
			cphoneInput.clear();
			caddressInput.clear();
			emailaddessInput.clear();
	    	
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
				String query = "SELECT * FROM customer_table";
					
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
		grid.getChildren().addAll(tableview, deleteButton, customerLabel, cidLabel, cidInput, fnameLabel, fnameInput, lnameLabel, lnameInput,
				cjoindateLabel, cjoindateInput, cmoneyspentLabel, cmoneyspentInput, caddressLabel, caddressInput, cphoneLabel,
				cphoneInput, emailaddressLabel, emailaddessInput, newButton, saveButton, backButton);
		
		Scene scene = new Scene(grid,1200,600);
		window.setScene(scene);
		window.show();
		}
}
