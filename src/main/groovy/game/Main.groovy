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

            Iterator<List> it = Levels.all.iterator()
            while(it.hasNext()) {
                screen.clear()
                screen.refresh()

                def game = new Game(screen, it.next())

                while (!game.isWon()) {

                    game.handleInput(terminal.pollInput())

                    game.update()

                    game.draw()

                    Thread.sleep(50)
                }
            }

            screen.clear()
            TextGraphics textGraphics = screen.newTextGraphics()
            textGraphics.setForegroundColor(TextColor.ANSI.RED)
            textGraphics.putString(10, 5, "You Win!")
            screen.refresh()

        } catch (Exception e) {
            e.printStackTrace()
        }
    }

}
