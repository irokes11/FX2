package MsgBox;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public enum MessageBoxButton {
		Ok, YesNo, YesNoCancel;
	}
	
	public enum MessageBoxResult {
		Ok, Yes, No, Cancel;
	}

	public enum MessageBoxIcon {
		Information, Warning, CriticalError;
	}
	
	
	public static class MessageBox {
		
		private static Stage generalStage;
		
		public static MessageBoxResult show( String messageText, String titleText, MessageBoxButton buttons, MessageBoxIcon icons) {
			
			
			VBox box1 = new VBox();
			box1.setAlignment(Pos.CENTER);
			
			Text text1 = new Text();
			text1.setText(messageText);
			
			HBox hbox1 = new HBox();
			hbox1.setAlignment(Pos.CENTER);
			
			
			if ( icons == MessageBoxIcon.Information ) {
			
				
				Image image1 = new Image(
				
				ClassLoader.getSystemResourceAsStream("StatusInformation_64x.png"));
	            ImageView image1View = new ImageView(image1);
				image1View.setImage(image1);
				hbox1.getChildren().add(new ImageView(image1));
				
				
				  String cssLayout = "-fx-border-color: blue ;\n" +
		                   "-fx-border-insets: 5;\n" +
		                   "-fx-border-width: 3;\n";
				  box1.setStyle(cssLayout);
				  
				  
			} else if (icons == MessageBoxIcon.Warning) {
				Image image2 = new Image(
						
				ClassLoader.getSystemResourceAsStream("StatusWarning_64x.png"));
			    ImageView image1View = new ImageView(image2);
			    image1View.setImage(image2);
				hbox1.getChildren().add(new ImageView(image2));
				 String cssLayout = "-fx-border-color: orange ;\n" +
		                   "-fx-border-insets: 5;\n" +
		                   "-fx-border-width: 3;\n";
				  box1.setStyle(cssLayout);
						
			} else if (icons == MessageBoxIcon.CriticalError) {
				Image image3 = new Image(
				ClassLoader.getSystemResourceAsStream("StatusCriticalError_64x.png"));
	            ImageView image1View = new ImageView(image3);
				image1View.setImage(image3);
				hbox1.getChildren().add(new ImageView(image3));
				
				 String cssLayout = "-fx-border-color: red ;\n" +
		                   "-fx-border-insets: 5;\n" +
		                   "-fx-border-width: 3;\n";
				  box1.setStyle(cssLayout);
			}
			
			
			hbox1.getChildren().addAll(text1);
			hbox1.setPadding(new Insets(25, 35, 25, 35));
			
			HBox hbox2 = new HBox();
			hbox2.setAlignment(Pos.CENTER);
			hbox2.setPrefSize(5000, 50);
			
			
			
			if ( buttons == MessageBoxButton.Ok ) {
				Button btnOk = new Button("OK");
				btnOk.setPrefHeight(100);
				btnOk.setPrefWidth(100);;
				btnOk.setOnAction(e -> closeStage());
				hbox2.getChildren().addAll(btnOk);	
			}
			
			else if ( buttons == MessageBoxButton.YesNo ) {
				
				Image imageYes = new Image(
				ClassLoader.getSystemResourceAsStream("StatusOK_32x.png"));
			    ImageView imageViewYes = new ImageView(imageYes);
			    
			    Image imageNo = new Image(
				ClassLoader.getSystemResourceAsStream("StatusNo_32xLG.png"));
			    ImageView imageViewNo = new ImageView(imageNo);
				
				Button btnYes = new Button("YES",imageViewYes);
				btnYes.setOnAction(e -> closeStage());
				btnYes.setDefaultButton(true);
				Button btnNo = new Button("NO",imageViewNo);
				btnNo.setOnAction(e -> closeStage());	
				btnNo.setCancelButton(true);
			
				hbox2.getChildren().addAll(btnYes, btnNo);
				
			}
			
			else if ( buttons == MessageBoxButton.YesNoCancel ) {
				
				Image imageYes = new Image(
				ClassLoader.getSystemResourceAsStream("StatusOK_32x.png"));
				ImageView imageViewYes = new ImageView(imageYes);
					    
				Image imageNo = new Image(
				ClassLoader.getSystemResourceAsStream("StatusNo_32xLG.png"));
				ImageView imageViewNo = new ImageView(imageNo);
				
				
				
				Button btnYes = new Button("YES",imageViewYes);
				btnYes.setOnAction(e -> closeStage());
				Button btnNo = new Button("NO",imageViewNo);
				btnNo.setOnAction(e -> closeStage());	
				Button btnCancel = new Button("Cancel");
				btnCancel.setOnAction(e -> closeStage());
				btnCancel.setPrefHeight(40);
				btnCancel.setPrefWidth(100);
				btnYes.setDefaultButton(true);
				btnNo.setCancelButton(true);
				hbox2.getChildren().addAll(btnYes, btnNo, btnCancel);
				
				
				
			}
			
			box1.getChildren().addAll(hbox1, hbox2);
			
			
			
			Scene scene1 = new Scene(box1, 550, 200);
			
			generalStage = new Stage();	
			generalStage.setScene(scene1);
			generalStage.sizeToScene();
			generalStage.setTitle(titleText);
			generalStage.setAlwaysOnTop(true);
			generalStage.centerOnScreen();
			generalStage.setResizable(false);
			generalStage.initModality(Modality.APPLICATION_MODAL);
			generalStage.showAndWait();			
						
			return MessageBoxResult.Cancel;
		}
		
		public static MessageBoxResult show(String messageText)
		{
			Text text1 = new Text();
			text1.setText(messageText);
			
			
			Button btnInformation = new Button("OK");
			btnInformation.setOnAction(e -> closeStage());
			
			HBox hbox1 = new HBox();
			hbox1.setAlignment(Pos.CENTER);
			hbox1.getChildren().addAll(text1, btnInformation);	
			
			Scene scene1 = new Scene(hbox1, 400, 300);
			
			generalStage = new Stage();	
			generalStage.setScene(scene1);
			generalStage.centerOnScreen();
			generalStage.setResizable(false);
			generalStage.initModality(Modality.APPLICATION_MODAL);
			generalStage.showAndWait();
			
			return MessageBoxResult.Cancel;
		}
		
		private static void closeStage()
		{
			generalStage.close();
		}
	}
	
	@Override
	public void start(Stage generalStage) {
		Button btnInformation = new Button("Information");
		btnInformation.setOnAction(e -> btn_infoClick());
		btnInformation.setStyle("-fx-border-color: #0000ff; -fx-border-width: 5px;");
		Button btnWarning = new Button("Warning");
		btnWarning.setOnAction(e -> btn_warningClick());
		btnWarning.setStyle("-fx-border-color: #ffa500; -fx-border-width: 5px;");
		Button btnCriticalError = new Button("CriticalError");
		btnCriticalError.setOnAction(e -> btn_CriticalErrorClick());
		btnCriticalError.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
		HBox hbox = new HBox();
		hbox.getChildren().addAll(btnInformation, btnWarning, btnCriticalError);
		hbox.setPadding(new Insets(50, 50, 50, 35));
		hbox.setSpacing(30);
		hbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(hbox);
		generalStage = new Stage();
		generalStage.setScene(scene);
		generalStage.setTitle("Message Boxes");
		generalStage.show();
		generalStage.sizeToScene();
	}
	
	private void btn_infoClick() {	
		
		
		MessageBox.show("IMPORTANT INFORMATION", "INFORMATION", MessageBoxButton.Ok, MessageBoxIcon.Information);
	}
	
	private void btn_warningClick() {	
		MessageBox.show("WARNING", "WARNING", MessageBoxButton.YesNo, MessageBoxIcon.Warning);
	}
	
	private void btn_CriticalErrorClick() {	
		MessageBox.show("ERROR FOUND", "ERROR", MessageBoxButton.YesNoCancel, MessageBoxIcon.CriticalError);
	}
	
	
	
	
}



	