package ua.hudyma.room.monsters;

public class Mummy extends Monster{

    public Mummy() {
        super.icon = 'M';
    }

    public Mummy(int x, int y) {
        super(x,y);
        super.attack = 6;
        super.defence = 4;
        super.endurance = 8;
        super.movability = 1;
        super.icon = 'M';
    }
}
