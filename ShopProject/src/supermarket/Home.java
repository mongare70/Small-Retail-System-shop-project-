package supermarket;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Home{
	public static void showHome() {
		Stage window = new Stage();
		window.setTitle("SwitchBoard");
		
		VBox vBox = new  VBox();
		vBox.setPadding(new Insets(10, 50, 50, 50));
	    vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);
		
		//Main Label
		Label mainLabel = new Label("Welcome To Masters Shop");
		mainLabel.setFont(Font.font("Arial", 16));
		mainLabel.setStyle("-fx-border-color: black;");
		mainLabel.setStyle("-fx-border-width: 2;");
		
		//Customer Button
	    Button customerButton = new Button("Customer Form");
		customerButton.setOnAction(e ->{
				Customer. showCustomer();
				window.hide();
			});
		//Employee Button
		Button employeeButton = new Button("Employee Form");
		employeeButton.setOnAction(e ->{
			Employee.showEmployee();
			window.hide();
		});
		
		//Product Button
		Button productButton = new Button("Product Form");
		productButton.setOnAction(e ->{
			Product.showProduct();
			window.hide();
		});
		
		//Transaction Button
		Button transactionButton = new Button("Transaction Form");
		transactionButton.setOnAction(e ->{
			Transaction.showTransaction();
			window.hide();
		});
		
		//Exit
		Button logoutButton = new Button("Exit");
		logoutButton.setOnAction(e ->{
			window.close();
		});
				
		
		vBox.getChildren().addAll(mainLabel, customerButton, employeeButton, productButton, transactionButton, logoutButton);
		Scene scene = new Scene(vBox,600,600);
		window.setScene(scene);
		window.show();
	}
}
