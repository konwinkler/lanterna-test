package game

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.Screen

import java.awt.event.KeyListener

class Game {
    def player = new Player()
    Screen screen
    Map map

    Game(Screen screen) {
        this.screen = screen
        this.map = new Map(screen.terminalSize.columns, screen.terminalSize.rows)
        map.initialize()

        drawMap()
    }

    def drawMap() {
        for(int x=0; x<map.width; x++) {
            for (int y = 0; y < map.height; y++) {
                screen.setCharacter(x, y, map.tiles[x][y].icon)
            }
        }
    }

    boolean update() {
        draw(player)

        true
    }

    def draw(Player player) {
        if(player.isDirty()) {
            screen.setCharacter(player.x, player.y, map.tiles[player.x][player.y].icon)
            player.move()
            screen.setCharacter(player.x, player.y, player.icon)
        }
    }

    def handleInput(KeyStroke keyStroke) {
        switch (keyStroke) {
            case KeyStroke.fromString('w'):
                player.movement(0, -1)
                break
            case KeyStroke.fromString('a'):
                player.movement(-1, 0)
                break
            case KeyStroke.fromString('s'):
                player.movement(0, 1)
                break
            case KeyStroke.fromString('d'):
                player.movement(1, 0)
                break
        }
    }
}
