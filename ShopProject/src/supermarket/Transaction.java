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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Transaction {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void showTransaction() {
		Stage window = new Stage();
		window.setTitle("Transaction");
		
		//GridPane with 10px padding around edge
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Transaction Label9
		Label transactionLabel = new Label("Enter Details");
		GridPane.setConstraints(transactionLabel, 0, 0);
		
		//Name Label1 - constraints use(child,column,row)
		//Customer Name Label
		Label customerNameLabel = new Label("Customer Name");
		GridPane.setConstraints(customerNameLabel, 0, 1);
		
		//Customer Name Input
		Label customerNameInput = new Label();
		GridPane.setConstraints(customerNameInput, 1, 1);
		
		//Customer Id Label
		Label customerIdLabel = new Label("Customer Id");
		GridPane.setConstraints(customerIdLabel, 0, 2);

		//Customer Id Input
		TextField customerIdInput = new TextField();
		customerIdInput.setPromptText("customer id");
		GridPane.setConstraints(customerIdInput, 1, 2);
		
		//Product Name Label
		Label productNameLabel = new Label ("Product Name:");
		GridPane.setConstraints(productNameLabel, 0, 3);
		
		//Product Name Input
		TextField productNameInput = new TextField();
		productNameInput.setPromptText("product name");
		GridPane.setConstraints(productNameInput, 1, 3);
		
		//Product Id label
		Label productIdLabel = new Label("Product Id:");
		GridPane.setConstraints(productIdLabel, 0, 4);
		
		//Product Name Input
		Label productIdInput = new Label();
		GridPane.setConstraints(productIdInput, 1, 4);
		
		//Quantity Label
		Label quantityLabel = new Label("Quantity:");
		GridPane.setConstraints(quantityLabel, 0, 5);
		
		//Quantity Input
        TextField quantityInput = new TextField();
		quantityInput.setPromptText("quantity");
		GridPane.setConstraints(quantityInput, 1, 5);
		
		//Price Label
		Label priceLabel = new Label("Price:");
		GridPane.setConstraints(priceLabel, 0, 6);
		
		//Price Input
		Label priceInput = new Label();
		GridPane.setConstraints(priceInput, 1, 6);
		
		
		//Total Textfield label
		Label totalTextFieldLabel = new Label("Total Price");
		GridPane.setConstraints(totalTextFieldLabel, 0, 7);
		
		//Total TextField input
		Label totalTextField = new Label();
		GridPane.setConstraints(totalTextField, 1, 7);
		totalTextField.setVisible(true);
		totalTextField.setFont(Font.font("Arial", 16));
		totalTextField.setStyle("-fx-border-color: black;");
		totalTextField.setStyle("-fx-border-width: 2;");
		
		//Transaction Date Label
		Label tdateLabel = new Label("Transaction Date");
		GridPane.setConstraints(tdateLabel, 0, 8);
		
		//Transaction Date Input
		DatePicker tdateInput = new DatePicker();
		tdateInput.setPromptText("transaction date");
		GridPane.setConstraints(tdateInput, 1, 8);
		
		//Transaction Amount Payed Label
		Label amountpayedLable = new Label("Amount Payed");
		GridPane.setConstraints(amountpayedLable, 0, 9);
		
		//Transaction amount payed Input
		TextField amountpayedInput = new TextField();
		amountpayedInput.setPromptText("Enter amount payed");
		GridPane.setConstraints(amountpayedInput, 1, 9);
		
		//Balance label
		Label balanceLabel = new Label("Balance");
		GridPane.setConstraints(balanceLabel, 2, 9);
		
		//Balance Input
		Label balanceInput = new Label();
		GridPane.setConstraints(balanceInput, 3, 9);
		
		//New Button
				Button newButton = new Button("New");
				GridPane.setConstraints(newButton, 1, 10);
				
				newButton.setOnAction(e ->{
					customerNameInput.setText("");
					customerIdInput.clear();
					productNameInput.clear();
					productIdInput.setText("");
					quantityInput.clear();
					priceInput.setText("");
					totalTextField.setText("");
					tdateInput.getEditor().setText("");
				});
		//Add Button
		Button addButton = new Button("Add");
		GridPane.setConstraints(addButton, 2, 10);
		addButton.setOnAction(e -> {
			String productname = productNameInput.getText();
			String customerid = customerIdInput.getText();
			
			ResultSet rs;
			PreparedStatement ps;
			String query ="SELECT * FROM product_table WHERE p_name =?"; 
			
			try {
				ps = MyConnection.getConnection().prepareStatement(query);
			
				ps.setString(1, productname);
				
				rs =ps.executeQuery();
				
				if(rs.next()) {
					productIdInput.setText(rs.getString("p_id")); 
					priceInput.setText(rs.getString("p_cprice"));
				}
				else {
					JOptionPane.showMessageDialog(null, "No Such Product");
				}
				
			}
			catch(SQLException ex) {
				Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE,null,ex);
			}
			
			ResultSet rs1;
			PreparedStatement ps1;
			
			String query1 ="SELECT * FROM customer_table WHERE c_id =?";
			
			try {
				ps1 = MyConnection.getConnection().prepareStatement(query1);
			
				ps1.setString(1, customerid);
				
				rs1 =ps1.executeQuery();
				
				if(rs1.next()) {
					customerNameInput.setText(rs1.getString("c_fname"));
				}
				else {
					JOptionPane.showMessageDialog(null, "No Such Customer");
				}
				
			}
			catch(SQLException ex) {
				Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE,null,ex);
			}
		    	
				int quantity = Integer.parseInt(quantityInput.getText());
				double price = Double.parseDouble(priceInput.getText());
				double totalprice = price*quantity;
			    totalTextField.setText(totalprice+"");
			    
			    double amountpayed = Double.parseDouble(amountpayedInput.getText());
			    double balance =  amountpayed - totalprice;
			    balanceInput.setText(balance+"");
		});
		
		//Sell Button
		Button sellButton = new Button("Sell");
		GridPane.setConstraints(sellButton, 2, 11);
		sellButton.setOnAction(e -> {
			String customername = customerNameInput.getText();
			String productname = productNameInput.getText();
			String quantity = quantityInput.getText();
			String totalprice = totalTextField.getText();
			String productid = productIdInput.getText();
			String customerid = customerIdInput.getText();
			String transactiondate = tdateInput.getEditor().getText();
			
			if (customername.equals("")) {
				JOptionPane.showMessageDialog(null, "Add Customer Name");
			}
			else if(productname.equals("")) {
				JOptionPane.showMessageDialog(null, "Add Product Name");
			}
			else if(quantity.equals("")){
				JOptionPane.showMessageDialog(null, "Add Quantity");
			}
			else if(Integer.parseInt(quantity)<=0){
				JOptionPane.showMessageDialog(null, "Quantity Cannot Be Less Than Or Equal to 0");
			}
			
			else if(totalprice.equals("")) {
				JOptionPane.showMessageDialog(null, "Add First");
			}
			else if(transactiondate.equals("")) {
				JOptionPane.showMessageDialog(null, "Add Transaction Date");
			}else {
			PreparedStatement ps;
			String query ="INSERT INTO transaction_table(c_name, p_name, p_quantity, t_price, p_id, c_id, t_date) VALUES(?,?,?,?,?,?,?)"; 
			
			try {
				ps = MyConnection.getConnection().prepareStatement(query);
				ps.setString(1, customername);
				ps.setString(2, productname);
				ps.setString(3, quantity);
				ps.setString(4, totalprice);
				ps.setString(5, productid);
				ps.setString(6, customerid);
				ps.setString(7, transactiondate);
				
				if(ps.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null, "Sale Successful");
					
				}
			}
			catch(SQLException ex) {
				Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE,null,ex);
			}
			
			customerNameInput.setText("");
			customerIdInput.clear();
			productNameInput.clear();
			productIdInput.setText("");
			quantityInput.clear();
			priceInput.setText("");
			totalTextField.setText("");
			tdateInput.getEditor().setText("");
			
			}
		});
		
		//Back Button
		Button backButton = new Button("Back");
		  GridPane.setConstraints(backButton, 3, 0);
		    backButton.setOnAction(e -> {
		    	window.close();
		    	Home.showHome();
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
					String query = "SELECT * FROM transaction_table";
						
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
		grid.getChildren().addAll(tableview,balanceLabel, balanceInput, amountpayedLable, amountpayedInput, tdateLabel, tdateInput, customerNameLabel, customerNameInput, customerIdInput, customerIdLabel,transactionLabel,productNameLabel, productNameInput, productIdLabel, productIdInput,quantityLabel,
				quantityInput, priceLabel, priceInput, newButton, addButton,totalTextFieldLabel,totalTextField, sellButton, backButton);
		
		Scene scene = new Scene(grid,1200,600);
		window.setScene(scene);
		window.show();
	}
}
