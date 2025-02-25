package ua.hudyma.maze;

import ua.hudyma.room.Room;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;
import static ua.hudyma.util.InheritorsFinder.retrieveAllClassInstances;

public abstract class Maze {
    protected int X, Y;
    protected char[][] mazeArray;
    public void fillMazeArray(){
        var array = getMazeArray();
        for (char[] chars : array) {
            Arrays.fill(chars, '.');
        }
        setMazeArray(array);
    }

    public void imprintRoomIntoMazeArray(Room room) {
        var mazeArray = getMazeArray();
        var roomArray = room.getRoomArray();
        for (int i = room.getPositionX() - 1; i < room.getDimensionX(); i++) {
            for (int j = room.getPositionY() - 1; j < room.getDimensionY(); j++){
                mazeArray[i + room.getPositionX()][j + room.getPositionY()] = roomArray[i][j];
            }
        }
        setMazeArray(mazeArray);
    }

    public void viewMazeArray(){
        var array = getMazeArray();
        for (int i = 0; i < getX(); i++) {
            out.print(i < 10 ? i + "  " : i + " ");
            for (int j = 0; j < getY(); j++) {
                out.print(array[i][j] + "  ");
            }
            out.println();
        }
        out.print("  ");
        for (int i = 0; i < getY(); i++) {
            out.print(i <= 10 ? " " + i + " " : i + " ");
        }
        out.println();
    }

    protected List<Room> rooms;
    protected String name, legend;
    protected boolean needToFindPerson, needToFindTreasure, needToFindExit;
    protected int reward;
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public String getName() {
        return name;
    }

    public String getLegend() {
        return legend;
    }

    public boolean isNeedToFindPerson() {
        return needToFindPerson;
    }

    public void setNeedToFindPerson(boolean needToFindPerson) {
        this.needToFindPerson = needToFindPerson;
    }

    public boolean isNeedToFindTreasure() {
        return needToFindTreasure;
    }

    public void setNeedToFindTreasure(boolean needToFindTreasure) {
        this.needToFindTreasure = needToFindTreasure;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }
    public int getY() {
        return Y;
    }
    public void setY(int y) {
        Y = y;
    }
    public char[][] getMazeArray() {
        return mazeArray;
    }

    public void setMazeArray(char[][] mazeArray) {
        this.mazeArray = mazeArray;
    }
}
