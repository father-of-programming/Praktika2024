package menu;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class menuWndCntrl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitBtn;

    @FXML
    private Button newcolonyBtn;

    public void handlenewcolonyBtn(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../main/mainWnd.fxml"));

        Scene scene = new Scene(root);
        Stage newstage = new Stage();
        newstage.setTitle("New wnd");
        newstage.setScene(scene);
        newstage.initModality(Modality.WINDOW_MODAL);
        //newstage.initOwner(newcolonyBtn.getScene().getWindow());
        newstage.show();
    }

    @FXML
    private Button statsBtn;

    @FXML
    private ImageView imageview;

    @FXML
    void initialize() {
        exitBtn.setOnAction(actionEvent -> {
            System.exit(0);
        });
    }

}
