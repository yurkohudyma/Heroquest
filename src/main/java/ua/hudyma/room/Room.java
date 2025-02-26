package ua.hudyma.room;

import ua.hudyma.room.caches.Cache;
import ua.hudyma.room.caches.Treasure;
import ua.hudyma.room.caches.potions.Potion;
import ua.hudyma.room.caches.potions.Resilience;
import ua.hudyma.room.caches.potions.Speed;
import ua.hudyma.room.monsters.ChaosWarrior;
import ua.hudyma.room.monsters.Monster;
import ua.hudyma.room.monsters.Skeleton;
import ua.hudyma.room.traps.CrossBoltTrap;
import ua.hudyma.room.traps.MonsterTrap;
import ua.hudyma.room.traps.PitTrap;
import ua.hudyma.room.traps.Trap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;
import static ua.hudyma.Main.*;

public class Room {
    protected List<Door> doors;
    protected List<Trap> traps;
    protected List<Cache> caches;
    protected List<Monster> monsters;
    public char [][] roomArray;
    private AtomicInteger localRoomCounter;
    protected int dimensionX, dimensionY, positionX, positionY;

    public static Room prepareRoom(int x, int y, int posX, int posY) throws ClassNotFoundException {
        Room room = new Room(x, y, posX, posY);
        room.fillRoomArray();
        room.addDoor(1,y - 1,true);
        room.addDoor(x - 1,y / 2, false);
        room.addMonsters(new ChaosWarrior(1,1), new Skeleton(2,2));
        room.unveilCaches(room);
        room.viewMonsters();
        room.viewRoomArray();
        printDelimiter();
        return room;
    }

    private void unveilCaches(Room room) throws ClassNotFoundException {
        var treasure = room.roomHasTreasures() ? new Treasure() : null;
        if (treasure != null) {
            out.println("Room " + room.getLocalRoomCounter() + " has treasure: " + treasure.getCache());
            var treasures = room.getCaches();
            treasures.add(treasure);
            setCaches(treasures);
        } else {
            out.println("Room " + room.getLocalRoomCounter() + " has no treasures");
            if (Potion.roomHasPotions()) {
                Potion potion = null;
                var room1Potion = Potion.generateRandomLotion();
                if (room1Potion instanceof Speed) {
                    potion = new Speed();
                } else if (room1Potion instanceof Resilience) {
                    potion = new Resilience();
                }
                assert potion != null;
                var cacheList = room.getCaches();
                cacheList.add(potion);
                //room.setCaches(cacheList);
                out.println("Room " + room.getLocalRoomCounter() + " has " + getSimpleName(potion) + " Potion");
            }
            else {
                out.println("Room " + room.getLocalRoomCounter() + " has no Potions");
                unveilTraps(room);
            }
        }
    }

    private void unveilTraps(Room room) {
        if (room.roomHasTraps()) {
            Trap roomTrap = null;
            var trap = Trap.generateTrap();
            if (trap instanceof PitTrap) {
                roomTrap = new PitTrap();
                calloutForRoomTrap(room, roomTrap);
            } else if (trap instanceof MonsterTrap) {
                roomTrap = new MonsterTrap();
                Monster monster = ((MonsterTrap) roomTrap).getMonster();
                out.println("Room " + room.getLocalRoomCounter() + " has " + getSimpleName(roomTrap) + " " + getSimpleName(monster));
            } else if (trap instanceof CrossBoltTrap) {
                roomTrap = new CrossBoltTrap();
                calloutForRoomTrap(room, roomTrap);
            }
            assert roomTrap != null;
            var trapList = room.getTraps();
            trapList.add(roomTrap);
            room.setTraps(trapList);
        } else out.println("Room " + room.getLocalRoomCounter() + " has no Traps");
    }

    private static void calloutForRoomTrap(Room room, Trap roomTrap) {
        out.println("Room " + room.getLocalRoomCounter() + " has " + getSimpleName(roomTrap));
    }

    public void addTraps(Room room, Trap... trap) {
        var traps = room.getTraps();
        traps.addAll(List.of(trap));
        room.setTraps(traps);
        var array = getRoomArray();
        for (Trap t : trap){
            array[t.getX()][t.getY()] = t.icon;
        }
        room.setRoomArray(array);
    }

    public void addDoor(int x, int y, boolean isSecret) {
        Door door = isSecret ? new SecretDoor(x, y) : new Door(x,y);
        var doorList = getDoors();
        doorList.add(door);
        var array = getRoomArray();
        array[door.getX()][door.getY()] = door.icon;
        setDoors(doorList);
    }

    public void addMonsters(Monster... monster) {
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
    public void fillRoomArray(){
        var array = getRoomArray();
        for (char[] chars : array) {
            Arrays.fill(chars, '.');
        }
        for (int i = 0; i < getDimensionX(); i++){
            for (int j = 0; j < getDimensionY(); j++){
                array[i][0] = '|';
                array[0][j] = '_';
                array[getDimensionX() - 1][j] = '_';
                array[i][getDimensionY() - 1] = '|';
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
        doors = new ArrayList<>();
        traps = new ArrayList<>();
        caches = new ArrayList<>();
        monsters = new ArrayList<>();
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