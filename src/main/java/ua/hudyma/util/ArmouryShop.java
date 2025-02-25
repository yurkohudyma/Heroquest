package ua.hudyma.util;

import ua.hudyma.hero.characters.Hero;
import ua.hudyma.hero.weaponry.attack.Weapon;
import ua.hudyma.hero.weaponry.defence.Armour;

import java.util.Objects;

import static java.lang.System.out;
import static ua.hudyma.Main.getSimpleName;

public class ArmouryShop {

    public static void purchaseWeapon (Hero hero, Weapon weapon){
        var balance = hero.getBalance();
        int price = weapon.getPrice();
        out.println(getSimpleName(hero) + " has " + balance + " gold");
        out.println(getSimpleName(weapon) + " price is "+ price);
        if (price > balance) {
            out.println(getSimpleName(hero) + " has no available funds, earn " + (price - balance));
            return;
        }
        out.println(getSimpleName(weapon) + " purchased");
        hero.setBalance(balance - price);
        var heroWeapon = hero.getWeapons();
        heroWeapon.add(weapon);
        hero.setWeapons(heroWeapon);
        out.print(getSimpleName(hero) + " weapons: ");
        heroWeapon.forEach( w -> out.print(getSimpleName(w) + " "));
        out.println();
        out.println(getSimpleName(hero) + " balance is now " + hero.getBalance());
    }

    public static void purchaseArmour (Hero hero, Armour armour){
        var balance = hero.getBalance();
        var heroArmour = hero.getArmours();
        for (Armour unit : heroArmour){
            if (armour.getClass().isAssignableFrom(unit.getClass())){
                out.println(getSimpleName(hero) + " already owns " + getSimpleName(armour));
                return;
            }
        }
        int price = armour.getPrice();
        out.println(getSimpleName(hero) + " has " + balance + " gold");
        out.println(getSimpleName(armour) + " price is "+ price);
        if (price > balance) {
            out.println(getSimpleName(hero) + " has no available funds, earn " + (price - balance));
            return;
        }
        out.println(getSimpleName(armour) + " purchased");
        hero.setBalance(balance - price);
        heroArmour.add(armour);
        out.print(getSimpleName(hero) + " armours: ");
        heroArmour.forEach( a -> out.print(getSimpleName(a) + " "));
        out.println();
        out.println(getSimpleName(hero) + " balance is now " + hero.getBalance());
    }
}
