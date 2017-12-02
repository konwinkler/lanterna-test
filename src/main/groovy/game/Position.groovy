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

}
