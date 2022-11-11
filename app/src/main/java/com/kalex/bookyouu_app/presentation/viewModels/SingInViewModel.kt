package com.kalex.bookyouu_app.presentation.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalex.bookyouu_app.common.Resource
import com.kalex.bookyouu_app.domain.use_case.authentication.AuthenticationUseCase
import com.kalex.bookyouu_app.presentation.states.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val authUseCase: AuthenticationUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    fun logout(){
        viewModelScope.launch {
            authUseCase.logout().onEach { result ->
                when (result){
                    is Resource.Success -> {

                        _state.value = AuthState(islogout = result.data ?: false)
                        _state.value = AuthState(islogout = false, isLogin = null)
                    }
                    is Resource.Loading -> {

                        _state.value = AuthState(isLoading = true)
                    }
                    is Resource.Error -> {

                        _state.value = AuthState(isError = result.message ?:"do not get and error")
                    }
                }
            }
        }
    }

    fun login(email: String, password: String){
        authUseCase.login(email,password).onEach { result ->
            when (result){
                is Resource.Success -> {
                    _state.value = AuthState(isLogin = result.data )
                }
                is Resource.Loading -> {

                    _state.value = AuthState(isLoading = true)
                }
                is Resource.Error -> {

                    _state.value = AuthState(isError = result.message ?:"do not get and error")
                }
            }
        }.launchIn(viewModelScope)
    }



}
