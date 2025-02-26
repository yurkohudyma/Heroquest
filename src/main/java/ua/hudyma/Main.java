package ua.hudyma;

import ua.hudyma.hero.characters.Hero;
import ua.hudyma.hero.weaponry.attack.Weapon;
import ua.hudyma.hero.weaponry.defence.Armour;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;
import static ua.hudyma.hero.characters.Hero.sigmar;
import static ua.hudyma.maze.Maze.configureMaze01;

public class Main {
    public static AtomicInteger roomCounter = new AtomicInteger();

    public void main(String[] args) throws ClassNotFoundException {
        configureMaze01();
    }
    @Deprecated
    public static void configureHeroes() {

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
