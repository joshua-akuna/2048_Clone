import com.sun.org.apache.xpath.internal.operations.Bool
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
    private var score: Int = 0
        get() = field
    private var maxTile = 0
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

    fun consolidateTiles(tiles: Array<Tile>): Boolean{
        var insert = 0
        var index = 0
        var consolidated = false

        // sort the array by move all empty tiles to the right
        while (index < tiles.size){
            if (!tiles[index].isEmpty() && index != insert){
                tiles[insert++] = Tile(tiles[index].value)
                tiles[index] = Tile()
                consolidated = true
            }
            index++
        }

        return consolidated
    }

    private fun mergeTiles(tile: Array<Tile>): Boolean{
        var i = 0
        var merged = false

        while (i < tile.size - 1){
            if (tile[i].isEmpty()) {
                i++
                continue
            }

            if(tile[i].value == tile[i + 1].value){
                // add the values of consecutive tiles if their weight is equal
                var currentVal = tile[i].value * 2
                // updates the maxTile attribute
                maxTile = if (currentVal > maxTile) currentVal else maxTile
                // updates the weight of the current tile
                tile[i] = Tile(currentVal)
                // updates the current score of the game
                score += currentVal
                // moves the remaining tiles to the left
                var j = i + 1
                while (j < tile.size - 1){
                    tile[j] = Tile(tile[j + 1].value)
                    j++
                }
                tile[tile.size - 1] = Tile()
                merged = true
            }
            i++
        }
        return merged
    }

    fun left(){
        var willAddTile = false

        for (row in gameTiles!!){
            if (consolidateTiles(row) || mergeTiles(row))
                willAddTile = true
        }

        if (willAddTile) addTile()
    }

    fun resetGameTiles(){
        // Initializes the gameTiles reference attribute
        gameTiles = Array(FIELD_WIDTH) { Array(FIELD_WIDTH) { Tile() } }
        // Begin the game by add 2 weighted tiles to the board
        addTile()
        addTile()
    }
}