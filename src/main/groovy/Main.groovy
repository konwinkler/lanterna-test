import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
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

            Random random = new Random()
            TerminalSize terminalSize = screen.terminalSize

            for(int column = 0; column < terminalSize.columns; column++) {
                for(int row = 0; row < terminalSize.getRows(); row++) {
                    screen.setCharacter(column, row, new TextCharacter(
                            ' 'as char,
                            TextColor.ANSI.DEFAULT,
                            // This will pick a random background color
                            TextColor.ANSI.values()[random.nextInt(TextColor.ANSI.values().length)])
                    );
                }
            }

            screen.refresh()


        } catch (Exception e) {
            e.printStackTrace()
        }
    }

}
