package ua.hudyma.maze;

import java.util.ArrayList;

import static ua.hudyma.hero.characters.Hero.*;

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
        sigmar.setCurPosX(0);
        sigmar.setCurPosY(0);
        grungi.setCurPosX(0);
        grungi.setCurPosY(Y - 1);
        ladril.setCurPosX(X - 1);
        ladril.setCurPosY(0);
        zoltar.setCurPosX(X - 1);
        zoltar.setCurPosY(Y - 1);

    }

}
