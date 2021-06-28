import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Text


class Simulation(var width: Int, var height: Int) {
    var board: Array<IntArray>

    fun randomBoard() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                val state = arrayOf(0, 1).random()
                if (state == 1) {
                    setAlive(j, i)
                }
            }
        }
    }

    @Composable
    fun printBoard() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (getState(j, i) == 1) {
                    piece(j, i, colors["alive"])
                } else {
                    piece(j, i, colors["dead"])
                }
            }
        }
    }

    @Composable
    fun printWebBoard() {
        for (i in 0 until height) {
            Text("${board[i]}")
            Br()
        }
    }

    fun setAlive(x: Int, y: Int) {
        board[x][y] = 1
    }

    fun setDead(x: Int, y: Int) {
        board[x][y] = 0
    }

    fun countAliveNeighbours(x: Int, y: Int): Int {
        var count = 0
        count += getState(x - 1, y - 1)
        count += getState(x, y - 1)
        count += getState(x + 1, y - 1)
        count += getState(x - 1, y)
        count += getState(x + 1, y)
        count += getState(x - 1, y + 1)
        count += getState(x, y + 1)
        count += getState(x + 1, y + 1)
        return count
    }

    fun getState(x: Int, y: Int): Int {
        if (x < 0 || x >= width) {
            return 0
        }
        return if (y < 0 || y >= height) {
            0
        } else board[x][y]
    }

    fun step() {
        val newBoard = Array(width) { IntArray(height) }
        for (y in 0 until height) {
            for (x in 0 until width) {
                val aliveNeighbours = countAliveNeighbours(x, y)
                if (getState(x, y) == 1) {
                    if (aliveNeighbours < 2) {
                        newBoard[x][y] = 0
                    } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        newBoard[x][y] = 1
                    } else if (aliveNeighbours > 3) {
                        newBoard[x][y] = 0
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

    init {
        board = Array(width) { IntArray(height) }
    }
}