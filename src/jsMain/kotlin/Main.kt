import androidx.compose.runtime.Composable
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

val canvas = document.getElementById("myCanvas") as HTMLCanvasElement
val ctx = canvas.getContext("2d") as CanvasRenderingContext2D

object MyStyleSheet: StyleSheet() {
    val mainContainer by style {
        padding(5.px)
        border(1.px, LineStyle("solid"), Color.RGB(66, 135, 245))
        property("font-family", "Arial, Helvetica, sans-serif")
        fontSize(12.px)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
    }
    val description by style {
        padding(5.px)
        property("font-family", "Arial, Helvetica, sans-serif")
    }
}

@Composable
fun mainContainer(input: String) {
    Div(attrs = {
        classes(MyStyleSheet.mainContainer) /* use `container` class */
    }) {
        Text(input)
    }

}

fun main() {
    document.createElement("canvas")
    renderComposable(rootElementId = "root") {
        Style(MyStyleSheet)
        mainContainer("Hello, this is extracted from a style-sheet object")
        Div(attrs = {
            classes(MyStyleSheet.description)
        }) {
            H1 {
                Text("The Game of Life using Kotlin Compose")
            }
            P {
                Text(
                    """
                      This is an interpretation of John Conway's 'Game of Life' using Kotlin Compose. 
                      Click the 'Evolve' button to go up a generation  
                    """.trimIndent()
                )
            }
        }
        gameOfLife(50, 50)
    }
}
