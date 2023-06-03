package com.kalex.bookyouu_app.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kalex.bookyouu_app.R
import com.kalex.bookyouu_app.common.Constants
import com.kalex.bookyouu_app.res.composables.ButtonText
import com.kalex.bookyouu_app.res.composables.Icono
import com.kalex.bookyouu_app.presentation.states.AuthState
import com.kalex.bookyouu_app.res.theme.blanco
import com.kalex.bookyouu_app.res.theme.bookYouuPrimary
import com.kalex.bookyouu_app.presentation.validations.Emailvalidation
import com.kalex.bookyouu_app.presentation.viewModels.SingInViewModel

@Composable
fun SingIn(
    navController: NavController,
    signInViewModel: SingInViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), // para hacer scroll
        verticalArrangement = Arrangement.spacedBy(11.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val resources = LocalContext.current.resources
        Text(
            text = resources.getString(R.string.login_title_text),
            style = MaterialTheme.typography.h6,
            fontSize = 40.sp,
            color = colors.secondary,
            modifier = Modifier.padding(30.dp),
        )

        Textfield(resources.getString(R.string.login_subtitle_text))
        Spacer(
            modifier = Modifier.padding(30.dp),
        )
        // manejar focus de los texto,
        val localFocusManager = LocalFocusManager.current

        // state hoisting Email
        val text = remember { Emailvalidation() }

        EmailField(
            text.correo,
            text.error,
            onAction = {
                // bajar al siguiente field
                localFocusManager.moveFocus(FocusDirection.Down)
            },
        ) {
            text.correo = it
            text.validate()
        }

        // state hoisting Password
        val password = remember { mutableStateOf("") }
        PasswordFiels(
            password.value,
            onAction = { localFocusManager.clearFocus() },
        ) {
            password.value = it
        }
        val singInStateFlag = remember { mutableStateOf(false) }
        val loadingStateFlag = remember { mutableStateOf(false) }
        val successStateFlag = remember { mutableStateOf(false) }
        Buttonin(
            habilitado = text.valid(),
            onButtonClick = {
                signInViewModel.login(text.correo, password.value)
                singInStateFlag.value = true
            },
        )
        if (loadingStateFlag.value) {
            CircularProgressIndicator()
        }

        if (singInStateFlag.value) {
            handleLoginState(
                signInViewModel.state.collectAsState().value,
                onLoading = {
                    loadingStateFlag.value = true
                },
                onSuccess = {
                    loadingStateFlag.value = false
                    navController.navigate(Constants.AdminHomeNavItem)
                },
                onError = {
// todo: add error handle
                },
            )
        }
    }
}

fun handleLoginState(
    state: AuthState,
    onLoading: () -> Unit,
    onSuccess: () -> Unit,
    onError: () -> Unit,
) {
    if (state.isLoading) {
        onLoading()
    }
    if (!state.isLoading && state.isLogin != null) {
        onSuccess()
    }
    if (!state.isLoading && state.isError.isNotEmpty() && state.isLogin == null) {
        onError()
    }
}

@Composable
fun Buttonin(
    habilitado: Boolean,
    onButtonClick: () -> Unit,
) {
    Button(
        onClick = { onButtonClick() },
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxWidth(0.8f),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(23.dp),
        contentPadding = PaddingValues(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = bookYouuPrimary,
            contentColor = blanco,
        ),
        enabled = habilitado,
    ) {
        Icono(R.drawable.outline_login_24, 30)
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        ButtonText("Ingresar", 22)
    }
}

@Composable
fun Textfield(texto: String) {
    Text(
        text = texto,
        style = MaterialTheme.typography.h6,
        color = colors.secondary,
        modifier = Modifier.padding(4.dp),
    )
}

@Composable
fun EmailField(
    text: String,
    error: String?,
    onAction: () -> Unit,
    onEmailChanged: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = text,
            singleLine = true,
            onValueChange = { onEmailChanged(it) },
            label = { Text(text = "Email") },
            placeholder = { Text("example@gmail.com") },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(0.9f),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = colors.secondary,
            ),
            shape = RoundedCornerShape(9.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(onNext = {
                onAction()
            }),
            isError = error != null,
        )
        error?.let { FielError(it) }
    }
}

@Composable
fun FielError(it: String) {
    Text(
        text = it,
        style = TextStyle(color = colors.error),
    )
}

@Composable
fun PasswordFiels(
    pass: String,
    onAction: () -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    TextField(
        value = pass,
        onValueChange = { onPasswordChange(it) },
        label = { Text("Contraseña") },
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(0.9f),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = colors.secondary,
        ),
        shape = RoundedCornerShape(9.dp),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(onDone = {
            onAction()
        }),

    )
}
