package main;
import javafx.scene.layout.GridPane;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static utils.Constants.GRID_SIZE;

class Human {
    enum State {HEALTHY, INFECTED, RECOVERED, DEAD}

    private State state;
    private int daysInfected;
    private int daysRecovered;
    private int immunity;
    private Random random;


    public Human() {
        this.state = State.HEALTHY;
        this.daysInfected = 0;
        random = new Random();
        this.immunity = random.nextInt(4);
    }

    public State getState() {
        return state;
    }
    public int getImmunity() { return immunity; }

    public void bornInfect(){
        if (state == State.HEALTHY){
            state = State.INFECTED;
            this.immunity = 0;
        }
    }

    public void infect() {                      //Менять вероятность заражения
        switch(this.immunity){
            case 0:
                if (random.nextDouble() < 0.5) {state = State.INFECTED;}
                break;
            case 1:
                if (random.nextDouble() < 0.25) {state = State.INFECTED;}
                this.immunity--;
                break;
            case 2:
                if (random.nextDouble() < 0.125) {state = State.INFECTED;}
                this.immunity--;
                break;
            case 3:
                if (random.nextDouble() < 0.0625) {state = State.INFECTED;}
                this.immunity--;
                break;
        }
    }

    public void recover() {
        if (state == State.INFECTED) {
            if (random.nextDouble() < 0.03) {
                state = State.DEAD;
            }
            else {
                state = State.RECOVERED;
                this.immunity = random.nextInt(4);
            }
        }
    }

    public void update() {
        if (state == State.INFECTED) {
            daysInfected++;
            if (daysInfected > 5) {         //менять колво дней болезни до выздоровления
                recover();
            }
        }
    }

    public boolean infectedIsNear(List<Human> people, int i, int j){
        if (this.getState() == State.HEALTHY) {
            if (j > 0) {
                Human HumanMinX = people.get(i * GRID_SIZE + j - 1);
                if (HumanMinX.state == State.INFECTED) return true;
            }

            if (i > 0) {
                Human HumanMinY = people.get((i - 1) * GRID_SIZE + j);
                if (HumanMinY.state == State.INFECTED) return true;
            }

            if (j < GRID_SIZE - 1) {
                Human HumanPlsX = people.get(i * GRID_SIZE + j + 1);
                if (HumanPlsX.state == State.INFECTED) return true;
            }

            if (i < GRID_SIZE - 1) {
                Human HumanPlsY = people.get((i + 1) * GRID_SIZE + j);
                if (HumanPlsY.state == State.INFECTED) return true;
            }
        }
        return false;
    }

    public int randHumanFromRadius(int r, int i, int j){
        int board = random.nextInt(-r - 1, r + 1);
        i += board;
        if (i < 0) i = 0;
        else if (i > 49) i = 49;
        int temp = random.nextInt(2);
        if (temp == 0) temp = -1;
        temp = temp * (r - abs(board));
        j += temp;
        if (j < 0) j = 0;
        int res = i * GRID_SIZE + j;

        if (res < GRID_SIZE * GRID_SIZE) return res;
        else return GRID_SIZE * GRID_SIZE - 1;
    }
}