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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Product{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void showProduct() {
		Stage window = new Stage();
		window.setTitle("Product");
		
		//GridPane with 10px padding around edge
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Product Details Label
		Label productLabel = new Label("Product Details");
		GridPane.setConstraints(productLabel, 1, 0);
		
		//Name Label1 - constraints use(child,column,row)
		//Product Id Label
		Label productIdLabel = new Label ("Product Id:");
		GridPane.setConstraints(productIdLabel, 0, 1);
		
		//Product Id Input
		TextField productIdInput = new TextField();
		productIdInput.setPromptText("product id");
		GridPane.setConstraints(productIdInput, 1, 1);
		
		//Supplier Id label
		Label supplierIdLabel = new Label("Supplier Id:");
		GridPane.setConstraints(supplierIdLabel, 0, 2);
		
		// Supplier Id Input
		TextField supplierIdInput = new TextField();
		supplierIdInput.setPromptText("supplier id");
		GridPane.setConstraints(supplierIdInput, 1, 2);
		
		//Product Name label
		Label productNameLabel = new Label("Product Name:");
		GridPane.setConstraints(productNameLabel, 0, 3);
		
		//Product Name Input
		TextField productNameInput = new TextField();
		productNameInput.setPromptText("product name");
		GridPane.setConstraints(productNameInput, 1, 3);
		
		//Cost Price label
		Label costPriceLabel = new Label("Cost Price:");
		GridPane.setConstraints(costPriceLabel, 0, 4);
		
		//Cost Price Input
        TextField costPriceInput = new TextField();
		costPriceInput.setPromptText("cost price");
		GridPane.setConstraints(costPriceInput, 1, 4);
		
		//Quantity label
		Label quantityLabel = new Label("Quantity:");
		GridPane.setConstraints(quantityLabel, 0, 5);
		
		//Quantity input
		TextField quantityInput = new TextField();
		quantityInput.setPromptText("quantity");
		GridPane.setConstraints(quantityInput, 1, 5);
		
		//Product Type Label
		Label productTypeLabel = new Label("Product Type:");
		GridPane.setConstraints(productTypeLabel, 0, 6);
		
		//Product Type Input
		TextField productTypeInput = new TextField();
		productTypeInput.setPromptText("product type");
		GridPane.setConstraints(productTypeInput, 1, 6);
		
		//Market Price Label
		Label marketPriceLabel = new Label("Market Price:");
		GridPane.setConstraints(marketPriceLabel, 0, 7);
		
		//Market Price input
		TextField marketPriceInput = new TextField();
		marketPriceInput.setPromptText("markerprice");
		GridPane.setConstraints(marketPriceInput, 1, 7);
		
		//New Button
		Button newButton = new Button("New");
		GridPane.setConstraints(newButton, 1, 9);
		newButton.setOnAction(e ->{
			productIdInput.clear();
			productNameInput.clear();
			costPriceInput.clear();
			quantityInput.clear();
			productTypeInput.clear();
			marketPriceInput.clear();
		});
		
		
	    //Save Button
	    Button saveButton = new Button("Save");
	    GridPane.setConstraints(saveButton, 2, 10);
	    saveButton.setOnAction(e -> {
	    	String pid = productIdInput.getText();
	    	String pname = productNameInput.getText();
			String costprice = costPriceInput.getText();
			String quantity = quantityInput.getText();
			String ptype = productTypeInput.getText();
			String marketprice = marketPriceInput.getText();
			
			if (pid.equals("")) {
				JOptionPane.showMessageDialog(null, "Add The Product Id");
			}
			else if(pname.equals("")) {
				JOptionPane.showMessageDialog(null, "Add Product Name");
			}
			else if(costprice.equals("")){
				JOptionPane.showMessageDialog(null, "Add Cost Price");
			}
			else if(quantity.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Quantity");
			}
			else if(ptype.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Product Type");
			}
			else if(marketprice.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter Market Price");
			}
	
			else {
			
			PreparedStatement ps;
			String query = "INSERT INTO product_table (p_id, p_name, p_cprice, p_quantity, p_type, p_mprice) VALUES(?,?,?,?,?,?)";
			try {
				ps = MyConnection.getConnection().prepareStatement(query);
				ps.setString(1, pid);
				ps.setString(2, pname);
				ps.setString(3, costprice);
				ps.setString(4, quantity);
				ps.setString(5, ptype);
				ps.setString(6, marketprice);
				
				if(ps.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null, "New Product Added");
					
				}
			}
			catch(SQLException ex) {
				Logger.getLogger(Product.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
	    });
	    
	    //Back Button
	    Button backButton = new Button("Back");
	    GridPane.setConstraints(backButton, 3, 0);
	    backButton.setOnAction(e ->{
	    	Home.showHome();
	    	window.close();
	    });
	    
		
	    //Delete Button
	    Button deleteButton = new Button("Delete");
	    GridPane.setConstraints(deleteButton, 1, 10);
	    deleteButton.setOnAction(e ->{
	    	
	    	String productId = productIdInput.getText();
			PreparedStatement ps;
			
			String query = "delete from product_table where p_id =?";
			
			try {
				ps = MyConnection.getConnection().prepareStatement(query);
				ps.setString(1, productId);
				
				if(ps.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null,"Delete Successful");
				}
				else {
					JOptionPane.showMessageDialog(null, "No Such Product");
				}
				
			}
			catch(SQLException ex) {
				Logger.getLogger(Product.class.getName()).log(Level.SEVERE,null,ex);
			}
			
			productIdInput.clear();
			productNameInput.clear();
			costPriceInput.clear();
			quantityInput.clear();
			productTypeInput.clear();
			marketPriceInput.clear();
	    	
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
				String query = "SELECT * FROM product_table";
					
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
		grid.getChildren().addAll(tableview, deleteButton, productLabel, productIdLabel,productIdInput,
				productNameLabel, productNameInput, costPriceLabel, costPriceInput,quantityLabel, quantityInput , productTypeLabel, productTypeInput,
				marketPriceLabel, marketPriceInput, newButton, saveButton, backButton);
		
		Scene scene = new Scene(grid,1200,600);
		window.setScene(scene);
		window.show();
		
	}
}
