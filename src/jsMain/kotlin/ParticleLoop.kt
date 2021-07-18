import androidx.compose.runtime.Composable
import kotlinx.browser.window

var particleArray: MutableList<Particle> = mutableListOf()

@Composable
fun init(n: Int, mouse: Mouse) {
    for (i in 0 until n) {
        val x = (1..canvas.width).random()
        val y = (1..canvas.height).random()
        particleArray.add(Particle(x.toDouble(), y.toDouble(), mouse))
    }
}

fun animate() {
    ctx.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
    particleArray.forEach {
        it.draw()
        it.update()
    }

    window.requestAnimationFrame {
        animate()
    }
}
