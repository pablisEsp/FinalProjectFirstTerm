package com.example.finalprojectfirstterm.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.finalprojectfirstterm.models.Cita
import kotlinx.coroutines.flow.Flow

@Dao
interface ADataBaseDAO {
    @Query("SELECT * FROM citas")
    fun obtenerCitas() : Flow<List<Cita>>

    @Insert
    suspend fun agregarCita(cita: Cita)

    @Update
    suspend fun actualizarCita(cita: Cita)

    @Delete
    suspend fun eliminarCita(cita: Cita)

}