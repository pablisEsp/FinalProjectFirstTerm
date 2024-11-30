package com.example.finalprojectfirstterm.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalprojectfirstterm.models.Cita

@Database(
    entities = [Cita::class],
    version = 1,
    exportSchema = false

)

abstract class ADataBase() : RoomDatabase() {
    abstract fun citasDAO() : ADataBaseDAO
}