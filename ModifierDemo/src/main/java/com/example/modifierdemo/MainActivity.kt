package com.example.modifierdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.modifierdemo.ui.theme.JetpackComposeTheme

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
fun DemoScreen() {
    val modifier = Modifier                         // 먼저 작성한 코드가 우선 적용
        .border(width = 2.dp, color = Color.Black)  // 경계를 그리고
        .padding(all = 10.dp)                       // 적용된 모든 컴포저블 상하좌우에 10dp 패딩

    val secondModifier = Modifier
        .height(100.dp)

    Column(
        Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello Compose",
            modifier = modifier.then(secondModifier),   // 모디파이어 조합 (then)
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomImage(
            image = R.drawable.vacation,
            modifier = Modifier
                .padding(16.dp)
                .width(270.dp)
                .clip(shape = RoundedCornerShape(30.dp))
        )
    }

}

@Composable
fun CustomImage(
    image: Int,
    modifier: Modifier = Modifier
) {   // 함수에서 modifier 받기 : 반드시! 첫번째 선택적 파라미터
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    JetpackComposeTheme {
        DemoScreen()
    }
}
