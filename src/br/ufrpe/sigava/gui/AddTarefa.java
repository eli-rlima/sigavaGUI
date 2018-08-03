package br.ufrpe.sigava.gui; 
 
import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
 
/** 
 * 
 * @author Thiago 
 */ 
public class AddTarefa extends Application{ 
    private static Stage stage; 
 
    public static Stage getStage() { 
        return stage; 
    } 
 
    public static void setStage(Stage sta) { 
        stage = sta; 
    } 
     
    public static void fechar(){ 
        stage.close(); 
    } 
     
    @Override 
    public void start(Stage primaryStage) throws Exception { 
        primaryStage.resizableProperty().setValue(Boolean.FALSE); 
        Parent rootADM = FXMLLoader.load(getClass().getResource("AddTarefa.fxml")); 
        Scene sceneADM = new Scene(rootADM); 
        primaryStage.setScene(sceneADM); 
        primaryStage.show(); 
        setStage(stage); 
    } 
     
    public static void main(String[] args) { 
        launch(args); 
    } 
     
} 