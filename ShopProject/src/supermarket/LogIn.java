package supermarket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LogIn extends Application {
	static Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
			window = primaryStage;
			window.setTitle("Log In");
			
			window.setOnCloseRequest(e -> {
				e.consume();
				closeProgram();
				});
			
			//GridPane with 10px padding around edge
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setPadding(new Insets(10,10,10,10));
			grid.setVgap(8);
			grid.setHgap(10);
			
			//Log In Label
			Label LogInLabel = new Label("Welcome");
			GridPane.setConstraints(LogInLabel, 1, 0);
			
			//Name Label1 - constraints use(child,column,row)
			Label nameLabel = new Label ("Username:");
			GridPane.setConstraints(nameLabel, 0, 1);
			
			//Name Input
			TextField nameInput = new TextField();
			nameInput.setPromptText("username");
			GridPane.setConstraints(nameInput, 1, 1);
			
			//Password label
			Label passLabel = new Label("Password:");
			GridPane.setConstraints(passLabel, 0, 2);
			
			//Password Input
			PasswordField passInput = new PasswordField();
			passInput.setPromptText("password");
			GridPane.setConstraints(passInput, 1, 2);
			
			//Login Button
			Button loginButton = new Button("Log In");
			GridPane.setConstraints(loginButton, 1, 3);
			loginButton.setOnAction(e -> {
				
				PreparedStatement ps;
				ResultSet rs;
				String uname = nameInput.getText();
				String pass = String.valueOf(passInput.getText());
				
				String query = "SELECT * FROM login_register WHERE u_uname =? AND u_pass =?";
				
				try {
				ps = MyConnection.getConnection().prepareStatement(query);
				
				ps.setString(1, uname);
				ps.setString(2, pass);
				
				rs =ps.executeQuery();
				
				if(rs.next()) {
					Home. showHome();
					window.close();
				}	
				
				else {
					JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed",1);
				}
				
				}
				catch(SQLException ex){
					Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE,null,ex);
				}
			});
			
			//Close Button
			Button closeButton = new Button("Close");
			GridPane.setConstraints(closeButton, 2, 3);
			closeButton.setOnAction(e -> closeProgram());
			
			//Click here to register Label
			Button clickHereToRegisterButton = new Button("Click Here To Register");
			GridPane.setConstraints(clickHereToRegisterButton, 2, 5);
			
			clickHereToRegisterButton.setOnAction(e -> {
				Register. showRegister();
				window.hide();
			});
			
			//Add everything to grid
			grid.getChildren().addAll(LogInLabel, nameLabel, nameInput, passLabel, passInput, loginButton, closeButton, clickHereToRegisterButton);
			
			Scene scene = new Scene(grid,500,500);
			window.setScene(scene);
			window.show();
	}

	public void closeProgram() {
		boolean answer = ConfirmBox.display();
				if(answer) {
					window.close();
				}
	}
}
