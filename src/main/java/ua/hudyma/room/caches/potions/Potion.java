package ua.hudyma.room.caches.potions;

import ua.hudyma.room.Room;
import ua.hudyma.room.caches.Cache;

import java.util.List;
import java.util.Random;

import static ua.hudyma.util.InheritorsFinder.retrieveAllClassInstances;

public abstract class Potion extends Cache {

    public int getDefenceBoost() {
        return defenceBoost;
    }

    int defenceBoost;
    int attackBoost;

    public int getAttackBoost() {
        return attackBoost;
    }

    public boolean canKillUndead() {
        return canKillUndead;
    }

    protected boolean canKillUndead;

    public static List<Potion> getAllPotionList() throws ClassNotFoundException {
        return retrieveAllClassInstances(Potion.class);
    }

    public List<Potion> allPotionList;

    public static Potion generateRandomLotion () throws ClassNotFoundException {
        var potionList = retrieveAllClassInstances(Potion.class);
        return potionList.get(new Random().nextInt(potionList.size()));
    }

    public static boolean roomHasPotions (){
        return Room.generateBoolean();
    }
}
