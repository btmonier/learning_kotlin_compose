import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Text

class Simulation(private var nCol: Int, private var nRow: Int) {
    private var board: Array<IntArray>
    init {
        board = Array(nCol) { IntArray(nRow) }
    }

    private val height = canvas.height.toDouble()
    private val width = canvas.width.toDouble()
    private val cellWidth = width / nCol
    private val cellHeight = height / nRow

    private val colors = mapOf(
        "alive" to "#4287f5",
        "dead" to "#caddfc"
    )

    // Data for coordinates of adjacent cell neighbors
    private val mooreNeighbor = arrayOf(
        Pair(-1, -1),
        Pair(0, -1),
        Pair(1, -1),
        Pair(-1, 0),
        Pair(1, 0),
        Pair(-1, 1),
        Pair(0, 1),
        Pair(1, 1)
    )

    fun randomBoard() {
        for (i in 0 until nRow) {
            for (j in 0 until nCol) {
                val state = arrayOf(0, 1).random()
                if (state == 1) {
                    setAlive(j, i)
                }
            }
        }
    }

    @Composable
    fun printBoard() {
        ctx.fillStyle = colors["dead"]
        ctx.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        for (i in 0 until nRow) {
            for (j in 0 until nCol) {
                if (getState(j, i) == 1) {
                    ctx.fillStyle = colors["alive"]
                    ctx.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight)
                }
            }
        }
    }

    @Composable
    fun printWebBoard() {
        for (i in 0 until nRow) {
            Text("${board[i]}")
            Br()
        }
    }

    private fun setAlive(x: Int, y: Int) {
        board[x][y] = 1
    }

    private fun setDead(x: Int, y: Int) {
        board[x][y] = 0
    }

    private fun countAliveNeighbours(x: Int, y: Int): Int {
        var count = 0

        mooreNeighbor.forEach {
            count += getState(x + it.first, y + it.second)
        }

        return count
    }

    private fun getState(x: Int, y: Int): Int {
        return if (x < 0 || x >= nCol || y < 0 || y >= nRow) {
            0
        }  else {
            board[x][y]
        }
    }

    fun step() {
        val newBoard = Array(nCol) { IntArray(nRow) }
        for (y in 0 until nRow) {
            for (x in 0 until nCol) {
                val aliveNeighbours = countAliveNeighbours(x, y)
                if (getState(x, y) == 1) {
                    if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        newBoard[x][y] = 1
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        newBoard[x][y] = 1
                    }
                }
            }
        }
        board = newBoard
    }
}