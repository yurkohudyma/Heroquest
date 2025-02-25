package ua.hudyma.hero.weaponry.defence;

import ua.hudyma.util.InheritorsFinder;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public abstract class Armour {
    public int getPrice() {
        return price;
    }

    public int getDefenceBoost() {
        return defenceBoost;
    }

    public int price, defenceBoost;

    public Armour() {
    }

    public static List<Armour> getArmourList() throws ClassNotFoundException {
        return InheritorsFinder.retrieveAllClassInstances(Armour.class);
    }

    public static Map<Armour, Integer> getArmourPriceMap (List<Armour> armourList){
        return armourList.stream()
                .collect(toMap(Function.identity(), Armour::getPrice));
    }

    public static Map<Armour, Integer> getArmourDefenceBoostMap (List<Armour> armourList){
        return armourList.stream()
                .collect(toMap(Function.identity(), Armour::getDefenceBoost));
    }
}
