package main;

import java.util.Random;
import java.util.Set;

import main.mainWndCntrl;

public class Human {
    private int state; //0 - здоровый, 1 - больной, 2 - выздоровевший
    private int immunity; //0 - 2
    private int x;
    private int y;
    private int adr;
    private int direction;
    private static final int GRID_SIZE = 9;

    public Human(int state, int immunity, int x, int y){
        this.state = state;
        this.immunity = immunity;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getState() { return state; }

    public int getImmunity() { return immunity; }

    public void move(Set<String> occupiedPositions){

        int newX = x;
        int newY = y;
        int direction = new Random().nextInt(8);

        switch (direction) {
            case 0: // Up
                if (y > 0) newY--;
                break;
            case 1: // Down
                if (y < GRID_SIZE) newY++;
                break;
            case 2: // Left
                if (x > 0) newX--;
                else newX = GRID_SIZE;
                break;
            case 3: // Right
                if (x < GRID_SIZE) newX++;
                else newX = 0;
                break;
            case 4: // Top left
                if (y > 0 && x > 0) {
                    newY--;
                    newX--;
                }
                break;
            case 5: // Top right
                if (y > 0 && x < GRID_SIZE) {
                    newY--;
                    newX++;
                }
                break;
            case 6: // Bottom left
                if (y < GRID_SIZE && x > 0) {
                    newY++;
                    newX--;
                }
                break;
            case 7: // Bottom right
                if (y < GRID_SIZE && x < GRID_SIZE) {
                    newY++;
                    newX++;
                }
                break;
            default: break;
        }
        if (!occupiedPositions.contains(newX + "," + newY)){
            x = newX;
            y = newY;
        }
        /*else if(state > 0){
            state -=1;
        }*/
        newX = x;
        newY = y;
    }

    public String getPosition(){
        return x+","+y;
    }

    public boolean isSurrounded(Set<String> occupiedPositions) {
        int[][] directions = {
                {0, -1}, // Up
                {0, 1},  // Down
                {-1, 0}, // Left
                {1, 0}   // Right
        };

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < GRID_SIZE && newY >= 0 && newY < GRID_SIZE) {
                if (!occupiedPositions.contains(newX + "," + newY)) {
                    return false; // Found at least one free adjacent cell
                }
            }
        }

        return true; // All adjacent cells are occupied
    }
}
