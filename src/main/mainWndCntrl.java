package main;

import com.sun.scenario.animation.shared.FiniteClipEnvelope;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class mainWndCntrl {

    @FXML
    private MenuItem exitBtn;

    @FXML
    private GridPane gridPane;

    private void updateGrid(GridPane grid, Human human) {
        javafx.application.Platform.runLater(() -> {
            grid.getChildren().clear();
            Circle circle = new Circle(10);
            circle.setFill(Color.RED);
            grid.add(circle, human.getX(), human.getY());
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


        human.move();
        updateGrid(gridPane, human);

        /*while (true) {
            human.move();
            updateGrid(gridPane, human);
        }*/

    }

}
