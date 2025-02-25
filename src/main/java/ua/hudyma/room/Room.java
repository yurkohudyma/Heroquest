package ua.hudyma.room;

import ua.hudyma.Main;
import ua.hudyma.maze.Maze;
import ua.hudyma.room.caches.Cache;
import ua.hudyma.room.monsters.Monster;
import ua.hudyma.room.traps.Trap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;
import static ua.hudyma.Main.getSimpleName;
import static ua.hudyma.Main.roomCounter;

public class Room {
    protected List<Door> doors;
    protected List<Trap> traps;
    protected List<Cache> caches;
    protected List<Monster> monsters;
    public char [][] roomArray;
    private AtomicInteger localRoomCounter;
    protected int dimensionX, dimensionY, positionX, positionY;

    public void addDoor(int x, int y, boolean isSecret) {
        Door door = isSecret ? new SecretDoor(x, y) : new Door(x,y);
        var doorList = getDoors();
        doorList.add(door);
        var array = getRoomArray();
        array[door.getX()][door.getY()] = door.icon;
        setDoors(doorList);
    }

    public void addMonsters(Monster ... monster) {
        var monsterList = getMonsters();
        var array = getRoomArray();
        for (Monster m : monster){
            monsterList.add(m);
            array[m.getX()][m.getY()] = m.icon;
        }
        setRoomArray(array);
    }

    public void viewMonsters() {
        var monsterList = getMonsters();
        out.print(!monsterList.isEmpty() ? "This room contains monster(s): " : "");
        monsterList.forEach(e -> out.print (getSimpleName(e) + " "));
        out.println();
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }
    public int getPositionX() {
        return positionX;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    public AtomicInteger getLocalRoomCounter() {
        return localRoomCounter;
    }
    public char[][] getRoomArray() {
        return roomArray;
    }
    public void setRoomArray(char[][] roomArray) {
        this.roomArray = roomArray;
    }
    public void fillRoomArray (){
        var array = getRoomArray();
        for (char[] chars : array) {
            Arrays.fill(chars, '.');
        }
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                array[j][0] = '|';
                array[0][j] = '_';
                array[array.length - 1][j] = '_';
                array[i][array[i].length - 1] = '|';
            }
        }
        setRoomArray(array);
    }
    public void viewRoomArray(){
        var array = getRoomArray();
        for (int i = 0; i < array.length; i++) {
            out.print(i + " ");
            for (int j = 0; j < array[i].length; j++) {
                out.print(roomArray[i][j] + "  ");
            }
            out.println();
        }
        out.print("  ");
        for (int i = 0; i < getDimensionY(); i++) {
            out.print(i + "  ");
        }
        out.println();
    }
    public int getDimensionX() {
        return dimensionX;
    }
    public void setDimensionX(int x) {
        dimensionX = x;
    }
    public int getDimensionY() {
        return dimensionY;
    }
    public void setDimensionY(int y) {
        dimensionY = y;
    }
    public List<Door> getDoors() {
        return doors;
    }
    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }
    public List<Trap> getTraps() {
        return traps;
    }
    public void setTraps(List<Trap> traps) {
        this.traps = traps;
    }
    public List<Cache> getCaches() {
        return caches;
    }
    public void setCaches(List<Cache> caches) {
        this.caches = caches;
    }
    public List<Monster> getMonsters() {
        return monsters;
    }
    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }
    public Room(int dimensionX, int dimensionY, int positionX, int positionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.positionX = positionX;
        this.positionY = positionY;
        this.doors = new ArrayList<>();
        this.traps = new ArrayList<>();
        this.caches = new ArrayList<>();
        this.monsters = new ArrayList<>();
        roomArray = new char[dimensionX][dimensionY];
        localRoomCounter = new AtomicInteger(roomCounter.incrementAndGet());
    }


    public boolean roomHasTraps (){
        return generateBoolean();
    }
    public boolean roomHasTreasures (){
        return generateBoolean();
    }
    public static boolean generateBoolean(){
        return new Random().nextBoolean();
    }
}