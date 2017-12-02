package game

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor

class Tile extends Drawable {

    Tile(Character c) {
        if(c != null) {
            icon = new TextCharacter(c)
        }
    }

    static Tile scan(Character c) {
        switch (c) {
            case '^':
                def t = new Tile(' ' as char)
                t.setBackground(TextColor.ANSI.YELLOW)
                return t
            default:
                return new Tile(c)
        }
    }

}
