package me.kevinbayona.viewmodelapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * 1) EL ESTADO
 * Una clase de datos con TODO lo que la pantalla necesita mostrar.
 * Es inmutable (val): para cambiarlo se crea una copia nueva con .copy().
 */
data class ContadorUiState(
    val contador: Int = 0,
    val mensaje: String = "Presiona un botón"
)

/**
 * 2) EL VIEWMODEL
 * - Guarda el estado y contiene la lógica.
 * - Sobrevive a cambios de configuración (por ejemplo, girar el teléfono),
 *   por eso el contador NO se pierde al rotar la pantalla.
 * - La vista nunca modifica el estado directamente: solo llama funciones.
 */
class ContadorViewModel : ViewModel() {

    // Estado privado y modificable (solo el ViewModel puede cambiarlo)
    private val _uiState = MutableStateFlow(ContadorUiState())

    // Estado público de solo lectura (la vista solo puede observarlo)
    val uiState: StateFlow<ContadorUiState> = _uiState.asStateFlow()

    // 3) EVENTOS: la vista llama estas funciones y el ViewModel actualiza el estado
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
