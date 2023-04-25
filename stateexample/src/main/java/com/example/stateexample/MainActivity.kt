package com.example.stateexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stateexample.MainActivity.Companion.TAG
import com.example.stateexample.ui.theme.JetpackComposeTheme
import kotlin.jvm.internal.FunctionAdapter

class MainActivity : ComponentActivity() {
    companion object{
        val TAG = MainActivity::class.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        DemoScreen()
                        FunctionA()
                    }
                }
            }
        }
    }
}

@Composable
fun DemoScreen() {
    var textState by rememberSaveable { mutableStateOf("") }    // by 키워드 - 코틀린 프로퍼티 위임 - 간결, 가장 많이 사용 / rememberSaveable : 환경설정 변경 유지 (화면 회전)

    Log.d(TAG, "MyTextField2 $textState")

    val onTextChange = { text: String ->
        textState = text    // .value - 값 프로퍼티 참조 없이 상태값 접근 가능, 간결한 코드
    }

    MyTextField(textState, onTextChange)
}
@Composable
fun MyTextField(text : String, onTextChange : (String) -> Unit) {  // 재 사용 가능
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = onTextChange
    )
}

@Composable
fun MyTextField1() {
    var textState = remember { mutableStateOf("") }    // remember - 현재 상태 값 유지

    val onTextChange = { text: String ->
        textState.value = text
    }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState.value,
        onValueChange = onTextChange
    )
}

@Composable
fun MyTextField2() {
    var (textValue, setText) =  remember { mutableStateOf("") }    // 구조분해, mutableState : operator fun component1(): T , operator fun component2(): (T) -> Unit

    val onTextChange = { text: String ->
        setText(text)    // .value - 값 프로퍼티 참조 없이 상태값 접근 가능, 간결한 코드
    }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue,
        onValueChange = onTextChange
    )
}

@Composable
fun FunctionA() {
    var switchState by remember {
        mutableStateOf(true)        // true 값으로 초기화
    }
    Log.d(TAG, "FunctionA $switchState")
    val onSwitchChange = {value : Boolean ->        // 이벤트 핸들러 호출 시 switchState 값 할당
        switchState = value
    }
    
    FunctionB(switchState = switchState, onSwitchChange = onSwitchChange)
}

@Composable
fun FunctionB(switchState : Boolean, onSwitchChange : (Boolean) -> Unit) {
    Switch(
        checked = switchState,
        onCheckedChange = onSwitchChange
    )
}

@Preview
@Composable
fun Preview(){
    JetpackComposeTheme {
        Column {
            DemoScreen()
            FunctionA()
        }
    }
}


