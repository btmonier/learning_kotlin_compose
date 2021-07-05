import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*



@Composable
fun gameOfLife(nCol: Int, nRow: Int) {
    val mySim = Simulation(nCol, nRow)

    mySim.randomBoard()

    val simulation: Simulation by mutableStateOf(mySim)
    var count: Int by mutableStateOf(0)
    Div(attrs = {classes(MyStyleSheet.description)}){
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
