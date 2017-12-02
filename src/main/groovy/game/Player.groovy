package game

class Player extends Actor {

    List<Actor> followers = new LinkedList<>()

    Player(int x = 10, int y = 5) {
        this.x = x
        this.y = y
    }

    def addFollower(Actor actor) {
        this.followers.add(actor)
    }

    @Override
    def movement(int x, int y) {
        super.movement(x, y)

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
