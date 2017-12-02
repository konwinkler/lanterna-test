package game

import com.googlecode.lanterna.TextCharacter

class Tile {
    TextCharacter icon = new TextCharacter(' ' as char)

    Tile(Character c) {
        if(c != null) {
            icon = new TextCharacter(c)
        }
    }
}
