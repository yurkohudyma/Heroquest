package ua.hudyma.room.monsters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static ua.hudyma.util.InheritorsFinder.retrieveAllClassInstances;

public abstract class Monster {
    int endurance, attack, defence, movability;
    public char icon;
    int x, y;

    private static Map<Character, Monster> iconMonsterMap;

    public static Monster getMonsterByIcon(char icon) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var map = getIconMonsterMap();
        if (map.containsKey(icon)){
            return map.get(icon);
        }
        throw new IllegalArgumentException("Icon not found");
    }

    protected Monster(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Map<Character, Monster> getIconMonsterMap() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var monsterList = retrieveAllClassInstances(Monster.class);
        var map = new HashMap<Character, Monster>();
        for (Monster m : monsterList){
            Constructor<?> constructor = m.getClass().getConstructor();
            Monster instance = (Monster) constructor.newInstance();
            char icon = instance.getIcon();
            map.put(icon, instance);
        }
        return map;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMovability() {
        return movability;
    }

    public void setMovability(int movability) {
        this.movability = movability;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Monster() {
    }

    public char getIcon() {
        return icon;
    }
}
