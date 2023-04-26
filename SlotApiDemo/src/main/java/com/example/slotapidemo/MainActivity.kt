package com.example.slotapidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slotapidemo.ui.theme.JetpackComposeTheme

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
    var linearSelected by remember { mutableStateOf(true) }     // 상태

    var imageSelected by remember { mutableStateOf(true) }

    val onLinearClick = { value: Boolean ->                           // 이벤트 핸들러
        linearSelected = value
    }

    val onTitleClick = { value: Boolean ->
        imageSelected = value
    }

    ScreenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        onLinearClick = onLinearClick,
        onTitleClick = onTitleClick,
        titleContent = {
            if(imageSelected){
                TitleImage(drawing = R.drawable.baseline_cloud_download_24)
            }else{
                Text(text = "Downloading",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(30.dp))
            }
        },
        progressContent = {
            if(linearSelected){
                LinearProgressIndicator(Modifier.height(40.dp))                         // LinearProgress
            }else{
                CircularProgressIndicator(Modifier.size(200.dp), strokeWidth = 18.dp)   // CircularProgress
            }
        }
    )
}

@Composable()
fun ScreenContent(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onLinearClick: (Boolean) -> Unit,
    onTitleClick: (Boolean) -> Unit,
    titleContent: @Composable () -> Unit,
    progressContent: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,     // 열의 자식 요소들이 균등하게 공간 차지
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        titleContent()
        progressContent()
        CheckBoxes(
            linearSelected = linearSelected,
            imageSelected = imageSelected,
            onLinearClick = onLinearClick,
            onTitleClick = onTitleClick
        )
    }
}

@Composable
fun TitleImage(drawing: Int) {
    Image(painter = painterResource(id = drawing), contentDescription = "title image" )
}

@Composable
fun CheckBoxes(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onLinearClick: (Boolean) -> Unit,
    onTitleClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = imageSelected, onCheckedChange = onTitleClick)
        Text(text = "Image Title")
        Spacer(modifier = Modifier.width(20.dp))
        Checkbox(checked = linearSelected, onCheckedChange = onLinearClick)
        Text(text = "Linear Progress")
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    Surface {
        MainScreen()
    }
}