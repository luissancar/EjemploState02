package com.example.borrar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.borrar.ui.theme.BorrarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BorrarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var numero by rememberSaveable { mutableStateOf("5") }
    var numeroTF by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(numero, fontSize = 50.sp)
        Row(
            Modifier.fillMaxWidth().padding(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonAddSub("+", numero = numero) { numero = it.toString() }
            ButtonAddSub("-", numero = numero) { numero = it.toString() }
        }
        LeeDato(numero = numeroTF) { numeroTF = it }
        ButtonSet() { numero = numeroTF }
    }

}


@Composable
fun ButtonSet(resultado: () -> Unit) {
    Button(
        onClick = {
            resultado()
        },
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text("Set", fontSize = 90.sp)
    }
}


@Composable
fun ButtonAddSub(operacion: String, numero: String, resultado: (Int) -> Unit) {
    Button(
        onClick = {
            try {
                if (operacion == "+")
                    resultado(numero.toInt() + 1)
                else
                    resultado(numero.toInt() - 1)
            } catch (e: Exception) {
                Log.d("error", "Error resta")
                resultado(0)
            }
        },


        modifier = Modifier
            .padding(20.dp)

    ) {

        Text(operacion, fontSize = 70.sp)
    }
}


@Composable
fun LeeDato(numero: String, onTextChange: (String) -> Unit) {
    TextField(
        value = numero, onValueChange = {
            if (it.isDigitsOnly())
                onTextChange(it)
        }, textStyle = TextStyle(
            fontSize = 40.sp,
            textAlign = TextAlign.End // Alineación del texto a la derecha
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )

}
