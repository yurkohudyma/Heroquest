package ua.hudyma.hero.characters;

import ua.hudyma.hero.Magic;
import ua.hudyma.hero.Races;
import ua.hudyma.hero.weaponry.attack.Weapon;
import ua.hudyma.hero.weaponry.defence.Armour;
import ua.hudyma.maze.Maze;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero {
    public static Hero sigmar, grungi, ladril, zoltar;
    public boolean assertHeroCurrentPositionInitialised() {
        return getCurPosX() != -1 && getCurPosY() != -1;
    }
    protected int livePoints, mindPoints, curPosX, curPosY, balance;
    protected String legend;
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

    public void moveDown (int steps, Hero hero){
        var mazeArray = Maze.getMazeArray();
        mazeArray[getCurPosX()][getCurPosY()] = '.';
        mazeArray[getCurPosX() + steps][getCurPosY()] = getIcon();
        Maze.setMazeArray(mazeArray);
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
}