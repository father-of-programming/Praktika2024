package menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class menuWnd extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menuWnd.fxml"));
        stage.setTitle("ORVI");
        stage.setMaximized(true);
        stage.setScene(new Scene(root,1800, 900));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}