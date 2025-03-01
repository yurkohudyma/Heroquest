package ua.hudyma.room.monsters;

public class Goblin extends Monster {
    public Goblin(int x, int y) {
        super(x,y);
        super.endurance = 1;
        super.attack = 1;
        super.defence = 1;
        super.movability = 5;
        super.icon = 'G';
    }

    public Goblin() {
        super.icon = 'G';
    }
}
