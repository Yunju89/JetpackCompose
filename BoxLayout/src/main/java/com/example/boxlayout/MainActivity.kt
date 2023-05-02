package com.example.boxlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boxlayout.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column() {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.size(height = 90.dp, width = 290.dp)
        ) {
            Text(text = "TopStart", Modifier.align(Alignment.TopStart))
            Text(text = "TopCenter", Modifier.align(Alignment.TopCenter))
            Text(text = "TopEnd", Modifier.align(Alignment.TopEnd))

            Text(text = "CenterStart", Modifier.align(Alignment.CenterStart))
            Text(text = "Center", Modifier.align(Alignment.Center))
            Text(text = "CenterEnd", Modifier.align(Alignment.CenterEnd))

            Text(text = "BottomStart", Modifier.align(Alignment.BottomStart))
            Text(text = "BottomCenter", Modifier.align(Alignment.BottomCenter))
            Text(text = "BottomEnd", Modifier.align(Alignment.BottomEnd))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row() {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.Blue)
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CutCornerShape(15.dp))
                    .background(Color.Blue)
            )
        }
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier, fontSize: Int = 150) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(width = 5.dp, color = Color.Black)

    Text(
        text = text,
        modifier = cellModifier.then(modifier),
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // A surface container using the 'background' color from the theme
    MainScreen()

}