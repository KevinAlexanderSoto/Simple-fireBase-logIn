package com.kalex.bookyouu_app.presentation.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.kalex.bookyouu_app.presentation.composables.Drawer
import com.kalex.bookyouu_app.presentation.validations.validarString
import com.kalex.bookyouu_app.presentation.viewModels.OficesViewModel
import com.kalex.bookyouu_app.presentation.viewModels.PostDocumentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


@ExperimentalPermissionsApi
@Composable
fun AdminCreateSubject(
    navController: NavHostController,
    oficesViewModel: OficesViewModel = hiltViewModel(),
    postDocumentViewModel: PostDocumentViewModel = hiltViewModel()
) {

    val resp = oficesViewModel.state.value
    val correo = oficesViewModel.correo
    //para barra lateral
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()

    //barra de cargando
    if (resp.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize(0.1f)

            )
        }
    }
    //Logica para obtener ciudades de la API
    if (!resp.isLoading) {
        createSubjectToolBar(
            navController,
            scope,
            scaffoldState,
            correo,
            postDocumentViewModel
        )

    }
}

@ExperimentalPermissionsApi
@Composable
fun createSubjectToolBar(
    navController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    correo: String,
    postDocumentViewModel: PostDocumentViewModel
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar Estudiante") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "go back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "menu hamburgesa"
                        )
                    }
                },
            )
        },
        drawerContent = { Drawer(scope, scaffoldState, navController) },
        drawerGesturesEnabled = true

    ) {
        SubjectForm(correo, postDocumentViewModel)
    }
}

@ExperimentalPermissionsApi
@Composable
fun SubjectForm(
    correo: String,
    postDocumentViewModel: PostDocumentViewModel

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),// para hacer scroll
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //------------------------------------Formulario---------------------------------
        val listaDocumento = listOf("CC", "TI", "CE", "PA")
        val listaCarreras = listOf("Ing.Sitemas", "Sicologia", "Salud ocupacional")
        // manejar focus de los texto,
        val localFocusManager = LocalFocusManager.current
        Spacer(Modifier.size(4.dp))

        val menu1 = dropDownMenu(listaDocumento, nombreInput = "Tipo de Documento")
        val text1: String = InputText(label = "Numero de documento", onAction = {
            // bajar al siguiente field
            localFocusManager.moveFocus(FocusDirection.Down)
        })
        val text2 = InputText(label = "Nombre", onAction = {
            // bajar al siguiente field
            localFocusManager.moveFocus(FocusDirection.Down)
        })
        val text3 = InputText(label = "Apellido", onAction = {
            // bajar al siguiente field
            localFocusManager.moveFocus(FocusDirection.Down)
        })
        val text4 = InputText(label = "Correo", correo, onAction = {
            // bajar al siguiente field
            localFocusManager.moveFocus(FocusDirection.Down)
        })
        val menu2 = dropDownMenu(listaCarreras, nombreInput = "Carrera")


//-------------------validaciones para habilitar enviar data---------------------------
        val validacion =
            validarString(text1) && validarString(text2) && validarString(text3) && validarString(
                text4
            ) && validarString(menu1) && validarString(menu2)

//-------------------Armado del body para Post Document---------------------------
        //Crear Body para mandar
        var requestBody: RequestBody? = null

        if (validacion) {
//-------------------Convertir Base 64---------------------------

            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("TipoId", menu1)
            jsonObject.put("Identificacion", text1)
            jsonObject.put("Nombre", text2)
            jsonObject.put("Apellido", text3)
            jsonObject.put("Ciudad", menu2)
            jsonObject.put("Correo", text4)

            var jsonObjectString = jsonObject.toString()

            requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        }

        BtnEnviarImg(validacion, postDocumentViewModel, requestBody)

    }

}

