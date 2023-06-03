package com.kalex.bookyouu_app.presentation.ui

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kalex.bookyouu_app.R
import com.kalex.bookyouu_app.common.Constants
import com.kalex.bookyouu_app.res.composables.Drawer
import com.kalex.bookyouu_app.res.composables.Imagen
import com.kalex.bookyouu_app.res.theme.color1
import com.kalex.bookyouu_app.res.theme.color2
import com.kalex.bookyouu_app.res.theme.color3
import kotlinx.coroutines.launch

@Composable
fun adminBottomSubjectHome(
    navController: NavController
){
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Materias") },
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
        subjectContent(navController)
    }


}

@Composable
fun subjectContent(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),// para hacer scroll
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        val resources = LocalContext.current.resources
        Imagen(url = R.drawable.materiaimg, modifier = Modifier
            .wrapContentSize(Alignment.BottomCenter)
            .height(250.dp)
            .width(450.dp)
             )
        Spacer(modifier = Modifier.padding(3.dp))
        Card(
            encabezado = "Agregar Materia",
            R.drawable.ic_student,
            navController,
            Constants.AdminSendSubjectNavItem,
            color1

        )
        Spacer(modifier = Modifier.padding(10.dp))
        Card(
            encabezado = "Eliminar Materia",
            R.drawable.plagiarism_24,
            navController,
            Constants.AdminSendProfesorNavItem,
            color2
        )

        Spacer(modifier = Modifier.padding(15.dp))
    }
}
