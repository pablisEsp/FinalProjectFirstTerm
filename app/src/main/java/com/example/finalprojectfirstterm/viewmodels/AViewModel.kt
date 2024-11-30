package com.example.finalprojectfirstterm.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectfirstterm.models.Cita
import com.example.finalprojectfirstterm.room.ADataBaseDAO
import com.example.finalprojectfirstterm.states.AState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AViewModel(
    private val dao: ADataBaseDAO

) : ViewModel() {
    var state by mutableStateOf(AState())
        private set

    init {
        viewModelScope.launch {
            dao.obtenerCitas().collectLatest {
                state = state.copy(listaCitas = it)
            }
        }
    }

    fun agregarCita(cita: Cita) = viewModelScope.launch {
        dao.agregarCita(cita)
    }

    fun actualizarCita(cita: Cita) = viewModelScope.launch {
        dao.actualizarCita(cita)
    }

    fun eliminarCita(cita: Cita) = viewModelScope.launch {
        dao.eliminarCita(cita)
    }
}