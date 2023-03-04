import java.awt.Color

data class Tile constructor (val value: Int = 0) {
    // return true if the value of the Tile weight is 0 else false.
    fun isEmpty(): Boolean {
        return value == 0
    }

    // returns a Color instance for the font based on the value of the Tile weight
    fun getFontColor(): Color =  if (value < 16)
        Color.decode("0x776e65") else Color.decode("0xf9f6f2")

    // returns a color instance for the background based on the value of the Tile weight.
    fun getTileColor(): Color = when (value){
        0-> Color.decode("0xcdc1b4")
        2-> Color.decode("0xeee4da")
        4-> Color.decode("0xede0c8")
        8-> Color.decode("0xf2b179")
        16-> Color.decode("0xf59563")
        32-> Color.decode("0xf67c5f")
        64-> Color.decode("0xf65e3b")
        128-> Color.decode("0xedcf72")
        256-> Color.decode("0xedcc61")
        512-> Color.decode("0xedc850")
        1024-> Color.decode("0xedc53f")
        2048-> Color.decode("0xedc22e")
        else-> Color.decode("0xff0000")
    }
}