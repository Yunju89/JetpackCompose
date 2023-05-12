package com.example.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.customlayout.ui.theme.JetpackComposeTheme

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

                }
            }
        }
    }
}

@Composable
fun CascadeLayout(
    modifier: Modifier = Modifier,
    spacing: Int = 0,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content
    ) {
            measurable, constraints ->      // 측정값, 제약 (제약 조건은 element(요소) 가 가질 수 있는 width, height 에 대한 min,max 범위)
        var xCoord = 0
        var yCoord = 0

        layout(constraints.maxWidth, constraints.maxHeight) {    // 제약 범위의 maxWidth, maxHeight

            val placeables = measurable.map { measurable ->     // 자식들 측정 List
                measurable.measure(constraints)
            }
            placeables.forEach { placeable ->
                placeable.placeRelative(x = xCoord, y = yCoord)     // 배치할 위치 지정 (x좌표, y좌표)
                xCoord += placeable.width + spacing                 // 좌표에 간격 추가
                yCoord += placeable.height + spacing
            }
        }
    }
}

@Composable
fun CustomLayout(
    content : @Composable () -> Unit,
    modifier: Modifier = Modifier
){
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = object : MeasurePolicy {    // MeasurePolicy : Layout 측정 및 동작 정의
            override fun MeasureScope.measure(
                measurables: List<Measurable>,      // 자식을 측정
                constraints: Constraints            // 제약 조건 측정
            ): MeasureResult {

                val placeable = measurables.map { measurable ->
                    // 측정, 레이아웃 정의, 반환된 placeable 들은 측정 된 width, height 값을 가진다.
                    measurable.measure(constraints)
                }

                return layout(constraints.maxWidth, constraints.maxHeight) {
                    placeable.forEach {
                        it.placeRelative(x = 0, y = 0)
                    }
                }
            }
        }
    )
}

@Composable
fun MainScreen(){
    Box(){
        CascadeLayout(spacing = 20) {
            Box(modifier = Modifier.size(60.dp).background(Color.Blue))
            Box(modifier = Modifier.size(80.dp).background(Color.Red))
            Box(modifier = Modifier.size(90.dp).background(Color.Cyan))
            Box(modifier = Modifier.size(50.dp).background(Color.Magenta))
            Box(modifier = Modifier.size(70.dp).background(Color.Green))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        MainScreen()
    }
}