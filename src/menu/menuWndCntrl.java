package menu;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class menuWndCntrl {

    @FXML
    private Button exitBtn;

    @FXML
    private Button startBtn;

    @FXML
    void initialize() {
        exitBtn.setOnAction(actionEvent -> {
            System.exit(0);
        });

        startBtn.setOnAction(actionEvent -> {
            startBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/mainWnd.fxml"));
            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.getIcons().add(new Image("utils\\ico.png"));
            stage.setTitle("\u0421\u0438\u043C\u0443\u043B\u044F\u0442\u043E\u0440");
            stage.showAndWait();

        });
    }

}
