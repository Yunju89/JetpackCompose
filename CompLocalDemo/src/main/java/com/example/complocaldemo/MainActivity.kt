package com.example.complocaldemo

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.complocaldemo.ui.theme.JetpackComposeTheme

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
                    Composable1()
                }
            }
        }
    }
}

//val LocalColor = compositionLocalOf { Color.Red }           // 현재 상태에 접근하는 컴포저블만 재 구성 (자주 변경될 때)
val LocalColor = staticCompositionLocalOf { Color.Red }       // 자주 변경되지 않는 상태 값 저장, 기본 값 Red 저장

@Composable
fun Composable1() {
    var color = if (isSystemInDarkTheme()){
        Color(0xFFa08d87)
    }else{
        Color(0xFFffdbcf)
    }
    Column {
        Text(text = "Composable 1", modifier = Modifier.background(LocalColor.current))

        Composable2()

        CompositionLocalProvider(LocalColor provides color) {
            // 상위 ~ 하위까지 Composition Local 전달 (CompositionProvider)
            // 3,5,7,8 Composable에 ColorState 전달
            Composable3()
        }
    }
}

@Composable
fun Composable2() {
    Composable4()
}

@Composable
fun Composable3() {
    Text(text = "Composable 3", Modifier.background(LocalColor.current))
    CompositionLocalProvider(LocalColor provides Color.Green) {
        Composable5()
    }
}

@Composable
fun Composable4() {
    Composable6()
}

@Composable
fun Composable5() {
    Text(text = "Composable 5", modifier = Modifier.background(LocalColor.current))
    Composable7()
    CompositionLocalProvider(LocalColor provides Color.Yellow) {
        Composable8()
    }
}

@Composable
fun Composable6() {
    Text(text = "Composable 6", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable7() {

}

@Composable
fun Composable8() {
    Text(text = "Composable 8", modifier = Modifier.background(LocalColor.current))
    // 최상단 (Composable1() 에 선언 된 데이터를 하위 에서 사용할 수 있다)
}

@Preview
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
    JetpackComposeTheme {
        Composable1()
    }
}