package com.realityexpander.composelayouttests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                    ) {
                        LayoutModifierExample()
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
            .fillMaxWidth()
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
        Greeting("Android2")

    }
}