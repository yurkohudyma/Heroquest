package ua.hudyma.hero.characters;

import ua.hudyma.hero.Magic;
import ua.hudyma.hero.Races;
import ua.hudyma.hero.weaponry.attack.Weapon;
import ua.hudyma.hero.weaponry.defence.Armour;
import ua.hudyma.maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;
import static ua.hudyma.Main.printDelimiter;
import static ua.hudyma.maze.Maze.setMazeArray;

public abstract class Hero {
    public static Hero sigmar, grungi, ladril, zoltar;

    public static List<Character> trespassableIconsList = List.of('▦', '□', '.');

    public static int getRandomSteps() {
        return new Random().nextInt(13) + 1;
    }

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
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.UP)) {
            proceedVerticalMovement(mazeArray, hero, steps, Movement.UP);
        }
    }

    public static void moveLeft(int steps, Hero hero) {
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.LEFT)) {
            proceedHorizontalMovement(mazeArray, hero, steps, Movement.LEFT);
        }
    }

    public static void moveRight(int steps, Hero hero) {
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.RIGHT)) {
            proceedHorizontalMovement(mazeArray, hero, steps, Movement.RIGHT);
        }
    }

    private static void proceedHorizontalMovement(char[][] mazeArray, Hero hero, int steps, Movement direction) {
        var heroPosX = hero.getCurPosX();
        var heroPosY = hero.getCurPosY();
        var moveIncrement = heroPosY + steps;
        mazeArray[heroPosX][heroPosY] = '.';
        mazeArray[heroPosX][moveIncrement] = hero.getIcon();
        hero.setCurPosY(moveIncrement);
        setMazeArray(mazeArray);
        out.println(hero.getName() + " (" +  hero.getIcon() + ") moved " + direction + " " + steps + " steps to (" + heroPosX + " : " + hero.getCurPosY() + ")");
    }

    private static void proceedVerticalMovement(char[][] mazeArray, Hero hero, int steps, Movement direction) {
        var heroPosX = hero.getCurPosX();
        var heroPosY = hero.getCurPosY();
        var moveIncrement = heroPosX + steps;
        mazeArray[heroPosX][heroPosY] = '.';
        mazeArray[moveIncrement][heroPosY] = hero.getIcon();
        hero.setCurPosX(moveIncrement);
        setMazeArray(mazeArray);
        out.println(hero.getName() + " (" +  hero.getIcon() + ") moved " + direction + " " + steps + " steps to (" + moveIncrement + " : " + heroPosY + ")");
    }

    private static boolean checkMovementPossibility(int steps, char[][] mazeArray,
                                                    Hero hero, Movement direction) {
        var heroesIconsCharList = getHeroIconsCharList();
        int x,y;
        switch (direction) {
            case DOWN -> {
                if (hero.getCurPosX() + steps > mazeArray.length - 1) {
                    calloutForOutsideMoveVertical(steps, hero);
                    return false;
                }

                for (x = hero.getCurPosX() + 1; x < hero.getCurPosX() + steps; x++) {
                    if (trespassableIconsList.contains(mazeArray[x][hero.getCurPosY()]) ||
                       heroesIconsCharList.contains(mazeArray[x][hero.getCurPosY()])) {
                       return true;
                    }
                }
                calloutForTakenPositionVertical(mazeArray, hero, x);
                return false;

            }
            case UP -> {
                if (hero.getCurPosX() + steps < 0) {
                    calloutForOutsideMoveVertical(steps, hero);
                    return false;
                }
                for (x = hero.curPosX - 1; x > hero.curPosX + steps; x--) {
                    if (trespassableIconsList.contains(mazeArray[x][hero.getCurPosY()]) ||
                        heroesIconsCharList.contains(mazeArray[x][hero.getCurPosY()])) {
                        return true;
                    }
                }
                calloutForTakenPositionVertical(mazeArray, hero, x);
                return false;
            }
            case LEFT -> {
                if (hero.getCurPosY() + steps < 0) {
                    calloutForOutsideMoveHorizontal(steps, hero);
                    return false;
                }
                for (y = hero.getCurPosY() - 1; y > hero.curPosY + steps; y--) {
                    if (trespassableIconsList.contains(mazeArray[hero.getCurPosX()][y]) ||
                        heroesIconsCharList.contains(mazeArray[hero.getCurPosX()][y])) {
                        return true;
                    }
                }
                calloutForTakenPositionHorizontal(mazeArray, hero, y);
                return false;
            }
            case RIGHT -> {
                if (hero.getCurPosY() + steps > mazeArray[0].length - 1) {
                    calloutForOutsideMoveHorizontal(steps, hero);
                    return false;
                }
                for (y = hero.curPosY + 1; y < hero.curPosY + steps; y++) {
                    if (trespassableIconsList.contains(mazeArray[hero.getCurPosX()][y]) ||
                        heroesIconsCharList.contains(mazeArray[hero.getCurPosX()][y])) {
                        return true;
                    }
                }
                calloutForTakenPositionHorizontal(mazeArray, hero, y);
                return false;
            }
            default -> throw new IllegalArgumentException("unknown direction");
        }
    }

    private static void calloutForOutsideMoveVertical(int steps, Hero hero) {
        printDelimiter();
        out.println(hero.getName() + " (" +  hero.getIcon() + "): Position (" + steps + " : " + hero.getCurPosY() + ")" +
                " leads outside the current maze");
        printDelimiter();
    }

    private static void calloutForOutsideMoveHorizontal(int steps, Hero hero) {
        printDelimiter();
        out.println(hero.getName() + " (" +  hero.getIcon() +") : Position (" + hero.getCurPosX() + " : " + steps + ")" +
                " leads outside the current maze");
        printDelimiter();
    }

    private static void calloutForTakenPositionHorizontal(char[][] mazeArray, Hero hero, int y) {
        printDelimiter();
        out.println(hero.getName() + " (" +  hero.getIcon() + ") : Position (" + hero.getCurPosX() + " : " + y + ") is taken by "
                + mazeArray[hero.getCurPosX()][y]);
        printDelimiter();
    }

    private static void calloutForTakenPositionVertical(char[][] mazeArray, Hero hero, int x) {
        printDelimiter();
        out.println(hero.getName() + " (" +  hero.getIcon() + ") : Position (" + x + " : " + hero.getCurPosY()  + ") is taken by "
                + mazeArray[x][hero.getCurPosY()]);
        printDelimiter();
    }

    private static List<Character> getHeroIconsCharList() {
        var sb = new StringBuilder();
        getHeroList()
                .stream()
                .map(Hero::getIcon)
                .forEach(sb::append);
        return sb
                .chars()
                .mapToObj(c -> (char) c)
                .toList();
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

    @Deprecated
    public static void configureHeroesDepr() {

        /*sigmar = new Barbarian();
        grungi = new Dwarf();
        ladril = new Elf(Nature.AIR);
        zoltar = new Wizard(Nature.FIRE);*/

        /*List<Weapon> sigmarWeapons = sigmar.getWeapons();
        List<Armour> sigmarArmours = sigmar.getArmours();

        sigmarWeapons.add(new BroadSword());
        sigmar.setWeapons(sigmarWeapons);

        sigmarArmours.add(new PlateArmour());
        sigmar.setArmours(sigmarArmours);

        List<Weapon> grungiWeapons = grungi.getWeapons();
        List<Armour> grungiArmours = grungi.getArmours();
        grungiWeapons.add(new HandAxe());
        grungiArmours.add(new Shield());
        grungi.setWeapons(grungiWeapons);
        grungi.setArmours(grungiArmours);

        List<Weapon> ladrilWeapons = ladril.getWeapons();
        List<Armour> ladrilArmours = ladril.getArmours();
        ladrilWeapons.add(new CrossBow());
        ladrilArmours.add(new ChainMail());
        ladril.setWeapons(ladrilWeapons);
        ladril.setArmours(ladrilArmours);

        List<Weapon> zoltarWeapons = zoltar.getWeapons();
        List<Armour> zoltarArmours = zoltar.getArmours();
        zoltarWeapons.add(new Staff());
        zoltar.setWeapons(zoltarWeapons);
        zoltarArmours.add(new Helmet());
        zoltar.setArmours(zoltarArmours);*/

        /*sigmar.setBalance(200);
        grungi.setBalance(150);
        ladril.setBalance(100);
        zoltar.setBalance(150);*/

        //viewAllStaff(sigmarWeapons, sigmarArmours, grungiWeapons, grungiArmours, ladrilWeapons, ladrilArmours, zoltarWeapons, zoltarArmours);

        ///////////////////////////////
        ///HEROES SETUP END
        ///////////////////////////////
    }
}