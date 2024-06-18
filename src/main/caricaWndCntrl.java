package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class caricaWndCntrl {

    @FXML
    private Button backBtn;

    @FXML
    private Button colonyBtn;

    @FXML
    private Slider slider;

    @FXML
    void initialize() {
        backBtn.setOnAction(actionEvent -> {
            backBtn.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/menu/menuWnd.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Colony");
            stage.setMaximized(true);
            stage.setScene(new Scene(root,1920, 1080));
            stage.show();
        });

        colonyBtn.setOnAction(actionEvent -> {
            colonyBtn.getScene().getWindow().hide();

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
            stage.setTitle("Colony");
            stage.setMaximized(true);
            stage.setScene(new Scene(root,1920, 1080));
            stage.showAndWait();
        });
    }

}
