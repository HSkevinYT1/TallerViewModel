package me.kevinbayona.viewmodelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.kevinbayona.viewmodelapp.ui.theme.ViewModelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelAppTheme {
                ContadorScreen()
            }
        }
    }
}

@Composable
fun ContadorScreen(vm: ContadorViewModel = viewModel()) {

    val estado by vm.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().systemBarsPadding().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Ejemplo de ViewModel", fontSize = 22.sp)
        Spacer(Modifier.height(24.dp))

        Text("${estado.contador}", fontSize = 64.sp)
        Text(estado.mensaje)

        Spacer(Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { vm.decrementar() }) { Text("−") }
            Button(onClick = { vm.incrementar() }) { Text("+") }
        }
        Spacer(Modifier.height(12.dp))
        OutlinedButton(onClick = { vm.reiniciar() }) { Text("Reiniciar") }

        Spacer(Modifier.height(24.dp))
        Text("Gira el teléfono: el número no se pierde porque vive en el ViewModel.")
    }
}
