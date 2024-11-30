package com.example.finalprojectfirstterm.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalprojectfirstterm.R
import com.example.finalprojectfirstterm.models.Cita
import com.example.finalprojectfirstterm.viewmodels.AViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarCView(
    navController: NavController,
    viewModel: AViewModel,
    idCita: String,
    nombre: String,
    telefono: String,
    asunto: String,
    dia: String,
    hora: String

){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Editar Cita",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.light_blue)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                }
            )
        }


    ) {
        ContentEditarCView(
            it,
            navController,
            viewModel,
            idCita,
            nombre,
            telefono,
            asunto,
            dia,
            hora
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentEditarCView(
    it: PaddingValues,
    navController: NavController,
    viewModel: AViewModel,
    idCita: String,
    nombre: String,
    telefono: String,
    asunto: String,
    dia: String,
    hora: String,

    ){
    val keyboardController = LocalSoftwareKeyboardController.current

    var nomP by remember { mutableStateOf(nombre) }
    var telP by remember { mutableStateOf(telefono) }
    var asuntoP by remember { mutableStateOf(asunto) }

    var errorMessage by remember { mutableStateOf("") }

    val diasLista = listOf("Selecciona un día", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    var showDias by remember { mutableStateOf(false) }
    var diaSeleccionado by remember { mutableStateOf(dia) }

    val horasLista = listOf(
        "Selecciona una hora",
        "9:00 a 10:00",
        "10:00 a 11:00",
        "11:00 a 12:00",
        "12:00 a 13:00",
        "13:00 a 14:00",
        "14:00 a 15:00",
        "15:00 a 16:00",
        "16:00 a 17:00",
        "17:00 a 18:00",
        "18:00 a 19:00",
        "19:00 a 20:00"
    )
    var showHoras by remember { mutableStateOf(false) }
    var horaSeleccionada by remember { mutableStateOf(hora) }

    val maxTel = 9

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = nomP,
            onValueChange = { nomP = it },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = telP,
            onValueChange = {
                if (it.length <= maxTel) telP = it
            },
            label = { Text("Teléfono") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)

        )

        OutlinedTextField(
            value = asuntoP,
            onValueChange = { asuntoP = it },
            label = { Text("Asunto") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        // Mostrar mensaje de error si existe
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
        }

        ExposedDropdownMenuBox(
            expanded = showDias,
            onExpandedChange = { showDias = !showDias },
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        ) {
            keyboardController?.hide()

            TextField(
                modifier = Modifier.menuAnchor(),
                value = diaSeleccionado,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = showDias)
                }
            )

            ExposedDropdownMenu(
                expanded = showDias,
                onDismissRequest = { showDias = false }

            ) {
                diasLista.forEach {dia ->
                    DropdownMenuItem(
                        text = { Text(text = dia) },
                        onClick = {
                            if (dia != diasLista[0]) { // on this way we are not saving the "Seleccionar dia"
                                diaSeleccionado = dia
                            }

                            showDias = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        ExposedDropdownMenuBox(
            expanded = showHoras,
            onExpandedChange = { showHoras = !showHoras },
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        ) {
            keyboardController?.hide()

            TextField(
                modifier = Modifier.menuAnchor(),
                value = horaSeleccionada,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = showHoras)
                }
            )

            ExposedDropdownMenu(
                expanded = showHoras,
                onDismissRequest = { showHoras = false }

            ) {
                horasLista.forEach { hora ->
                    DropdownMenuItem(
                        text = { Text(text = hora) },
                        onClick = {
                            if (hora != horasLista[0]) { // on this way we are not saving the "Seleccionar hora"
                                horaSeleccionada = hora
                            }

                            showHoras = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        Button(
            onClick = {
                if (nomP.isEmpty() || telP.isEmpty() || asuntoP.isEmpty() || diaSeleccionado == diasLista[0] || horaSeleccionada == horasLista[0]) {
                    errorMessage = "Por favor, rellena todos los campos correctamente."
                } else {
                    val cita = Cita(
                        System.currentTimeMillis().toString(),
                        nomP,
                        telP,
                        asuntoP,
                        diaSeleccionado,
                        horaSeleccionada
                    )

                    viewModel.agregarCita(cita)
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .padding(bottom = 15.dp)
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Editar Cita")
        }
    }
}