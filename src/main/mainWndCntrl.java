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

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class mainWndCntrl {

    @FXML
    private MenuItem exitBtn;

    @FXML
    private GridPane gridPane;

    private void updateGrid(GridPane grid, Human ... humans) {
        javafx.application.Platform.runLater(() -> {
            grid.getChildren().clear();
            for (Human human : humans) {
                Rectangle rectangle = new Rectangle(192, 98);

                if (human.getState() == 2) {
                    rectangle.setFill(Color.GREEN);
                } else if (human.getState() == 1) {
                    rectangle.setFill(Color.YELLOW);
                } else {
                    rectangle.setFill(Color.RED);
                }
                grid.add(rectangle, human.getX(), human.getY());
            }
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

        Human human = new Human(new Random().nextInt(3), 2, 1, 1);

        Human human2 = new Human(new Random().nextInt(3), 2, 4, 4);

        new Thread(() ->{
            try {
                while (true) {
                    Set<String> occupiedPositions = new HashSet<>();
                    occupiedPositions.add(human.getPosition());
                    occupiedPositions.add(human2.getPosition());
                    human.move(occupiedPositions);
                    occupiedPositions.add(human.getPosition());

                    human2.move(occupiedPositions);
                    updateGrid(gridPane, human, human2);
                    Thread.sleep(500);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();


    }

}
