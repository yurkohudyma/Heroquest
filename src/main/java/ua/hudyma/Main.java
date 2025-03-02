package ua.hudyma;

import ua.hudyma.hero.characters.Hero;
import ua.hudyma.maze.Maze;
import ua.hudyma.room.monsters.Monster;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;
import static ua.hudyma.hero.characters.Hero.*;
import static ua.hudyma.maze.Maze.configureMaze01;

public class Main {
    public static AtomicInteger roomCounter = new AtomicInteger();

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        configureMaze01();
        var heroList = Hero.getHeroList();
        var sigmar = heroList.get(heroList.indexOf(Hero.sigmar));
        var grungi = heroList.get(heroList.indexOf(Hero.grungi));
        var ladril = heroList.get(heroList.indexOf(Hero.ladril));
        var zoltar = heroList.get(heroList.indexOf(Hero.zoltar));
        /*moveRight(getRandomSteps(), sigmar);
        moveDown(getRandomSteps(), grungi);
        moveUp(-getRandomSteps(), ladril);
        moveLeft(-getRandomSteps(), zoltar);
        moveUp(-4,ladril);
        moveLeft(-3, grungi);*/
        moveDown(6, sigmar);
        moveRight(3, sigmar);
        moveUp(-2, sigmar);
        Maze.viewMazeArray();
        //out.println(getSimpleName(Monster.getMonsterByIcon('S')));
        //Monster.getMonstersList().forEach(hero -> out.println(m.getIcon()));
        moveUp(-1, sigmar);
        //Monster.getIconMonsterMap().forEach((k,v) -> out.println(k +" -> "+ getSimpleName(v)));
        attackUp(-1, sigmar);


        Maze.viewMazeArray();
    }


    public static String getSimpleName(Object obj) {
        return obj != null ? obj.getClass().getSimpleName() : "";
    }
    public static void printDelimiter() {
        out.println("---------------------------");
    }

    /*private static void viewAllStaff(List<Weapon> sigmarWeapons, List<Armour> sigmarArmours,
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
    }*/

    //getAllPotionList().forEach(e -> out.println(getSimpleName(e)));

        /*sigmar.setBalance(1000);
        purchaseWeapon(sigmar, new BroadSword());
        printDelimiter();
        purchaseWeapon(sigmar, new BroadSword());*/

    //getAllSpellsList().forEach(e -> out.println(getSimpleName(e)));
    //getFireSpellList().forEach(e -> out.println(getSimpleName(e)));
    //
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
