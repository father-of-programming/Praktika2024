package mainwnd;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.awt.Image;
import javax.swing.text.html.ImageView;

public class mainWndCntrl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitBtn;

    @FXML
    private Button newcolonyBtn;

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
