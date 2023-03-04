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
}