package main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//import javax.swing.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static utils.Constants.*;
import static utils.Constants.HUMAN_SIZE;
import static utils.Constants.SPEED;
import static utils.Constants.RADIUS;
import static utils.Constants.CHANCE;


public class mainWndCntrl {
    private List<Human> people;
    private int days = 30;
    private int beginInfected = 1;
    private Random random;
    private AtomicBoolean running = new AtomicBoolean();
    private int speed = 500;
    private int radius = 5;
    private double chance = 0.5;
    private boolean stopClicked = false;

    @FXML
    private MenuItem exitBtn;
    @FXML
    private MenuItem stopBtnMenu;
    @FXML
    private GridPane gridPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedLabel;
    @FXML
    private Button stopBtn;
    @FXML
    private Button resumeBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Slider radiusSlider;
    @FXML
    private Slider chanceSlider;

    @FXML
    void initialize() {
        people = new ArrayList<>();
        random = new Random();

        exitBtn.setOnAction(actionEvent -> {
            System.exit(0);
        });

        stopBtn.setOnAction(actionEvent -> {
            running.set(false);
            this.stopClicked = true;
        });

        resumeBtn.setOnAction(actionEvent -> {
            if (!running.get()) {
                running.set(true);
                resumeBtn.setText("Resume");
                this.stopClicked = false;
                startSimulation();
            }
        });

        resetBtn.setOnAction(actionEvent -> {
            resumeBtn.setVisible(true);
            stopBtn.setVisible(true);
            resetBtn.setVisible(false);
            resetBtn.setDisable(true);

            people.clear();
            for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
                people.add(new Human());
            }

            newSimulation();
        });

        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            speed = 1000 - newValue.intValue();
        });

        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            radius = newValue.intValue();
        });

        chanceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++){
                people.get(i).setChance(newValue.doubleValue());
            }
        });

        newSimulation();

        //startSimulation();
    }

    public void startSimulation(){
        Thread simulationThread = new Thread(() ->{
            int day;
            for (day = 0; day < days; day++) {
                if (!running.get()) {
                    break;
                }
                update(gridPane, people);
                Platform.runLater(() -> updateGrid(gridPane));
                try{
                    Thread.sleep(speed);             //менять скорость симуляции
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            if (!this.stopClicked) {
                running.set(false);
                resumeBtn.setVisible(false);
                stopBtn.setVisible(false);
                resetBtn.setVisible(true);
                resetBtn.setDisable(false);
            }
        });
        simulationThread.start();

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
                    int randIndex = person.randHumanFromRadius(radius, i, j);       //менять радиус рандомного заражения
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

    public void newSimulation(){
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            people.add(new Human());
        }

        people.get((GRID_SIZE * GRID_SIZE+GRID_SIZE)/2).bornInfect();

        resumeBtn.setText("Start");
        speedSlider.setValue(SPEED);
        radiusSlider.setValue(RADIUS);
        chanceSlider.setValue(CHANCE);
        updateGrid(gridPane);
    }
}