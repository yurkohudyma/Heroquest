package ua.hudyma.hero.characters;

import ua.hudyma.hero.Races;

public class Dwarf extends Hero {
    public Dwarf() {
        super();
        super.legend = "Stumpy the dwarf is quite strong and also the crafty one of the bunch who uses handy skills to compensate his shortcomings";
        super.race = Races.DWARF;
        super.livePoints = 7;
        super.mindPoints = 3; //for Dwarf seems like are useless
        super.hasToolkit = true;
        super.icon = 'D';
    }
}
