package main;

import com.sun.scenario.animation.shared.FiniteClipEnvelope;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class mainWndCntrl {

    @FXML
    private MenuItem exitBtn;

    @FXML
    private GridPane gridPane;

    private void updateGrid(GridPane grid, Human human) {
        javafx.application.Platform.runLater(() -> {
            grid.getChildren().clear();
            Rectangle rectangle = new Rectangle(192,98);
            rectangle.setFill(Color.BLACK);
            grid.add(rectangle, human.getX(), human.getY());
        });
    }

    @FXML
    private MenuBar menuBar;

    @FXML
    private Pane paneStngs;

    @FXML
    void initialize() {
        exitBtn.setOnAction(actionEvent -> {
            System.exit(0);
        });

        Human human = new Human(0, 2, 0, 0);

        new Thread(() ->{
            try {
                while (true) {
                    human.move();
                    updateGrid(gridPane, human);
                    Thread.sleep(500);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();


    }

}
