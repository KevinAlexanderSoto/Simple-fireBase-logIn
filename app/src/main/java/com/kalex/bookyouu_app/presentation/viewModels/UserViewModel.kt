package com.kalex.bookyouu_app.presentation.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalex.bookyouu_app.common.Resource
import com.kalex.bookyouu_app.domain.use_case.get_users.GetUserUseCase
import com.kalex.bookyouu_app.presentation.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    // Emailvalidation   MutableState<String>
    init {
        getUser()
    }

    fun getUser() {
        getUserUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserState(user = result.data)
                }

                is Resource.Error -> {
                    _state.value = UserState(
                        error = result.message ?: "An unexpected error occured",
                    )
                }

                is Resource.Loading -> {
                    _state.value = UserState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
