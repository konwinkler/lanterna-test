package game

class Map {
    int height
    int width
    Tile[][] tiles
    def input = ['                                                   ',
                 '                                                   ',
                 '                                                   ',
                 '                                                   ',
                 '                                                   ',
                 '                                           ####### ',
                 '                                           #^^^^^# ',
                 '                          @                 ^^^^^# ',
                 '                       S                    ^^^^^# ',
                 '                                           #^^^^^# ',
                 '                                           ####### ',
                 '                                                   ',
                 '                                                   ',
                 '                                                   ']

    Map(int width, int height) {
        this.width = width
        this.height = height
        tiles = new Tile[width][height]
    }

    def initialize(Game game) {
        for(int x=0; x<width; x++) {
            for (int y = 0; y < height; y++) {
                String s = null
                def c = null
                if(input.size() > y) {
                    s = input.get(y)
                }
                if(s!=null && s.length() > x) {
                    c = s.charAt(x)
                }
                if(Player.scan(c)) {
                    game.player = new Player(game, x, y)
                    tiles[x][y] = Tile.scan(' ' as char)
                }
                else if(Sheep.scan(c)) {
                    game.herd.add(new Sheep(game, x, y))
                    tiles[x][y] = Tile.scan(' ' as char)
                }
                else {
                    tiles[x][y] = Tile.scan(c)
                }
            }
        }
    }

    def getTileAt(Position position) {
        return tiles[position.x][position.y]
    }
}
