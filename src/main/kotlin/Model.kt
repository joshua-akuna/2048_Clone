import kotlin.random.Random

/**
 * The model class is responsible for all manipulations
 * performed on the board.
 */
class Model() {
    // defines the width of the board.
    val FIELD_WIDTH: Int = 4

    // define a 2-D array for the game Tiles.
    private var gameTiles: Array<Array<Tile>>? = null
        get() = field

    init {
        // Initializes the gameTiles reference attribute
        gameTiles = Array(FIELD_WIDTH) { Array(FIELD_WIDTH) { Tile() } }
    }

    private fun addTile(){
        // gets all the empty tiles
        val emptyTiles = getEmptyTiles()
        // gets a random index of an empty Tile
        val randomIndex = Random.nextInt(emptyTiles.size)
        // gets the Tile at the random index
        var randomTile = emptyTiles[randomIndex]
        // getting a value of 2 or 4 at random for the Tile weight
        val weight = if (Random.nextInt(10) < 9) 2 else 4
        // assign the random weight to the empty tile
        randomTile.value = weight
    }

    private fun getEmptyTiles(): ArrayList<Tile> {
        // initializing a list to store the empty tiles
        val emptyTiles = ArrayList<Tile>(16)

        // transversing the 2-D matrix to find all empty
        // tiles and add them to the emptyTiles list
        for (row in gameTiles!!){
            for (tile in row){
                if (tile.isEmpty())
                    emptyTiles.add(tile)
            }
        }

        return emptyTiles
    }

    fun resetGameTiles(){
        // Initializes the gameTiles reference attribute
        gameTiles = Array(FIELD_WIDTH) { Array(FIELD_WIDTH) { Tile() } }
        // Begin the game by add 2 weighted tiles to the board
        addTile()
        addTile()
    }

    fun printTiles(){
        for (row in gameTiles!!){
            for (tile in row){
                println(tile.getTileColor())
            }
        }
    }


}