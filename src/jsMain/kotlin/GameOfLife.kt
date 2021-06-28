import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*

val colors = mapOf<String, org.jetbrains.compose.common.core.graphics.Color>(
    "alive" to org.jetbrains.compose.common.core.graphics.Color(51, 149, 214),
    "dead" to org.jetbrains.compose.common.core.graphics.Color(81, 84, 82)
)


@Composable
fun gameOfLife() {
    val mySim = Simulation(5, 5)

    mySim.setAlive(2, 1)
    mySim.setAlive(2, 2)
    mySim.setAlive(2, 3)

    Div() {
        var simulation: Simulation by mutableStateOf(mySim)
        Button(attrs = { onClick {simulation.step()} }) {
            Text("Evolve")
        }
        for (i in 0 until 10) {
            Br()
            mySim.step()
            mySim.printConsoleBoard()
        }
    }
}
