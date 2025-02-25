package ua.hudyma.hero.spells;

import ua.hudyma.exceptions.SpellNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ua.hudyma.util.InheritorsFinder.retrieveAllClassInstances;

public abstract class Spell {

    protected Spell() {
    }

    public int getAttackBoost() {
        return attackBoost;
    }

    protected int attackBoost;

    public static final String SPELL_PATH = "ua.hudyma.hero.spells.magicnatures.";

    protected static final List<Spell> fireSpellList;
    protected static final List<Spell> waterSpellList;
    protected static final List<Spell> earthSpellList;
    protected static final List<Spell> airSpellList;

    public static List<Spell> getFireSpellList() {
        return fireSpellList;
    }


    public static List<Spell> getWaterSpellList() {
        return waterSpellList;
    }

    public static List<Spell> getAirSpellList() {
        return airSpellList;
    }

    public static List<Spell> getEarthSpellList() {
        return earthSpellList;
    }

    public static List<Spell> getAllSpellsList(){
        return allSpellsList;
    }



    static {
        try {
            fireSpellList = retrieveAllClassInstances(Spell.class, SPELL_PATH + "fire");
        } catch (ClassNotFoundException e) {
            throw new SpellNotFoundException(e);
        }
    }



    static {
        try {
            waterSpellList = retrieveAllClassInstances(Spell.class, SPELL_PATH + "water");
        } catch (ClassNotFoundException e) {
            throw new SpellNotFoundException(e);
        }
    }



    static {
        try {
            earthSpellList = retrieveAllClassInstances(Spell.class, SPELL_PATH + "earth");
        } catch (ClassNotFoundException e) {
            throw new SpellNotFoundException(e);
        }
    }



    static {
        try {
            airSpellList = retrieveAllClassInstances(Spell.class, SPELL_PATH + "air");
        } catch (ClassNotFoundException e) {
            throw new SpellNotFoundException(e);
        }
    }

    protected static final List<Spell> allSpellsList = new ArrayList<>();

    static {
        allSpellsList.addAll(getFireSpellList());
        allSpellsList.addAll(getWaterSpellList());
        allSpellsList.addAll(getEarthSpellList());
        allSpellsList.addAll(getAirSpellList());
    }
}
