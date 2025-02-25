package ua.hudyma.room;

public class Door {
    private int X, Y;

    public char icon;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Door(int x, int y) {
        X = x;
        Y = y;
        icon= '□';
    }
}
