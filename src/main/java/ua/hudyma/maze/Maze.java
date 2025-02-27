package ua.hudyma.maze;

import ua.hudyma.hero.characters.Hero;
import ua.hudyma.room.Room;
import ua.hudyma.room.monsters.ChaosWarrior;
import ua.hudyma.room.monsters.Fimir;
import ua.hudyma.room.traps.MonsterTrap;
import ua.hudyma.room.traps.PitTrap;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;
import static ua.hudyma.room.Room.prepareRoom;


public abstract class Maze {
    protected static int X, Y;
    protected static char[][] mazeArray;
    protected List<Room> rooms;
    protected String name, legend;
    protected boolean needToFindPerson, needToFindTreasure, needToFindExit;
    protected int reward;

    protected void fillMazeArray() {
        var array = getMazeArray();
        for (char[] chars : array) {
            Arrays.fill(chars, '.');
        }
        setMazeArray(array);
    }

    public static void configureMaze01() throws ClassNotFoundException {
        Hero.configureHeroes();
        Maze maze01 = new TheMaze01();
        maze01.fillMazeArray();
        Room room1hollow = prepareRoom(5, 5, 1, 1);
        Room room2 = prepareRoom(8, 6, room1hollow.getPositionX() + room1hollow.getDimensionX(),
                                              room1hollow.getPositionY());
        maze01.imprintRoomIntoMazeArray(room1hollow);
        maze01.imprintRoomIntoMazeArray(room2);
        var roomList = maze01.getRooms();
        roomList.addAll(List.of(room1hollow, room2));
        maze01.setRooms(roomList);
        maze01.imprintHeroIconIntoMazeArray();
        viewMazeArray();
    }

    public void configureMaze02() throws ClassNotFoundException, IOException {
        Room room1 = prepareRoom(4, 8, 1, 1);
        Room room2 = prepareRoom(5, 1, room1.getDimensionX() + 1, 1);
        room1.addTraps(room2, new PitTrap(2, 0), new MonsterTrap(4, 0));
        room2.addMonsters(new Fimir(4, 0), new ChaosWarrior(0, 0));
        Maze ragnarMaze = new RescueOfSirRagnar02();
        var roomsList = ragnarMaze.getRooms();
        roomsList.addAll(List.of(room1, room2));
        ragnarMaze.setRooms(roomsList);
    }

    protected void imprintHeroIconIntoMazeArray() throws ClassNotFoundException {
        var array = getMazeArray();
        var activeHeroes = Hero.getHeroList();
        for (Hero hero : activeHeroes) {
            if (hero == null) continue;
            var heroPosInit = hero.assertHeroCurrentPositionInitialised();
            if (heroPosInit) {
                array[hero.getCurPosX()][hero.getCurPosY()] = hero.getIcon();
            }
        }
        setMazeArray(array);
    }

    protected void imprintRoomIntoMazeArray(Room room) {
        char [][] mazeArray = getMazeArray(), roomArray = room.getRoomArray();
        int roomPosX = room.getPositionX(), roomPosY = room.getPositionY();
        int roomDimX = room.getDimensionX(), roomDimY = room.getDimensionY();
        for (int i = 0; i < roomDimX; i++) {
            System.arraycopy(roomArray[i], 0, mazeArray[i + roomPosX], roomPosY, roomDimY);
        }
        setMazeArray(mazeArray);
    }

    public static void viewMazeArray() {
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

    public static int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public static int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public static char[][] getMazeArray() {
        return mazeArray;
    }

    public static void setMazeArray(char[][] mazeArray) {
        mazeArray = mazeArray;
    }
}
