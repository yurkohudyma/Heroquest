package ua.hudyma.room.monsters;

public class Zombie extends Monster {
    public Zombie(int x, int y) {
        super(x, y);
        super.attack = 5;
        super.defence = 4;
        super.endurance = 6;
        super.movability = 2;
        super.icon = 'Z';
    }

    public Zombie() {
        super.icon = 'Z';
    }
}
