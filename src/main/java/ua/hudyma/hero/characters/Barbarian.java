package ua.hudyma.hero.characters;

import ua.hudyma.hero.Races;

public class Barbarian extends Hero {

    public Barbarian() {
        super.legend = "The Barbarian is built for strength and is a trooper during the bloodiest of melee battles";
        super.race = Races.BARBARIAN;
        super.livePoints = 8;
        super.mindPoints = 2; //for Dwarf seems like are useless
        super.icon = 'B';
    }
}
