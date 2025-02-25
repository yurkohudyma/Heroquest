package ua.hudyma.room.monsters;

public class Orc extends Monster {
    public Orc(int x, int y) {
        super(x,y);
        super.attack = 3;
        super.defence = 1;
        super.endurance = 2;
        super.movability = 4;
        super.icon = 'O';
    }
}
