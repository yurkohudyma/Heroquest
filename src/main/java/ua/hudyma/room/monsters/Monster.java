package ua.hudyma.room.monsters;

import static ua.hudyma.util.InheritorsFinder.retrieveAllClassInstances;

public abstract class Monster {
    int endurance, attack, defence, movability;
    public char icon;
    int x, y;

    public static Monster getMonsterByIcon(char icon) throws ClassNotFoundException {
        var monsterList = retrieveAllClassInstances(Monster.class);
        return monsterList
                .stream()
                .filter(i -> i.getIcon() == icon)
                .findAny()
                .orElseThrow();
    }

    protected Monster(int x, int y) {
        this.x = x;
        this.y = y;
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
