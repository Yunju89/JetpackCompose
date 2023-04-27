package com.example.rowcoldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rowcoldemo.ui.theme.JetpackComposeTheme

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
    Column(
        modifier = Modifier
            .padding(10.dp)
            .border(width = 2.dp, color = Color.Black)
    ) {
        Row(
            modifier = Modifier.height(200.dp)
        ) {
            TextCell(
                text = "1",
                Modifier
                    .align(Alignment.Top)
            )
            TextCell(
                text = "2",
                Modifier
                    .align(Alignment.CenterVertically)
            )
            TextCell(
                text = "3",
                Modifier
                    .align(Alignment.Bottom)
            )
        }

        Column(
            modifier = Modifier.width(200.dp)
        ) {
            TextCell(
                text = "1",
                Modifier
                    .align(Alignment.Start)
            )
            TextCell(
                text = "2",
                Modifier
                    .align(Alignment.CenterHorizontally)
            )
            TextCell(
                text = "3",
                Modifier
                    .align(Alignment.End)
            )
        }

        Row {
            TextCell(
                text = "1",
                Modifier.weight(weight = 0.2f, fill = true)
            )
            TextCell(
                text = "2",
                Modifier.weight(weight = 0.4f, fill = true)     // 가중치
            )
            TextCell(
                text = "3",
                Modifier.weight(weight = 0.3f, fill = true)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(
                text = "Large Text \n More Text",
                Modifier.alignBy(LastBaseline),     // 마지막 텍스트의 베이스 라인에 맞춤 (Column scope 에는 베이스라인 개념X)
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Small Text",
                Modifier.alignByBaseline(),         // 베이스라인에 맞춤
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier) {
    val cellModifier = Modifier
        .padding(4.dp)
        .size(100.dp)
        .border(width = 4.dp, color = Color.Black)

    Text(
        text = text,
        cellModifier.then(modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MainScreen()
}