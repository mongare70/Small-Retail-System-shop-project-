package supermarket;

import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {
	static boolean answer;
	
	public static boolean display() {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Attention!");
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText("Are you sure you want to exit");
		
		//create two buttons
		Button yesButton = new Button("Yes");
		Button noButton = new Button("no");
		
		yesButton.setOnAction(e ->{
			answer = true;
			window.close();
		});
		
		noButton.setOnAction(e ->{
			answer = false;
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,yesButton,noButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
		
	}
}

