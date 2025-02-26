package ua.hudyma.hero.characters;

import ua.hudyma.hero.Magic;
import ua.hudyma.hero.Races;

public class Wizard extends Hero implements Magic {

    public Wizard() {
    }

    public Wizard(Nature magicNature) {
        super.legend = "Wizard lives in Sky Mountains";
        super.race = Races.WIZARD;
        super.magicNature = magicNature;
        super.livePoints = 4;
        super.mindPoints = 6; //quantity of spell one could remember
        super.icon = 'W';
    }

    @Override
    public Nature getNature() {
        return super.magicNature;
    }
}
