import androidx.compose.runtime.Composable
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.MessageEvent
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.MouseEventInit

//fun makeParticles() {
//    val mouse = object {
//        var x = 0
//        var y = 0
//        var radius = 35
//    }
//
//    ctx.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
//    canvas.addEventListener("mousemove", EventListener {
//        val event = it as MouseEvent
//        mouse.x = event.clientX
//        mouse.y = event.clientY
//        //println("X: ${mouse.x}\tY: ${mouse.y}")
//    })
////    println("${mouse.x}, ${mouse.y}")
//}


var particleArray: MutableList<Particle> = mutableListOf()

@Composable
fun init() {
    for (i in 0 until 10) {
        val x = (1..canvas.width).random()
        val y = (1..canvas.height).random()
        particleArray.add(Particle(x, y))
    }
}


fun animate() {
    ctx.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
    particleArray.forEach {
        it.draw()
    }

    window.requestAnimationFrame {
        animate()
    }
    println("${particleArray.size}")
}



