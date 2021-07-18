import kotlin.math.PI
import kotlin.math.sqrt

class Particle(var x: Double, var y: Double, var mouse: Mouse) {
    val baseX = x
    val baseY = y
    var size = 2
    val density = (10..30).random()
    var color = "black"

    fun draw() {
        ctx.fillStyle = color
        ctx.beginPath()
        ctx.arc(x.toDouble(), y.toDouble(), size.toDouble(), 0.0, PI * 2)
        ctx.closePath()
        ctx.fill()
    }

    fun update() {
        val dx = mouse.x - this.x
        val dy = mouse.y - this.y
        val distance = sqrt((dx * dx).toDouble() + (dy * dy).toDouble())
        val forceDirectionX = dx / distance
        val forceDirectionY = dy / distance
        val maxDistance = mouse.radius
        val force = (maxDistance - distance) / maxDistance
        val directionX = forceDirectionX * force * density
        val directionY = forceDirectionY * force * density

        if (distance < mouse.radius) {
            this.x -= directionX
            this.y -= directionY
            color = "red"
        } else {
            color = "black"
        }
    }
}