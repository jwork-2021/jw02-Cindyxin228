package S191220097.taskSecond;

import java.util.Random;

import S191220097.taskSecond.Line.Position;

public class Monster implements Linable {

    private final int r;
    private final int g;
    private final int b;
    private final int rank;

    private Position position;

    public static Monster[] monster;

    private Monster(int r, int g, int b, int _rank) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.rank = _rank;
    }

    public static void initialize(int number) {
        monster = new Monster[number];
        for (int i = 0; i < number; i++) {
            int r1 = 0, g1 = 0, b1 = 0;
            int bound = number / 4, time = 255 / bound;
            if (i < bound) {
                r1 = 255;
                g1 = i * time;
                b1 = 0;
            } else if (i < bound * 2) {
                r1 = time * number / 2 - time * i;
                g1 = 250;
                b1 = 0;
            } else if (i < bound * 3) {
                r1 = 0;
                g1 = number * 3 / 4 * time - time * i;
                b1 = 255;
            } else {
                r1 = 252;
                g1 = 0;
                b1 = number * time - time * i;
            }
            monster[i] = new Monster(r1, g1, b1, i);
        }
        Random r = new Random();
        final int exchangeNum = number / 2;
        for (int i = 0; i < exchangeNum; i++) {
            int x = r.nextInt(number);
            int y = r.nextInt(number);
            if (x != y) {
                Monster tp = monster[x];
                monster[x] = monster[y];
                monster[y] = tp;
            }
        }
    }

    @Override
    public String toString() {
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m    " + this.rank + "  \033[0m";
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public void swapPosition(Monster another) {
        Position p = another.position;
        this.position.setLinable(another);
        p.setLinable(this);
    }

    public static Monster getMonsterByRank(int rank) {

        for (Monster m : monster) {
            if (m.rank == rank) {
                return m;
            }
        }
        return null;

    }

    @Override
    public int getValue() {
        return this.rank;
    }

}