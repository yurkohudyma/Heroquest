package ua.hudyma.maze;

import java.util.ArrayList;

public class TheMaze01 extends Maze {
    public TheMaze01() {
        super.name = "The Maze";
        super.legend = "Should find the exit";
        super.X = 20;
        super.Y = 30;
        super.needToFindExit = true;
        super.reward = 100;
        super.mazeArray = new char[getX()][getY()];
        super.rooms = new ArrayList<>();
    }

}
