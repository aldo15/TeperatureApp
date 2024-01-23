package com.hsbc.temperatureapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsbc.temperatureapp.TemperatureData
import com.hsbc.temperatureapp.TemperatureViewModel
import com.hsbc.temperatureapp.ui.theme.TeperatureAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: TemperatureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTemperatureData()
        setContent {
            TeperatureAppTheme {
                Surface( ) {
                    TemperatureScreen(viewModel)
                }
            }
        }
    }
}


@Composable
fun TemperatureScreen(viewModel: TemperatureViewModel) {
    val currentTemperature: TemperatureData by viewModel.currentTemperature.observeAsState(initial = TemperatureData("--", 0, "C"))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column() {
            Row(Modifier.height(30.dp)) {
                Text( text = "Location: ")
                Text( text = currentTemperature.place,
                    Modifier.fillMaxWidth()
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(5.dp))
                        .padding(5.dp))

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.height(30.dp)) {
                Text( text = "Temperature: ")
                Text( text = "${currentTemperature.value} ${currentTemperature.unit}",
                    Modifier.fillMaxWidth()
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(5.dp))
                        .padding(5.dp))
            }
        }


        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { viewModel.fetchTemperatureData() }) {
            Text(text = "Next Random Location")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TeperatureAppTheme {
       //TemperatureScreen(viewModel = )
    }
}