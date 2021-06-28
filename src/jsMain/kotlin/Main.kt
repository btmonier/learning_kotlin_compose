import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.common.foundation.layout.*
import org.jetbrains.compose.common.internal.castOrCreate
import org.jetbrains.compose.common.ui.Modifier
import org.jetbrains.compose.common.ui.background
import org.jetbrains.compose.common.ui.padding
import org.jetbrains.compose.common.ui.size
import org.jetbrains.compose.common.ui.unit.Dp
import org.jetbrains.compose.common.ui.unit.dp
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable


object MyStyleSheet: StyleSheet() {
    val mainContainer by style {
        padding(5.px)
        border(1.px, LineStyle("solid"), Color.RGB(66, 135, 245))
        property("font-family", "Arial, Helvetica, sans-serif")
        fontSize(12.px)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
    }

    val counter by style {
        padding(25.px)
        backgroundColor(Color.RGB(227, 228, 250))
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
    }
    val golGrid by style {
        padding(25.px)
        backgroundColor(Color.RGB(200, 228, 250))
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

@Composable
fun simpleCounter() {
    var count: Int by mutableStateOf(0)
    Div({
        classes(MyStyleSheet.counter)
    }) {
        Button(attrs = {
            onClick { count -= 1 }
        }) {
            Text("-")
        }

        Span({ style { padding(15.px) } }) {
            Text("$count")
        }

        Button(attrs = {
            onClick { count += 1 }
        }) {
            Text("+")
        }
    }
}

@Composable
fun piece(x: Int, y: Int, col: org.jetbrains.compose.common.core.graphics.Color?) {
    val boxSize = 20.dp
    val offSet = 1.1f
    Box(
        Modifier
            .size(boxSize, boxSize)
            .position(Dp(boxSize.value * x * offSet + 30), Dp(boxSize.value * y * offSet + 200))
            .background(col!!)
            .padding(3.dp)
    ) {}
}

@Composable
fun Modifier.position(width: Dp, height: Dp): Modifier  = castOrCreate().apply {
    add {
        position(Position.Absolute)
        top(height.value.px)
        left(width.value.px)
    }
}

fun main() {
    renderComposable(rootElementId = "root") {
        Style(MyStyleSheet)
        mainContainer("Hello, this is extracted from a style-sheet object")
        simpleCounter()
        gameOfLife()
    }
}
