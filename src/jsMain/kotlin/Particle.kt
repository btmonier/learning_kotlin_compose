import androidx.compose.runtime.Composable
import kotlin.math.PI

class Particle(private val x: Int, private val y: Int) {
    val baseX = x
    val baseY = y
    val size = 3
    val density = (1..30).random()


    fun draw() {
        ctx.fillStyle = "black"
        ctx.beginPath()
        ctx.arc(x.toDouble(), y.toDouble(), size.toDouble(), 0.0, PI * 2)
        ctx.closePath()
        ctx.fill()
    }
}