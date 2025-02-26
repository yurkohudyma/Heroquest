package ua.hudyma.hero.characters;

import ua.hudyma.hero.Magic;
import ua.hudyma.hero.Races;

public class Elf extends Hero implements Magic {

    public Elf() {
    }

    public Elf(Nature magicNature) {
        super.legend = "Elves and Wizards are physically weaker but the clever lads of the group. " +
                "They use their brains to cast an array of magical spells without running the risk of having to get up close and personal";
        super.race = Races.ELF;
        super.magicNature = magicNature;
        super.livePoints = 6;
        super.mindPoints = 4; // could remember spell of one nature kind (4 or 3?)
        super.icon = 'E';
    }

    @Override
    public Nature getNature() {
        return super.magicNature;
    }
}
