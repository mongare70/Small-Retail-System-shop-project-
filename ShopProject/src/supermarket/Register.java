package supermarket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Register{
	public static void showRegister() {
		Stage window = new Stage();
		window.setTitle("Register");
		
		//GridPane with 10px padding around edge
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Enter Details Label
		Label enterDetailsLabel = new Label("Enter your details");
		GridPane.setConstraints(enterDetailsLabel, 1, 0);
		
		//Name Label1 - constraints use(child,column,row)
		//First Name Label
		Label fnameLabel = new Label ("First Name:");
		GridPane.setConstraints(fnameLabel, 0, 1);
		
		//First Name Input
		TextField fnameInput = new TextField();
		fnameInput.setPromptText("first name");
		GridPane.setConstraints(fnameInput, 1, 1);
		
		//last Name label
		Label lnameLabel = new Label("Last Name:");
		GridPane.setConstraints(lnameLabel, 0, 2);
		
		//last Name Input
		TextField lnameInput = new TextField();
		lnameInput.setPromptText("last name");
		GridPane.setConstraints(lnameInput, 1, 2);
		
		//Username label
		Label uNameLabel = new Label("Username:");
		GridPane.setConstraints(uNameLabel, 0, 3);
		
		//Username Input
		TextField uNameInput = new TextField();
		uNameInput.setPromptText("username");
		GridPane.setConstraints(uNameInput, 1, 3);
		

		//Password label
		Label passwordLabel = new Label("Password:");
		GridPane.setConstraints(passwordLabel, 0, 4);
		
		//Password Input
		PasswordField passwordInput = new PasswordField();
		passwordInput.setPromptText("password");
		GridPane.setConstraints(passwordInput, 1, 4);
		
		//Retype Password Label
		Label retypePasswordLabel = new Label("Retype Password:");
		GridPane.setConstraints(retypePasswordLabel, 0, 5);
		
		//Retype Password Input
		PasswordField retypePasswordInput = new PasswordField();
		retypePasswordInput.setPromptText("password");
		GridPane.setConstraints(retypePasswordInput, 1, 5);
		
		//Birthday label
		Label bdateLabel =new Label("BirthDate");
		GridPane.setConstraints(bdateLabel, 0, 6);

		//Address Label
		Label addressLabel = new Label("Address:");
		GridPane.setConstraints(addressLabel, 0, 7);
				
		//Address Input
		TextField addressInput = new TextField();
		addressInput.setPromptText("address");
		GridPane.setConstraints(addressInput, 1,7);
				
		//Register Button
		Button registerButton = new Button("Register");
		GridPane.setConstraints(registerButton, 2, 9 );
		
		registerButton.setOnAction(e -> {
			String fname = fnameInput.getText();
			String lname = lnameInput.getText();
			String uname = uNameInput.getText();
			String pass = String.valueOf(passwordInput.getText());
			String re_pass = String.valueOf(retypePasswordInput.getText());
			String address = addressInput.getText();
			
			if(uname.equals("")) {
				JOptionPane.showMessageDialog(null, "Add A Username");
			}
			else if(pass.equals("")){
				JOptionPane.showMessageDialog(null, "Add A Password");
			}
			else if(!re_pass.equals(pass)) {
				JOptionPane.showMessageDialog(null, "Retype The Password Again");
			}
			else if(checkUsername(uname)) {
				JOptionPane.showMessageDialog(null, "This Username Already Exists");
			}
			
			else {
			
			PreparedStatement ps;
			String query = "INSERT INTO login_register (u_fname, u_lname, u_uname, u_pass, u_address) VALUES(?,?,?,?,?)";
			try {
				ps = MyConnection.getConnection().prepareStatement(query);
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, uname);
				ps.setString(4, pass);
				ps.setString(5, address);
				
				if(ps.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null, "New User Add");
					
				}
			}
			catch(SQLException ex) {
				Logger.getLogger(Register.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
		});
		
		//Cancel Button
		Button cancelButton = new Button("Cancel");
		GridPane.setConstraints(cancelButton, 1, 9);
		
		cancelButton.setOnAction(e -> {
			System.exit(0);
		});
		
		//Button to go back to Log In
		Button goBackToLoginButton = new Button("Go back To Login");
		GridPane.setConstraints(goBackToLoginButton, 2, 10);
		
		goBackToLoginButton.setOnAction(e -> {
			LogIn.window.show();
			window.close();
		});
		
		//Add everything to grid
		grid.getChildren().addAll(enterDetailsLabel, fnameLabel, fnameInput, lnameLabel, lnameInput, 
			 uNameLabel, uNameInput, passwordLabel, passwordInput, retypePasswordLabel,retypePasswordInput,
			 goBackToLoginButton, addressLabel, addressInput,registerButton, cancelButton);
		
		Scene scene = new Scene(grid,500,500);
		window.setScene(scene);
		window.show();

	}	
	public static boolean checkUsername(String username) {
		PreparedStatement ps;
		ResultSet rs;
		boolean checkUser = false;
		
		String query = "SELECT * FROM login_register WHERE u_uname =?";
		
		try {
		ps = MyConnection.getConnection().prepareStatement(query);
		ps.setString(1, username);
		
		rs =ps.executeQuery();
		
		if(rs.next()) {
			checkUser = true;
		}
		
		} catch(SQLException ex){
			Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE,null,ex);
		}
		return checkUser;
	}
}
