package com.example.finalprojectfirstterm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citas")


class Cita(
    @PrimaryKey(autoGenerate = false)
    val idCita: String,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "telefono")
    val telefono: String,
    @ColumnInfo(name = "asunto")
    val asunto: String,
    @ColumnInfo(name = "dia")
    val dia: String,
    @ColumnInfo(name = "hora")
    val hora : String
)