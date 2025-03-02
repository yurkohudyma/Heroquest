package ua.hudyma.room.monsters;

public class ChaosWarrior extends Monster {

    public ChaosWarrior() {
        super.icon = 'C';

        //todo
    }

    public ChaosWarrior(int x, int y) {
        super(x,y);
        super.attack = 7;
        super.defence = 10;
        super.endurance = 10;
        super.movability = 8;
        super.icon = 'C';
    }
}
