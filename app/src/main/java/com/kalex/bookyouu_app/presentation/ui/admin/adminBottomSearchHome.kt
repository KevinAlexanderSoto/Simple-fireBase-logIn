package com.kalex.bookyouu_app.presentation.ui


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kalex.bookyouu_app.R
import com.kalex.bookyouu_app.common.Constants
import com.kalex.bookyouu_app.presentation.composables.Drawer
import com.kalex.bookyouu_app.presentation.composables.Imagen
import com.kalex.bookyouu_app.presentation.theme.color1
import com.kalex.bookyouu_app.presentation.theme.color2
import com.kalex.bookyouu_app.presentation.viewModels.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun adminBottomSearchHome(
    navController: NavController,
    usermodelData : UserViewModel = hiltViewModel()
){
    val state = usermodelData.state.value

    if (state.isLoading){
        Log.d("KEVIN","CARGANDO")
    }
    val context = LocalContext.current
    if (!state.isLoading){
        val nombre = state.user?.usuario
        Log.d("KEVIN","nombre = $nombre")
        Toast.makeText(context,"$nombre", Toast.LENGTH_LONG).show()
    }
    if(state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)

        )
    }
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Consulta") },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu,
                            contentDescription = "menu hamburgesa")
                    }
                },
            )
        }, drawerContent = { Drawer(scope, scaffoldState, navController,) },
        drawerGesturesEnabled = true,
        bottomBar = {}
    ) {
            padding->
        searchContent(navController)
    }


}

@Composable
fun searchContent(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),// para hacer scroll
        verticalArrangement = Arrangement.spacedBy(19.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        val resources = LocalContext.current.resources


        Imagen(url = R.drawable.searchimg, modifier = Modifier
            .wrapContentSize(Alignment.BottomCenter)
            .height(250.dp)
            .width(440.dp)
            )
        Card(
            encabezado = "Consultar Estudiantes",
            R.drawable.ic_student,
            navController,
            Constants.AdminSendStudentNavItem,
            color1

        )
        Card(
            encabezado = "Consultar Docentes",
            R.drawable.plagiarism_24,
            navController,
            Constants.AdminSendProfesorNavItem,
            color2
        )
        Card(
            encabezado = "Consultar Materias",
            R.drawable.plagiarism_24,
            navController,
            Constants.AdminSendProfesorNavItem,
            color2
        )

        Spacer(modifier = Modifier.padding(15.dp))
    }
}
