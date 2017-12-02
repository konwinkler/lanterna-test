package game

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor

class Actor {
    TextCharacter icon = new TextCharacter('@' as char)
    int x = 0
    int y = 0
    private int offsetX
    private int offsetY
    boolean dirty = true
    protected boolean captured = false

    def movement(int x, int y) {
        this.offsetX = x
        this.offsetY = y
        dirty = true
    }

    def movement(Position position) {
        this.offsetX = position.x - x
        this.offsetY = position.y - y
        dirty = true
    }

    def executeMovement() {
        this.x += offsetX
        this.y += offsetY
        offsetX = 0
        offsetY = 0
    }

    def update() {

    }

    Position getPosition() {
        new Position(x, y)
    }

    def capture(Player player) {
        this.captured = true
        icon = icon.withBackgroundColor(TextColor.ANSI.WHITE).withForegroundColor(TextColor.ANSI.BLACK)
        this.dirty = true

        player.addFollower(this)
    }

    def free() {
        this.captured = false
        icon = icon.withBackgroundColor(TextColor.ANSI.BLACK).withForegroundColor(TextColor.ANSI.WHITE)
        this.dirty = true
    }
}
