package game

import com.googlecode.lanterna.TextCharacter;

class Sheep extends Actor {

    Sheep(int x = 5, int y = 5) {
        this.x = x
        this.y = y

        this.icon = new TextCharacter('S' as char)
    }

    def randomMove() {
        // do a random movement
        switch (new Random().nextInt(4)) {
            case 0:
                this.movement(0, 1)
                break
            case 1:
                this.movement(0, -1)
                break
            case 2:
                this.movement(1, 0)
                break
            case 3:
                this.movement(-1, 0)
                break
        }
    }

}
