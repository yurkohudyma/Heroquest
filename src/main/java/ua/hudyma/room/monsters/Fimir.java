package ua.hudyma.room.monsters;

public class Fimir extends Monster {
    public Fimir(int i, int y) {
        super(i, y);
    }

    public Fimir() {
    }

    {
        super.attack = 4;
        super.defence = 3;
        super.endurance = 5;
        super.movability = 6;
        super.icon = 'F';
    }
}
