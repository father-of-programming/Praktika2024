package menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class menuWnd extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menuWnd.fxml"));
        stage.setTitle("\u0421\u0438\u043C\u0443\u043B\u044F\u0442\u043E\u0440");
        stage.setResizable(false);
        stage.getIcons().add(new Image("utils\\ico.png"));
        stage.setScene(new Scene(root,900, 900));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}