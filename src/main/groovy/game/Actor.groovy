package game

import com.googlecode.lanterna.TextCharacter

class Actor {
    TextCharacter icon = new TextCharacter('@' as char)
    int x = 0
    int y = 0
    private int offsetX
    private int offsetY
    boolean dirty = true

    def movement(int x, int y) {
        this.offsetX = x
        this.offsetY = y
        dirty = true
    }

    def move() {
        this.x += offsetX
        this.y += offsetY
        offsetX = 0
        offsetY = 0
        dirty = false
    }

    def update() {

    }
}
