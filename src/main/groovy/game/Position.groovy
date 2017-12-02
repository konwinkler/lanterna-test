package game

class Position {

    int x
    int y

    Position(int x, int y) {
        this.x = x
        this.y = y
    }

    boolean adjacent(Position other) {
        return (Math.abs(x - other.x) <= 1
                && Math.abs(y - other.y) <= 1)
    }

    Position withX(int n) {
        new Position(x + n, this.y)
    }

    Position withY(int n) {
        new Position(x, y + n)
    }
}
