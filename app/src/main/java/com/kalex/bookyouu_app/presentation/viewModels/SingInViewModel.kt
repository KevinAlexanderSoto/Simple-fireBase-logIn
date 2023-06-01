package com.kalex.bookyouu_app.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalex.bookyouu_app.common.Resource
import com.kalex.bookyouu_app.domain.use_case.authentication.AuthenticationUseCase
import com.kalex.bookyouu_app.presentation.states.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val authUseCase: AuthenticationUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun logout() {
        viewModelScope.launch {
            authUseCase.logout().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false, isLogin = null,isLogout = result.data ?: false) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true, isLogin = null) }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, isError = result.message ?: "do not get and error" ) }

                    }
                }
            }
        }
    }

    fun login(email: String, password: String) {
        authUseCase.login(email, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, isLogin = result.data) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, isError = result.message ?: "do not get and error" ) }
                }
            }
        }.launchIn(viewModelScope)
    }
}
