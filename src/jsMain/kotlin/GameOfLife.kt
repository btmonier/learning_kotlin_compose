import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

val colors = mapOf<String, org.jetbrains.compose.common.core.graphics.Color>(
    "alive" to org.jetbrains.compose.common.core.graphics.Color(51, 149, 214),
    "dead" to org.jetbrains.compose.common.core.graphics.Color(81, 84, 82)
)


@Composable
fun gameOfLife() {
    val mySim = Simulation(20, 20)

    mySim.randomBoard()

    val simArray = arrayOfNulls<Simulation>(10)
    for (i in simArray.indices) {
        mySim.step()
        simArray[i] = mySim
    }

    var simulation: Simulation by mutableStateOf(mySim)
    var count: Int by mutableStateOf(0)
    Div() {
        Button(attrs = {
            onClick {
                simulation.step()
                count++
            }
        }) {
            Text("Evolve")
        }
        Br()
        Text("Generation: $count")
        Br()
        simulation.printBoard()
    }
}
