package game

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.Screen

class Game {
    Player player
    List<Sheep> herd = new ArrayList<>()
    Screen screen
    Map map

    Game(Screen screen) {
        this.screen = screen
        this.map = new Map(screen.terminalSize.columns, screen.terminalSize.rows)
        map.initialize(this)
        drawMap()
    }

    def drawMap() {
        for(int x=0; x < map.width; x++) {
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
            //BUG: what if there is a different actor on that tile?
            //Or can we not walk into other actors?
            screen.setCharacter(actor.x, actor.y, map.tiles[actor.x][actor.y].icon)
            actor.executeMovement()
            screen.setCharacter(actor.x, actor.y, actor.icon)
            actor.dirty = false
        }
    }

    def handleInput(KeyStroke keyStroke) {
        def progress = false
        switch (keyStroke) {
            case KeyStroke.fromString('w'):
                player.movement(player.position.withY(-1))
                progress = true
                break
            case KeyStroke.fromString('a'):
                player.movement(player.position.withX(-1))
                progress = true
                break
            case KeyStroke.fromString('s'):
                player.movement(player.position.withY(1))
                progress = true
                break
            case KeyStroke.fromString('d'):
                player.movement(player.position.withX(1))
                progress = true
                break
            case KeyStroke.fromString('l'):
                catchAnimal()
                progress = true
                break
        }

        if(progress) {
            herd.each {
                it.randomMove()
            }
        }
    }

    def catchAnimal() {
        if(player.followers.empty) {
            herd.each {
                if (player.position.adjacent(it.position)) {
                    it.capture(player)
                }
            }
        } else {
            // release followers
            player.releaseFollowers()
        }
    }

    def update() {
        herd.each {
            it.update()
        }
    }

    boolean isWon() {
        for(Actor actor : herd) {
            if(!map.getTileAt(actor.position).goal) {
                return false
            }
        }

        return true
    }

    List<Actor> getActors() {
        def l = new LinkedList()
        l.add(player)
        l.addAll(herd)

        return l
    }
}
