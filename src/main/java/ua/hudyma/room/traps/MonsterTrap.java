package ua.hudyma.room.traps;

import ua.hudyma.room.monsters.*;
import ua.hudyma.util.InheritorsFinder;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MonsterTrap extends Trap {
    public Monster getMonster() {
        return monster;
    }

    Monster monster;

    public MonsterTrap(int x, int y) throws IOException, ClassNotFoundException {
        super(x, y);
        monster = getMonsterForTrap();
    }

    public MonsterTrap() {}

    public Monster getMonsterForTrap() throws ClassNotFoundException {
        var classList = InheritorsFinder.findInheritors(Monster.class, Monster.class.getPackageName());
        var monsterList = InheritorsFinder.mapToEntity(classList, Monster.class);
        int rand = new Random().nextInt(monsterList.size());
        return monsterList.get(rand);
    }
}
