package com.example.layoutmodifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutmodifier.ui.theme.JetpackComposeTheme
import kotlin.math.roundToInt

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
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun MainScreen() {

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(120.dp, 80.dp)){

            Column() {
                Box(modifier = Modifier             // 기준점 (0.0)
                    .exampleLayout(0f)
                    .size(1.dp)
                    .background(color = Color.Black))

                ColorBox(modifier = Modifier
                    .exampleLayout(0f)
                    .background(Color.Blue))

                ColorBox(modifier = Modifier        // 자식을 왼쪽으로 옮김
                    .exampleLayout(0.25f)
                    .background(Color.Green))

                ColorBox(modifier = Modifier
                    .exampleLayout(0.5f)
                    .background(Color.Yellow))

                ColorBox(modifier = Modifier
                    .exampleLayout(0.25f)
                    .background(Color.Red))
                ColorBox(modifier = Modifier

                    .exampleLayout(0.0f)
                    .background(Color.Magenta))
            }

//        ColorBox(modifier = Modifier
//            .customLayout(100, 100)
//            .background(Color.Blue))
//
//        Box(modifier = Modifier
//            .padding(1.dp)
//            .size(100.pxToDp(), 100.pxToDp())
//            .background(Color.Yellow.copy(alpha = 0.2f)))//
    }
}

@Composable
fun ColorBox(modifier: Modifier) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(width = 50.dp, height = 10.dp)
            .then(modifier))
}

fun Modifier.exampleLayout(
    fraction : Float
) = layout {measurable, constraints ->
    val placeable = measurable.measure(constraints)

    val x = -(placeable.width * fraction).roundToInt()
    // placeable 로 부터 폭을 받아서 fraction 파라미터 값을 곱하고 정수 값 올림
    // 정렬 선을 오른쪽으로 옮김 -> 자식을 왼쪽으로 옮김 과 같음, x값 음수로

    layout(placeable.width, placeable.height){
        placeable.placeRelative(x = x , y = 0)
    }
}

//fun Modifier.exampleLayout (   // Modifier.커스텀 레이아웃 이름 (
//    x : Int,                   // 선택적 파라미터 )
//    y : Int
//) = layout {measurable, constraints ->    // layout 뒤 람다는 measurable(측정)측정할 수 있는 요소, constraints(제약) 크기 조정 코드 전달
//    // measurable : 해당 모디파이어가 호출 된 자식이 배치 될 정보,  constraints : 자식이 이용할 수 있는 최대/최소 폭, 높이 포함
//
//    val placeable = measurable.measure(constraints)
//    // 측정 값은 measurable 인스턴스의 measure() 메서드 호출 해 얻을 수 있으며, 제약(constraints) 객체를 통해 전달.
//    // 호출 결과로 하나의 Placeable 인스턴스 반환, 높이와 폭 값을 갖는다.
//
//    // placeable 인스턴스의 메서드 호출 해 부모 콘텐츠 영역 안에 있는 요소의 새로운 위치를 지정 할 수 있다.
//    // 커스텀 레이아웃 개발 시 모디파이어가 호출될 때 마다 자식 측정 규칙 적용 : 싱글 패스 측정
//
//    layout(placeable.width, placeable.height){
//        placeable.placeRelative(x,y)
//    }
//    // layout() 호출, placeable 값으로 부터 높이와 폭을 전달.  placeable 값으로 부터 높이와 폭을 전달 (px)
//    // placeable 객체의 placeRelative() 호출 자식 요소 지정
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        MainScreen()
    }
}