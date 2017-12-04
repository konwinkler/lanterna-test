package game

import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal

class Main {

    static void main(String[] args) {

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory()
        try {
            Terminal terminal = defaultTerminalFactory.createTerminal()
            Screen screen = new TerminalScreen(terminal)

            screen.startScreen()

            screen.setCursorPosition(null)

            intro(screen)

            Iterator<List> it = Levels.all.iterator()

            //skip levels
//            it.next()
//            it.next()
//            it.next()

            while(it.hasNext()) {

                def game = new Game(screen, it.next())

                while (!game.isWon()) {

                    game.handleInput(terminal.pollInput())

                    game.update()

                    game.draw()

                    Thread.sleep(50)
                }

                screen.terminal.bell()
            }

            outro(screen)

        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    static void outro(TerminalScreen screen) {
        screen.clear()
        TextGraphics textGraphics = screen.newTextGraphics()
        textGraphics.setForegroundColor(TextColor.ANSI.RED)
        textGraphics.putString(10, 5, "You Win!")
        textGraphics.putString(10, 10, "Thank you for playing")
        screen.refresh()
    }

    static def intro(TerminalScreen screen) {
        screen.clear()
        TextGraphics textGraphics = screen.newTextGraphics()
        textGraphics.putString(5, 2, "Herd the sheep back to the barn")
        textGraphics.putString(5, 4, "'@' this is you")
        textGraphics.putString(5, 5, "'S' those are the sheep")
        textGraphics.putString(5, 6, "bring them to the orange area")
        textGraphics.putString(5, 8, "Controls:")
        textGraphics.putString(5, 9, "Arrow Keys: move in that direction (can hold the key)")
        textGraphics.putString(5, 10, "'l': leash/unleash a sheep (must be adjacent)")
        textGraphics.putString(5, 12, "Press any key to continue")
        screen.refresh()

        screen.terminal.readInput()
    }
}
