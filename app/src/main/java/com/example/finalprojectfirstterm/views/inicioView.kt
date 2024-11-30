package com.example.finalprojectfirstterm.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalprojectfirstterm.R
import com.example.finalprojectfirstterm.viewmodels.AViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioView(navController: NavController, viewModel: AViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Agenda",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.light_blue)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("anyadir") },
                containerColor = colorResource(id = R.color.light_blue),
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
            }
        }

    ) {
        ContentInicioView(it, navController, viewModel)
    }
}

@Composable
fun ContentInicioView(it: PaddingValues, navController: NavController, viewModel: AViewModel){
    val state = viewModel.state

    Column(
        modifier = Modifier
            .padding(it)
            .fillMaxSize()
    ){
        LazyColumn(){
            /* se hace un sort utilizando los métodos parse creados después
               para pasar los valores a números y comparar estos, así saber
               cuál va antes y cuál después y ordenarlos
            */
            items(state.listaCitas.sortedWith(
                compareBy(
                    { parseDay(it.dia) }, // Ordenar por día
                    { parseHour(it.hora) } // Ordenar por hora
                )
            )
            ){ cita ->
                SwipeableActionsBox(
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    startActions = listOf(
                        // Acción para editar (deslizando hacia la derecha)
                        SwipeAction(
                            icon = { Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                modifier = Modifier
                                    .size(40.dp)
                            ) },
                            onSwipe = { navController.navigate(
                                "editar/${cita.idCita}/${cita.nombre}/${cita.telefono}/${cita.asunto}/${cita.dia}/${cita.hora}"
                            )  },
                            background = Color.Yellow
                        )

                    ),
                    endActions = listOf(
                        // Acción para eliminar (deslizando hacia la izquierda)
                        SwipeAction(
                            icon = { Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                modifier = Modifier
                                    .size(40.dp)
                            ) },
                            onSwipe = { viewModel.eliminarCita(cita) },
                            background = Color.Red
                        )
                    )
                ){
                    Card(
                        modifier = Modifier
                            .padding(1.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ){
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxSize()
                        ){
                            Text(
                                text = cita.nombre,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = cita.dia,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = cita.hora,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )



                        }
                    }
                }
            }
        }
    }
}
//función para hacer parse al día y poder compararlo
fun parseDay(dia: String): Int {
    return when (dia) {
        "Lunes" -> 1
        "Martes" -> 2
        "Miércoles" -> 3
        "Jueves" -> 4
        "Viernes" -> 5
        "Sábado" -> 6
        "Domingo" -> 7
        else -> 8 // Por si se agrega "Selecciona un día" u otro texto
    }
}
//función para hacer parse a las horas y poder compararlas
fun parseHour(hora: String): Int {
    return when (hora) {
        "9:00 a 10:00" -> 9
        "10:00 a 11:00" -> 10
        "11:00 a 12:00" -> 11
        "12:00 a 13:00" -> 12
        "13:00 a 14:00" -> 13
        "14:00 a 15:00" -> 14
        "15:00 a 16:00" -> 15
        "16:00 a 17:00" -> 16
        "17:00 a 18:00" -> 17
        "18:00 a 19:00" -> 18
        "19:00 a 20:00" -> 19
        else -> Int.MAX_VALUE // Por si se agrega "Selecciona una hora" u otro texto
    }
}