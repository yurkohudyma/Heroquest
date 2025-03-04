package ua.hudyma.hero.characters;

import ua.hudyma.exceptions.MonsterNotFoundInRoomException;
import ua.hudyma.exceptions.NoRoomRecognitionException;
import ua.hudyma.hero.Magic;
import ua.hudyma.hero.Races;
import ua.hudyma.hero.weaponry.attack.Weapon;
import ua.hudyma.hero.weaponry.defence.Armour;
import ua.hudyma.maze.Maze;
import ua.hudyma.room.Room;
import ua.hudyma.room.monsters.Monster;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;
import static ua.hudyma.Main.*;
import static ua.hudyma.maze.Maze.*;
import static ua.hudyma.room.monsters.Monster.getIconMonsterMap;

public abstract class Hero {
    public static Hero sigmar, grungi, ladril, zoltar;

    protected static Weapon activeWeapon;

    protected static Armour activeArmour;

    public static List<Character> trespassableIconsList = List.of('▦', '□', '.');

    public static int getRandomSteps() {
        return new Random().nextInt(13) + 1;
    }

    public static void attackUp(int steps, Hero hero) throws MonsterNotFoundInRoomException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Room room = recogniseHeroLocationRoom(hero);
        var monsterIcon = mazeArray[hero.getCurPosX() + steps][hero.getCurPosY()];
        assertMonstersInRoom(room, monsterIcon);
        var monster = getIconMonsterMap().get(monsterIcon);

        //hero's attack phase

        //todo randomize attack and defence skills, taking into account natural skills and armoury
        var monsterKilled = false;
        var weaponAttackBoost = activeWeapon == null ? 0 : activeWeapon.getAttackBoost();
        var overallHeroAttackValue = (new Random().nextInt(hero.getAttack()) + 1) + weaponAttackBoost;
        out.println(hero.getName() + "'s attack value is " + overallHeroAttackValue);
        var overallMonsterDefenceValue = monster.getDefence();
        out.println(getSimpleName(monster) + "'s defence value is " + overallMonsterDefenceValue);
        var attackPointsDifference = overallHeroAttackValue - overallMonsterDefenceValue;
        if (attackPointsDifference == 0) {
            out.println("The attack has been duly responded : " + overallHeroAttackValue + " : " + overallMonsterDefenceValue);
        } else if (attackPointsDifference < 0) {
            out.println("The strike has not been successful : " + overallHeroAttackValue + " : " + overallMonsterDefenceValue);
        } else if (attackPointsDifference < monster.getDefence()) {
            out.println(hero.getName() + " has wounded the monster : " + overallHeroAttackValue + " : " + overallMonsterDefenceValue);
            monster.setEndurance(attackPointsDifference);
        } else if (attackPointsDifference >= monster.getDefence()){
            out.println(getSimpleName(monster) + " has been killed");
            monsterKilled = true;
            //todo checkout monster from the room
            var roomMonsters = room.getMonsters();
            var mon = correlateAndGetMonsterInstance(monster, roomMonsters);
            var monsterUncheckedFromTheRoom = roomMonsters.remove(mon);
            mazeArray[hero.getCurPosX() + steps][hero.getCurPosY()] = 'x';
            out.println(roomMonsters);
        }

        //monster's counterattack phase (if alive)
        if (!monsterKilled){
            var heroArmours = hero.getArmours();
            out.println("Monster fights back!!!");
        }




    }

    private static boolean assertMonstersInRoom(Room room, char monsterIcon) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, MonsterNotFoundInRoomException {
        assertAttackedMonsterIconIsOfMonsterPool(monsterIcon);
        var monster = getIconMonsterMap().get(monsterIcon);
        var roomMonsters = room.getMonsters();
        if (correlateMonsterInstances(monster, roomMonsters)) return true;
        throw new MonsterNotFoundInRoomException("There is no monster at that place, search another one");
    }

    private static boolean correlateMonsterInstances(Monster monster, List<Monster> roomMonsters) {
        for (Monster mon : roomMonsters) {
            if (mon.getClass().asSubclass(Monster.class)
                    .isAssignableFrom(monster.getClass().asSubclass(Monster.class))) {
                return true;
            }
        }
        return false;
    }

    private static Monster correlateAndGetMonsterInstance(Monster monster, List<Monster> roomMonsters) {
        for (Monster mon : roomMonsters) {
            if (mon.getClass().asSubclass(Monster.class)
                    .isAssignableFrom(monster.getClass().asSubclass(Monster.class))) {
                return mon;
            }
        }
        return null;
    }

    private static void assertAttackedMonsterIconIsOfMonsterPool(char monsterIcon) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, MonsterNotFoundInRoomException {
        if (!Monster.getMonsterIconList().contains(monsterIcon)) {
            throw new MonsterNotFoundInRoomException("Monster icon " + monsterIcon + " has not been recognized as registered monster icon");
        }
    }

    public static Room recogniseHeroLocationRoom(Hero hero) {
        Maze maze = Maze.currentMaze;
        var roomList = maze.getRooms();
        for (Room room : roomList) {
            if (hero.getCurPosX() < room.getDimensionX() &&
                    hero.getCurPosX() > room.getPositionX() &&
                    hero.getCurPosY() < room.getDimensionY() &&
                    hero.getCurPosY() > room.getPositionY()) {
                return room;
            }
        }
        throw new NoRoomRecognitionException("could not locate room with hero coords");
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

    public static void moveDown(int steps, Hero hero) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.DOWN)) {
            proceedVerticalMovement(mazeArray, hero, steps, Movement.DOWN);
        }
    }

    public static void moveUp(int steps, Hero hero) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.UP)) {
            proceedVerticalMovement(mazeArray, hero, steps, Movement.UP);
        }
    }

    public static void moveLeft(int steps, Hero hero) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.LEFT)) {
            proceedHorizontalMovement(mazeArray, hero, steps, Movement.LEFT);
        }
    }

    public static void moveRight(int steps, Hero hero) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var mazeArray = Maze.getMazeArray();
        if (checkMovementPossibility(steps, mazeArray, hero, Movement.RIGHT)) {
            proceedHorizontalMovement(mazeArray, hero, steps, Movement.RIGHT);
        }
    }

    private static void proceedHorizontalMovement(char[][] mazeArray,
                                                  Hero hero, int steps, Movement direction) {
        var heroPosX = hero.getCurPosX();
        var heroPosY = hero.getCurPosY();
        var moveIncrement = heroPosY + steps;
        mazeArray[heroPosX][heroPosY] = '.';
        mazeArray[heroPosX][moveIncrement] = hero.getIcon();
        hero.setCurPosY(moveIncrement);
        setMazeArray(mazeArray);
        out.println(hero.getName() + " (" + hero.getIcon() + ") moved "
                + direction + " " + steps + " steps to (" + heroPosX + " : " + hero.getCurPosY() + ")");
    }

    private static void proceedVerticalMovement(char[][] mazeArray,
                                                Hero hero, int steps, Movement direction) {
        var heroPosX = hero.getCurPosX();
        var heroPosY = hero.getCurPosY();
        var moveIncrement = heroPosX + steps;
        mazeArray[heroPosX][heroPosY] = '.';
        mazeArray[moveIncrement][heroPosY] = hero.getIcon();
        hero.setCurPosX(moveIncrement);
        setMazeArray(mazeArray);
        out.println(hero.getName() + " (" + hero.getIcon() + ") moved "
                + direction + " " + steps + " steps to (" + moveIncrement + " : " + heroPosY + ")");
    }

    private static boolean checkMovementPossibility(int steps, char[][] mazeArray,
                                                    Hero hero, Movement direction) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var heroesIconsCharList = getHeroIconsCharList();
        int x, y;
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
        out.println(hero.getName() + " (" + hero.getIcon() + "): Position (" + steps + " : "
                + hero.getCurPosY() + ")" +
                " leads outside the current maze");
        printDelimiter();
    }

    private static void calloutForOutsideMoveHorizontal(int steps, Hero hero) {
        printDelimiter();
        out.println(hero.getName() + " (" + hero.getIcon() + ") : Position (" +
                hero.getCurPosX() + " : " + steps + ")" +
                " leads outside the current maze");
        printDelimiter();
    }

    private static void calloutForTakenPositionHorizontal(char[][] mazeArray,
                                                          Hero hero, int y) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        printDelimiter();
        out.println(hero.getName() + " (" + hero.getIcon() + ") : Position ("
                + hero.getCurPosX() + " : " + y + ") is taken by "
                + getMonsterSimpleNameByIconPosition(mazeArray[hero.curPosX][y], hero, y));
        printDelimiter();
    }

    private static String getMonsterSimpleNameByIconPosition(char icon,
                                                             Hero hero, int coordinate)
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return getSimpleName(Monster.getMonsterByIcon(icon));
    }

    private static void calloutForTakenPositionVertical(char[][] mazeArray,
                                                        Hero hero, int x)
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        printDelimiter();
        out.println(hero.getName() + " (" + hero.getIcon() + ") : Position (" + x + " : "
                + hero.getCurPosY() + ") is taken by "
                + getMonsterSimpleNameByIconPosition(mazeArray[x][hero.curPosY], hero, x));
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

    public static Armour getActiveArmour() {
        return activeArmour;
    }

    public static void setActiveArmour(Armour activeArmour) {
        Hero.activeArmour = activeArmour;
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

    public static Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public static void setActiveWeapon(Weapon activeWeapon) {
        Hero.activeWeapon = activeWeapon;
    }
}