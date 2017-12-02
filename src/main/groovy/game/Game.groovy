package game

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.Screen

import java.awt.event.KeyListener

class Game {
    def player = new Player()
    List<Sheep> herd = new ArrayList<>()
    Screen screen
    Map map

    Game(Screen screen) {
        this.screen = screen
        this.map = new Map(screen.terminalSize.columns, screen.terminalSize.rows)
        map.initialize()

        herd.add(new Sheep())

        drawMap()
    }

    def drawMap() {
        for(int x=0; x<map.width; x++) {
            for (int y = 0; y < map.height; y++) {
                screen.setCharacter(x, y, map.tiles[x][y].icon)
            }
        }
    }

    boolean draw() {
        draw(player)
        draw(herd)

        screen.refresh()
        true
    }

    def draw(List<Actor> actors) {
        actors.each {
            draw(it)
        }
    }

    def draw(Actor actor) {
        if(actor.isDirty()) {
            screen.setCharacter(actor.x, actor.y, map.tiles[actor.x][actor.y].icon)
            actor.move()
            screen.setCharacter(actor.x, actor.y, actor.icon)
        }
    }

    def handleInput(KeyStroke keyStroke) {
        def progress = false
        switch (keyStroke) {
            case KeyStroke.fromString('w'):
                player.movement(0, -1)
                progress = true
                break
            case KeyStroke.fromString('a'):
                player.movement(-1, 0)
                progress = true
                break
            case KeyStroke.fromString('s'):
                player.movement(0, 1)
                progress = true
                break
            case KeyStroke.fromString('d'):
                player.movement(1, 0)
                progress = true
                break
        }

        if(progress) {
            herd.each {
                it.randomMove()
            }
        }
    }

    def update() {
        herd.each {
            it.update()
        }
    }

}
