package game

class Player extends Actor {

    List<Actor> followers = new LinkedList<>()

    Player(Game game, int x = 30, int y = 5) {
        super(game)
        this.x = x
        this.y = y
    }

    def addFollower(Actor actor) {
        this.followers.add(actor)
    }

    @Override
    def executeMovement() {
        super.executeMovement()

        this.setBackground(game.map.tiles[x][y].icon.backgroundColor)
    }

    @Override
    boolean movement(Position position) {
        if(super.movement(position)) {
            followers.each {
                it.movement(this.position)
            }
        }

        true
    }

    def releaseFollowers() {
        followers.each {
            it.free()
        }
        followers.clear()
    }
}
