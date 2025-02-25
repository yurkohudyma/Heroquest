package ua.hudyma;

import ua.hudyma.hero.Magic.Nature;
import ua.hudyma.hero.characters.*;
import ua.hudyma.hero.weaponry.attack.Weapon;
import ua.hudyma.hero.weaponry.defence.Armour;
import ua.hudyma.maze.Maze;
import ua.hudyma.maze.RescueOfSirRagnar02;
import ua.hudyma.maze.TheMaze01;
import ua.hudyma.room.Room;
import ua.hudyma.room.caches.Treasure;
import ua.hudyma.room.caches.potions.Potion;
import ua.hudyma.room.caches.potions.Resilience;
import ua.hudyma.room.caches.potions.Speed;
import ua.hudyma.room.monsters.ChaosWarrior;
import ua.hudyma.room.monsters.Fimir;
import ua.hudyma.room.monsters.Monster;
import ua.hudyma.room.monsters.Skeleton;
import ua.hudyma.room.traps.CrossBoltTrap;
import ua.hudyma.room.traps.MonsterTrap;
import ua.hudyma.room.traps.PitTrap;
import ua.hudyma.room.traps.Trap;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

public class Main {
    public static AtomicInteger roomCounter = new AtomicInteger();
    static Hero sigmar, grungi, ladril, zoltar;

    public static void main(String[] args) throws ClassNotFoundException {

        configureMaze01();
        //configureMaze02();
        //configureHeroes();

    }

    private static void configureMaze01() throws ClassNotFoundException {
        Maze maze01 = new TheMaze01();
        maze01.fillMazeArray();
        Room room1hollow = configureRoom(5,5, 1, 1);
        maze01.imprintRoomIntoMazeArray(room1hollow);
        var roomList = maze01.getRooms();
        roomList.add(room1hollow);
        maze01.setRooms(roomList);
        maze01.viewMazeArray();
    }

    private static void configureMaze02() throws ClassNotFoundException, IOException {
        Room room1 = configureRoom(4,8, 1, 1);
        Room room2 = configureRoom(5,1, room1.getDimensionX() + 1, 1);
        addTraps (room2, new PitTrap(2, 0), new MonsterTrap(4, 0));
        room2.addMonsters (new Fimir(4,0), new ChaosWarrior(0,0));
        Maze ragnarMaze = new RescueOfSirRagnar02();
        var roomsList = ragnarMaze.getRooms();
        roomsList.addAll(List.of(room1, room2));
        ragnarMaze.setRooms(roomsList);
    }



    private static void addTraps( Room room, Trap ... trap) {
        var traps = room.getTraps();
        traps.addAll(List.of(trap));
        room.setTraps(traps);
        var array = room.getRoomArray();
        for (Trap t : trap){
            array[t.getX()][t.getY()] = t.icon;
        }
        room.setRoomArray(array);
    }

    public static void configureHeroes() {

        sigmar = new Barbarian();
        grungi = new Dwarf();
        ladril = new Elf(Nature.AIR);
        zoltar = new Wizard(Nature.FIRE);

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

        sigmar.setBalance(200);
        grungi.setBalance(150);
        //grungi.setHasToolkit(true);
        ladril.setBalance(100);
        zoltar.setBalance(150);

        //viewAllStaff(sigmarWeapons, sigmarArmours, grungiWeapons, grungiArmours, ladrilWeapons, ladrilArmours, zoltarWeapons, zoltarArmours);

        ///////////////////////////////
        ///HEROES SETUP END
        ///////////////////////////////
    }

    private static Room configureRoom(int x, int y, int posX, int posY) throws ClassNotFoundException {
        Room room = new Room(x, y, posX, posY);
        room.fillRoomArray();
        room.addDoor(1,y - 1,true);
        room.addDoor(x - 1,y / 2, false);
        room.addMonsters(new ChaosWarrior(1,1), new Skeleton(3,2));
        unveilCaches(room);
        room.viewMonsters();
        room.viewRoomArray();
        printDelimiter();
        return room;
    }



    private static void unveilCaches(Room room) throws ClassNotFoundException {
        var treasure = room.roomHasTreasures() ? new Treasure() : null;
        if (treasure != null) {
            out.println("Room " + room.getLocalRoomCounter() + " has treasure: " + treasure.getCache());
            var treasures = room.getCaches();
            treasures.add(treasure);
            room.setCaches(treasures);
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
                room.setCaches(cacheList);
                out.println("Room " + room.getLocalRoomCounter() + " has " + getSimpleName(potion) + " Potion");
            }
            else {
                out.println("Room " + room.getLocalRoomCounter() + " has no Potions");
                unveilTraps(room);
            }
        }
    }

    private static void unveilTraps(Room room) {
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

    public static String getSimpleName(Object obj) {
        return obj != null ? obj.getClass().getSimpleName() : "";
    }

    private static void calloutForRoomTrap(Room room, Trap roomTrap) {
        out.println("Room " + room.getLocalRoomCounter() + " has " + getSimpleName(roomTrap));
    }

    private static void viewAllStaff(List<Weapon> sigmarWeapons, List<Armour> sigmarArmours,
                                     List<Weapon> grungiWeapons, List<Armour> grungiArmours,
                                     List<Weapon> ladrilWeapons, List<Armour> ladrilArmours,
                                     List<Weapon> zoltarWeapons, List<Armour> zoltarArmours) {

        out.print("Sigmar Armours: ");
        sigmarArmours.forEach(e -> out.println(e.getClass().getSimpleName()));
        out.print("");
        out.print("Sigmar Weapons: ");
        sigmarWeapons.forEach(e -> out.println(e.getClass().getSimpleName()));
        out.print("");
        out.print("Grungi Weapons: ");
        grungiWeapons.forEach(e -> out.println(e.getClass().getSimpleName()));
        out.print("");
        out.print("Grungi Armours: ");
        grungiArmours.forEach(e -> out.println(e.getClass().getSimpleName()));
        out.print("");
        out.print("Ladril Armours: ");
        ladrilArmours.forEach(e -> out.println(e.getClass().getSimpleName()));
        out.print("");
        out.print("Ladril Weapons: ");
        ladrilWeapons.forEach(e -> out.println(e.getClass().getSimpleName()));
        out.print("");
        out.print("Zoltar Armours: ");
        zoltarArmours.forEach(e -> out.println(e.getClass().getSimpleName()));
        out.print("");
        out.print("Zoltar Weapons: ");
        zoltarWeapons.forEach(e -> out.println(e.getClass().getSimpleName()));
    }

    static void printDelimiter() {
        out.println("---------------------------");
    }
    //getAllPotionList().forEach(e -> out.println(getSimpleName(e)));

        /*sigmar.setBalance(1000);
        purchaseWeapon(sigmar, new BroadSword());
        printDelimiter();
        purchaseWeapon(sigmar, new BroadSword());*/

    //getAllSpellsList().forEach(e -> out.println(getSimpleName(e)));
    //getFireSpellList().forEach(e -> out.println(getSimpleName(e)));




        /*purchaseArmour(sigmar, new Shield());
        printDelimiter();
        purchaseWeapon(sigmar, new BroadSword());
        printDelimiter();
        purchaseWeapon(sigmar, new Staff());
        sigmar.setBalance(500);
        printDelimiter();
        purchaseWeapon(sigmar, new CrossBow());*/

        /*out.println(ladril.getMagicNature());
        out.println(zoltar.getMagicNature());
        out.println(sigmar.getMagicNature());
        out.print(sigmar.getRace());*/
    //Spell.getAirSpellList().forEach(e -> out.println(getSimpleName(e)));
    //Spell.getAllSpellsList().forEach(e -> out.println(getSimpleName(e)));


        /*var heroList = retrieveAllClassInstances(Monster.class);
        heroList.forEach(e -> out.println(e.getClass().getSimpleName()));*/
        /*Weapon.getWeaponAttackBoostMap(Weapon.getWeaponList())
                .forEach((k,v) -> out.println(k.getClass().getSimpleName() + " -> " + v));
        out.println();
        Armour.getArmourDefenceBoostMap(Armour.getArmourList())
                .forEach((k,v) -> out.println(k.getClass().getSimpleName() + " -> " + v));*/
}
