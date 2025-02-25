package ua.hudyma.hero.characters;

import ua.hudyma.hero.Magic;
import ua.hudyma.hero.Races;
import ua.hudyma.hero.weaponry.attack.Weapon;
import ua.hudyma.hero.weaponry.defence.Armour;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero {

    int livePoints, mindPoints;

    public String legend;

    public int getAttack() {
        return livePoints;
    }

    public void setAttack(int attack) {
        this.livePoints = attack;
    }

    public int getDefence() {
        return mindPoints;
    }

    public void setDefence(int defence) {
        this.mindPoints = defence;
    }

    public Races getRace() {
        return race;
    }

    public Races race;

    public Magic.Nature getMagicNature() {
        return this instanceof Magic ? magicNature : Magic.Nature.NA;
    }

    protected Magic.Nature magicNature;

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    protected List<Weapon> weapons;

    public List<Armour> getArmours() {
        return armours;
    }

    public void setArmours(List<Armour> armours) {
        this.armours = armours;
    }

    protected List<Armour> armours;

    public boolean hasToolkit() {
        return hasToolkit;
    }

    public void setHasToolkit (boolean hasToolkit) {
        this.hasToolkit = hasToolkit;
    }

    boolean hasToolkit;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private int balance;

    protected Hero() {
        this.armours = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.balance = 0;
    }
}
