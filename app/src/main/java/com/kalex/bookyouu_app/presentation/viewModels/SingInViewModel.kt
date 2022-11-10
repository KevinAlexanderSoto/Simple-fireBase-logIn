package com.kalex.bookyouu_app.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.kalex.bookyouu_app.domain.use_case.authentication.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val authUseCase: AuthenticationUseCase,
) : ViewModel() {

}