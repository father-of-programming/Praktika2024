package main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static utils.Constants.*;
import static utils.Constants.HUMAN_SIZE;


public class mainWndCntrl {
    private List<Human> people;
    private int days;
    private Random random;
    private int speed = 500;

    @FXML
    private MenuItem exitBtn;

    @FXML
    private MenuItem stopBtn;

    /*private boolean stopsimulation(){
        AtomicBoolean isStopped = new AtomicBoolean(false);
        stopBtn.setOnAction(actionEvent -> {
            isStopped.set(true);
            System.out.println("STOP SIMULATION RIGHT NOW!!!!!!!!!");
        });
        return isStopped.get();
    }*/

    @FXML
    private GridPane gridPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedLabel;

    @FXML
    void initialize() {
        exitBtn.setOnAction(actionEvent -> {
            System.exit(0);
        });


        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            speed = newValue.intValue();
        });

        people = new ArrayList<>();
        this.days = 30;
        random = new Random();

        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            people.add(new Human());
        }

        /*/ Инфицируем начальное количество людей                               //начальное колво больных
        for (int i = 0; i < 5; i++) {
            people.get(random.nextInt(GRID_SIZE * GRID_SIZE)).bornInfect();
        }*/

        people.get(GRID_SIZE*GRID_SIZE/2 + 25).bornInfect();

        updateGrid(gridPane);

        new Thread(() ->{
            while(true) {
                update(gridPane, people);
                Platform.runLater(() -> updateGrid(gridPane));
                try{
                    Thread.sleep(speed);             //менять скорость симуляции
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void update(GridPane grid, List<Human> people) {
        int i, j;
        for (i = 0; i < GRID_SIZE; i++) {
            for (j = 0; j < GRID_SIZE; j++) {
                Human person = people.get(i * GRID_SIZE + j);
                if (person.infectedIsNear(people, i, j)) {
                    person.infect();
                }
                if (person.getState() == Human.State.INFECTED) {
                    int randIndex = person.randHumanFromRadius(5, i, j);       //менять радиус рандомного заражения
                    Human farPerson = people.get(randIndex);
                    farPerson.infect();
                    farPerson.update();
                }
                person.update();
            }
        }
    }

    private void updateGrid(GridPane grid) {
        grid.getChildren().clear();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Human person = people.get(i * GRID_SIZE + j);
                Rectangle rect = new Rectangle(HUMAN_SIZE, HUMAN_SIZE);

                switch (person.getState()) {
                    case HEALTHY:
                        switch(person.getImmunity()){
                            case 0:
                                rect.setFill(Color.rgb(255, 213, 0));
                                break;
                            case 1:
                                rect.setFill(Color.rgb(255, 250, 107));
                                break;
                            case 2:
                                rect.setFill(Color.rgb(255, 254, 209));
                                break;
                            case 3:
                                rect.setFill(Color.WHITE);
                                break;
                        }
                        break;
                    case INFECTED:
                        rect.setFill(Color.rgb(255, 68, 0));
                        break;
                    case RECOVERED:
                        rect.setFill(Color.rgb(85, 255, 0));
                        break;
                    case DEAD:
                        rect.setFill(Color.BLACK);
                        break;
                }

                grid.add(rect, j, i);
            }
        }
    }
}