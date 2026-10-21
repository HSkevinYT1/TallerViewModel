package me.kevinbayona.viewmodelapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class ContadorUiState(
    val contador: Int = 0,
    val mensaje: String = "Presiona un botón"
)


class ContadorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ContadorUiState())

    val uiState: StateFlow<ContadorUiState> = _uiState.asStateFlow()

    fun incrementar() {
        _uiState.update { estado ->
            val nuevo = estado.contador + 1
            estado.copy(contador = nuevo, mensaje = mensajePara(nuevo))
        }
    }

    fun decrementar() {
        _uiState.update { estado ->
            val nuevo = estado.contador - 1
            estado.copy(contador = nuevo, mensaje = mensajePara(nuevo))
        }
    }

    fun reiniciar() {
        _uiState.value = ContadorUiState()
    }

    private fun mensajePara(n: Int): String = when {
        n > 0 -> "El número es positivo"
        n < 0 -> "El número es negativo"
        else -> "El número es cero"
    }
}
