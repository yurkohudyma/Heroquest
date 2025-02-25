package ua.hudyma.hero.weaponry.attack;

import ua.hudyma.hero.characters.Hero;
import ua.hudyma.hero.weaponry.defence.Armour;
import ua.hudyma.util.InheritorsFinder;

import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static ua.hudyma.Main.getSimpleName;

public abstract class Weapon {

    protected Weapon() throws ClassNotFoundException {
    }

    public int getPrice() {
        return price;
    }

    int price;

    public int getAttackBoost() {
        return attackBoost;
    }

    int attackBoost;

    public static List<Weapon> getWeaponList() throws ClassNotFoundException {
        return InheritorsFinder.retrieveAllClassInstances(Weapon.class);
    }

    public static Map<Weapon, Integer> getWeaponsPriceMap (List<Weapon> weaponList){
        return weaponList
                .stream()
                .collect(toMap(identity(), Weapon::getPrice));
    }

    public static Map<Weapon, Integer> getWeaponAttackBoostMap (List<Weapon> weaponList){
        return weaponList
                .stream()
                .collect(toMap(identity(), Weapon::getAttackBoost));
    }

    public static void purchaseWeapon (Hero hero, Weapon weapon){
        var balance = hero.getBalance();
        var heroWeapon = hero.getWeapons();
        for (Weapon unit : heroWeapon){
            if (weapon.getClass().isAssignableFrom(unit.getClass())){
                out.println(getSimpleName(hero) + " already owns " + getSimpleName(weapon));
                return;
            }
        }
        int price = weapon.getPrice();
        out.println(getSimpleName(hero) + " has " + balance + " gold");
        out.println(getSimpleName(weapon) + " price is "+ price);
        if (price > balance) {
            out.println(getSimpleName(hero) + " has no available funds, earn " + (price - balance));
            return;
        }
        out.println(getSimpleName(weapon) + " purchased");
        hero.setBalance(balance - price);
        heroWeapon.add(weapon);
        out.print(getSimpleName(hero) + " weapons: ");
        heroWeapon.forEach( a -> out.print(getSimpleName(a) + " "));
        out.println();
        out.println(getSimpleName(hero) + " balance is now " + hero.getBalance());
    }

}
