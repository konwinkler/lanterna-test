package game

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor

class Drawable {
    TextCharacter icon = new TextCharacter(' ' as char)

    def setBackground(TextColor color) {
        icon = icon.withBackgroundColor(color)
    }
}
