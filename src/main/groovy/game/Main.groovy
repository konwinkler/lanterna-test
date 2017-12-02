package game

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

            def game = new Game(screen)

            while(true) {

                game.handleInput(terminal.pollInput())

                game.update()

                game.draw()

                Thread.sleep(10)
            }


        } catch (Exception e) {
            e.printStackTrace()
        }
    }

}