package ua.hudyma.hero.characters;

import ua.hudyma.hero.Magic;
import ua.hudyma.hero.Races;
import ua.hudyma.hero.weaponry.attack.Weapon;
import ua.hudyma.hero.weaponry.defence.Armour;
import ua.hudyma.maze.Maze;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static ua.hudyma.Main.printDelimiter;
import static ua.hudyma.maze.Maze.setMazeArray;

public abstract class Hero {
    public static Hero sigmar, grungi, ladril, zoltar;

    public boolean assertHeroCurrentPositionInitialised() {
        return getCurPosX() != -1 && getCurPosY() != -1;
    }

    public enum Movement {
        UP, DOWN, RIGHT, LEFT;
    }

    protected int livePoints, mindPoints, curPosX, curPosY, balance;
    protected String legend, name;
    protected Races race;
    protected Magic.Nature magicNature;
    protected List<Weapon> weapons;
    protected List<Armour> armours;
    protected static List<Hero> heroList;
    boolean hasToolkit;

    public static void configureHeroes() {
        sigmar = new Barbarian();
        grungi = new Dwarf();
        ladril = new Elf(Magic.Nature.AIR);
        zoltar = new Wizard(Magic.Nature.FIRE);
        heroList = new ArrayList<>(List.of(sigmar, grungi, ladril, zoltar));
        sigmar.setBalance(200);
        grungi.setBalance(150);
        ladril.setBalance(100);
        zoltar.setBalance(150);
    }

    protected char icon;

    public static void moveDown(int steps, Hero hero) {
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.DOWN)) {
            proceedVerticalMovement(mazeArray, hero, steps, Movement.DOWN);
        }
    }

    public static void moveUp(int steps, Hero hero) {
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.UP)) { //todo implement
            proceedVerticalMovement(mazeArray, hero, steps, Movement.UP);
        }
    }

    public static void moveLeft(int steps, Hero hero) {
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.LEFT)) { //todo implement
            proceedHorizontalMovement(mazeArray, hero, steps, Movement.LEFT);
        }
    }

    public static void moveRight (int steps, Hero hero){
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.RIGHT)) { //todo implement
            proceedHorizontalMovement(mazeArray, hero, steps, Movement.RIGHT);
        }
    }

    private static void proceedHorizontalMovement(char[][] mazeArray, Hero hero, int steps, Movement direction) {
        var heroPosX = hero.getCurPosX();
        var heroPosY = hero.getCurPosY();
        mazeArray[heroPosX][heroPosY] = '.';
        mazeArray[heroPosX][heroPosY + steps] = hero.getIcon();
        setMazeArray(mazeArray);
        out.println(hero.getName() + " moved " + direction + " " + steps + " steps to (" + steps + " : " + heroPosY + ")");
    }

    private static void proceedVerticalMovement(char[][] mazeArray, Hero hero, int steps, Movement direction) {
        var heroPosX = hero.getCurPosX();
        var heroPosY = hero.getCurPosY();
        mazeArray[heroPosX][heroPosY] = '.';
        mazeArray[heroPosX + steps][heroPosY] = hero.getIcon();
        setMazeArray(mazeArray);
        out.println(hero.getName() + " moved " + direction + " " + steps + " steps to (" + steps + " : " + heroPosY + ")");
    }

    private static boolean checkMovementPossibility(int steps, char[][] mazeArray,
                                                    Hero hero, Movement direction) {
        switch (direction) {
            case DOWN -> {
                if (hero.getCurPosX() + steps > mazeArray.length) {
                    printDelimiter();
                    out.println("Position (" + steps + " : " + hero.getCurPosY() + ")" +
                            " leads outside the current maze");
                    printDelimiter();
                    return false;
                }
                for (int x = hero.getCurPosX() + 1; x <= steps; x++) {
                    if (mazeArray[x][hero.getCurPosY()] != '.') {
                        printDelimiter();
                        out.println("Position (" + x + " : " + hero.getCurPosY() + ") is taken by "
                                + mazeArray[x][hero.getCurPosY()]);
                        printDelimiter();
                        return false;
                    }
                }
                return true;
            }
            case UP -> {
                if (hero.getCurPosX() - steps < 0) {
                    printDelimiter();
                    out.println("Position (" + steps + " : " + hero.getCurPosY() + ")" +
                            " leads outside the current maze");
                    printDelimiter();
                    return false;
                }
                for (int x = hero.curPosX - 1; x > -steps; x--) {
                    if (mazeArray[x][hero.getCurPosY()] != '.') {
                        printDelimiter();
                        out.println("Position (" + x + " : " + hero.getCurPosY() + ") is taken by "
                                + mazeArray[x][hero.getCurPosY()]);
                        printDelimiter();
                        return false;
                    }
                }
                return true;
            }
            case LEFT -> {
                if (hero.getCurPosY() - steps < 0) {
                    printDelimiter();
                    out.println("Position (" + hero.getCurPosX() + " : " + steps + ")" +
                            " leads outside the current maze");
                    printDelimiter();
                    return false;
                }
                for (int y = hero.curPosY - 1; y > -steps; y--) {
                    if (mazeArray[hero.getCurPosX()][y] != '.') {
                        printDelimiter();
                        out.println("Position (" + hero.getCurPosX() + " : " + y + ") is taken by "
                                + mazeArray[hero.getCurPosX()][y]);
                        printDelimiter();
                        return false;
                    }
                }
                return true;
            }
            case RIGHT -> {
                if (hero.getCurPosY() + steps > mazeArray[0].length) {
                    printDelimiter();
                    out.println("Position (" + hero.getCurPosX() + " : " + steps + ")" +
                            " leads outside the current maze");
                    printDelimiter();
                    return false;
                }
                for (int y = hero.curPosY + 1; y < steps; y++) {
                    if (mazeArray[hero.getCurPosX()][y] != '.') {
                        printDelimiter();
                        out.println("Position (" + hero.getCurPosX() + " : " + y + ") is taken by "
                                + mazeArray[hero.getCurPosX()][y]);
                        printDelimiter();
                        return false;
                    }
                }
                return true;
            }
            default -> throw new IllegalArgumentException("unknown direction");
        }
    }

    protected Hero() {
        this.armours = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.curPosX = -1;
        this.curPosY = -1;
    }

    protected Hero(int curPosX, int curPosY) {
        this.armours = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.curPosX = curPosX;
        this.curPosY = curPosY;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean hasToolkit() {
        return hasToolkit;
    }

    public void setHasToolkit(boolean hasToolkit) {
        this.hasToolkit = hasToolkit;
    }

    public int getAttack() {
        return livePoints;
    }

    public void setAttack(int attack) {
        this.livePoints = attack;
    }

    public int getDefence() {
        return mindPoints;
    }

    public void setDefence(int defence) {
        this.mindPoints = defence;
    }

    public Races getRace() {
        return race;
    }

    public Magic.Nature getMagicNature() {
        return this instanceof Magic ? magicNature : Magic.Nature.NA;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Armour> getArmours() {
        return armours;
    }

    public void setArmours(List<Armour> armours) {
        this.armours = armours;
    }

    public int getCurPosX() {
        return curPosX;
    }

    public void setCurPosX(int curPosX) {
        this.curPosX = curPosX;
    }

    public int getCurPosY() {
        return curPosY;
    }

    public void setCurPosY(int curPosY) {
        this.curPosY = curPosY;
    }

    public char getIcon() {
        return icon;
    }

    public static List<Hero> getHeroList() {
        return heroList;
    }

    public String getName() {
        return name;
    }
}