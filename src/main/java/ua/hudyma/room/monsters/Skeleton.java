package ua.hudyma.room.monsters;

public class Skeleton extends Monster {
    public Skeleton(int x, int y) {
        super(x, y);
        super.attack = 5;
        super.defence = 1;
        super.endurance = 3;
        super.movability = 4;
        super.icon = 'S';
    }

    public Skeleton() {
    }
}
