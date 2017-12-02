package game

class Map {
    int height
    int width
    Tile[][] tiles
    def input = ['# # ### #   #    ###',
                 '### #   #   #    # #',
                 '# # ### ### ###  ###']

    Map(int width, int height) {
        this.width = width
        this.height = height
        tiles = new Tile[width][height]
    }

    def initialize() {
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
                tiles[x][y] = new Tile(c)
            }
        }
    }

}
