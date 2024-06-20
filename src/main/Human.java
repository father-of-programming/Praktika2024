package main;

import java.util.Random;

public class Human {
    private int state; //0 - здоровый, 1 - больной, 2 - выздоровевший
    private int immunity; //0 - 2
    private int x;
    private int y;
    private int adr;
    private int direction;
    private Random random;

    public Human(int state, int immunity, int x, int y){
        this.state = state;
        this.immunity = immunity;
        this.x = x;
        this.y = y;
        this.random = new Random();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(){
        int direction = random.nextInt(8);
        switch (direction) {
            case 0: // Up
                if (y > 0) y--;
                break;
            case 1: // Down
                if (y < 9) y++;
                break;
            case 2: // Left
                if (x > 0) x--;
                break;
            case 3: // Right
                if (x < 9) x++;
                break;
            case 4: // Top left
                if (y > 0 && x > 0) y--; x--;
                break;
            case 5: // Top right
                if (y > 0 && x < 9) y--; x++;
                break;
            case 6: // Bottom left
                if (y < 9 && x > 0) y++; x--;
                break;
            case 7: // Bottom right
                if (y < 9 && x < 9) y++; x++;
                break;
        }
    }
}
