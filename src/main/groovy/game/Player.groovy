package game

class Player extends Actor {

    List<Actor> followers = new LinkedList<>()

    Player(int x = 30, int y = 5) {
        this.x = x
        this.y = y
    }

    def addFollower(Actor actor) {
        this.followers.add(actor)
    }

    @Override
    def executeMovement(Map map) {
        super.executeMovement(map)

        this.setBackground(map.tiles[x][y].icon.backgroundColor)
    }

    @Override
    def movement(Position position) {
        super.movement(position)

        followers.each {
            it.movement(this.position)
        }
    }

    def releaseFollowers() {
        followers.each {
            it.free()
        }
        followers.clear()
    }
}
