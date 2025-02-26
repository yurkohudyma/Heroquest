package ua.hudyma.room.traps;

import java.util.List;
import java.util.Random;

public abstract class Trap {

    protected Trap() {
    }

    public static Trap generateTrap(){
        var trapList = List.of (new CrossBoltTrap(), new MonsterTrap(), new PitTrap(), new Block());
        return trapList.get(new Random().nextInt(trapList.size()));
    }

    private int x, y;
    public char icon;

    public Trap(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
