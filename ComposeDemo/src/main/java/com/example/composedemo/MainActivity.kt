package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.JetpackComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {   // 원하는 테마를 지정, 적용할 수 있다
                Surface(            // 내장 컴포넌트, 다른 컴포저블의 배경으로 사용
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun DemoText(message: String, fontSize: Float) {
    Text(
        text = message,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun DemoSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit) {
    Slider(
        modifier = Modifier.padding(10.dp),     // 프로퍼티 설정
        valueRange = 20f..40f,                  // 슬라이더 값의 범위 지정
        value = sliderPosition,                 // 슬라이더의 값을 호출자가 전달한 위치로 설정
        onValueChange = onPositionChange        // 슬라이더 위치 변경 시 콜백
    )
}

@Composable
fun DemoScreen() {
    var sliderPosition by remember { mutableStateOf(20f) }    // 슬라이더 위치 저장

    val handlePositionChange =
        { position: Float ->                // 현재 슬라이더 위치와 DemoSlider에 전달 할 이벤트 핸들러 구현
            sliderPosition = position
        }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        DemoText(message = "welcome Compose", fontSize = sliderPosition)

        Spacer(modifier = Modifier.height(150.dp))

        DemoSlider(sliderPosition = sliderPosition, onPositionChange = handlePositionChange)

        Text(
            text = sliderPosition.toInt().toString() + "sp",
            style = MaterialTheme.typography.h2
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {

    }
}