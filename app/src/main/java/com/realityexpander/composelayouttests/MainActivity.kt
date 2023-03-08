package com.realityexpander.composelayouttests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.realityexpander.composelayouttests.ui.theme.ComposeLayoutTestsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutTestsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray)
                            .padding(16.dp)
                    ) {
                        Column {
                            InstrinsicExample()
                            //LayoutModifierExample()
//                            TwoTexts(text1 = "Hi", text2 = "there")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String, modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        color = Color.Black,
        modifier = modifier
            .padding(top = 16.dp)
            .background(Color.Red)
//            .fillMaxWidth()
            .width(150.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLayoutTestsTheme {
        Greeting("Android")
    }
}

@Composable
fun LayoutModifierExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(48.dp)
    ) {
        Greeting("Android1")
        Greeting("Android2")
        Greeting(
            modifier = Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(
                    constraints.copy(
                        maxWidth = constraints.maxWidth + 90.dp.roundToPx(), // overrides incoming constraint, 80dp wider than the parent container (that passed in the contraints).
                    )
                )

                layout(placeable.width, placeable.height) {
                    //placeable.placeRelative(0, 0)
                    placeable.place(-100, 600)
                }
            },
            name = "Android3"
        )
        Greeting("Android2", modifier = Modifier.requiredWidth(200.dp))
        Greeting("Android2", modifier = Modifier.width(250.dp))

    }
}

@Preview
@Composable
fun InstrinsicExample() {
    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .width(IntrinsicSize.Max) // this will make the column only as wide as the widest child (words
    ) {
        Text("a",
            color = Color.Black, modifier = Modifier.background(Color.Yellow)
                .fillMaxWidth()
        )
        Text("a a a a a a",
            color = Color.Black, modifier = Modifier.background(Color.Yellow)
                .fillMaxWidth()
        )
        Text("a a a",
            color = Color.Black, modifier = Modifier.background(Color.Yellow)
                .fillMaxWidth()
        )
        Text("+",
           color = Color.Black, modifier = Modifier.background(Color.Yellow)
                .fillMaxWidth()
        )
        Text("x",
            color = Color.Black, modifier = Modifier.background(Color.Yellow)
                .fillMaxWidth()
        )

        Greeting2("Android1")
        Greeting2("Android2 and abcdef")
        Greeting2("Android3 efg")
        Greeting2("Android4 jklmnop")

    }
}

@Composable
fun Greeting2(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$text!",
        color = Color.Black,
        modifier = modifier
            .padding(top = 16.dp)
            .background(Color.Yellow)
//            .wrapContentWidth()
    )
}


@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )
        Divider(
            color = Color.Black,
            modifier = Modifier.fillMaxHeight().width(1.dp)
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),
            text = text2
        )
    }
}

 @Preview
@Composable
fun TwoTextsPreview() {
    MaterialTheme {
        Surface {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}