package menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class menuWnd extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menuWnd.fxml"));
        stage.setTitle("TBG");
        //stage.setFullScreenExitHint("");    //убираем сообщение о выходе из полноэкр режима
        stage.setScene(new Scene(root,700, 390));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}