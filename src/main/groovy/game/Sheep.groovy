package game

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor;

class Sheep extends Actor {
    static Character symbol = 'S'

    Sheep(Game game, int x = (new Random().nextInt(30)), int y = (new Random().nextInt(10))) {
        super(game)
        this.x = x
        this.y = y

        this.icon = new TextCharacter(symbol)
    }

    static boolean scan(Character c) {
        return c == symbol
    }

    @Override
    def executeMovement() {
        super.executeMovement()

        if(!captured) {
            this.setBackground(game.map.tiles[x][y].icon.backgroundColor)
        }
    }

    def randomMove() {
        if(captured) {
            return
        }

        // do a random movement
        switch (new Random().nextInt(5)) {
            case 0:
                this.movement(this.position.withY(-1))
                break
            case 1:
                this.movement(this.position.withY(1))
                break
            case 2:
                this.movement(this.position.withX(-1))
                break
            case 3:
                this.movement(this.position.withX(1))
                break
            case 4:
                // do nothing
            break
        }
    }

}
