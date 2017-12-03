package game

import com.googlecode.lanterna.TextCharacter

class Player extends Actor {

    List<Actor> followers = new LinkedList<>()
    static Character symbol = '@'

    Player(Game game, int x = 30, int y = 5) {
        super(game)
        icon = new TextCharacter(symbol)
        this.x = x
        this.y = y
    }

    def addFollower(Actor actor) {
        this.followers.add(actor)
    }

    static boolean scan(Character c) {
        return c == symbol
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
