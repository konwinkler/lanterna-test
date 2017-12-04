package game

import com.googlecode.lanterna.TextColor

abstract class Actor extends Drawable {
    int x = 0
    int y = 0
    private int offsetX
    private int offsetY
    boolean dirty = true
    protected boolean captured = false
    Game game
    LinkedList<Action> actionQueue = new LinkedList<>()

    Actor(Game game) {
        this.game = game
    }

    boolean movement(Position position) {
        if(!game.map.getTileAt(position).passable) {
            return false
        }
        for(Actor actor : game.actors) {
            if(actor.futurePosition == position) {
                return false
            }
        }

        this.offsetX = position.x - x
        this.offsetY = position.y - y
        dirty = true

        return true
    }

    Position getFuturePosition() {
        new Position(x + offsetX, y + offsetY)
    }

    def executeMovement() {
        this.x += offsetX
        this.y += offsetY
        offsetX = 0
        offsetY = 0
    }

    boolean update() {
        if(actionQueue.empty) {
            return false
        }

        switch (actionQueue.removeFirst()) {
            case Action.MOVE_UP:
                movement(position.withY(-1))
                break
            case Action.MOVE_LEFT:
                movement(position.withX(-1))
                break
            case Action.MOVE_DOWN:
                movement(position.withY(1))
                break
            case Action.MOVE_RIGHT:
                movement(position.withX(1))
                break
            case Action.IDLE:
                // do nothing
                break
        }

        return true
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

    def addAction(Action action) {
        this.actionQueue.add(action)
    }
}
