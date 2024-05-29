package com.enriquepalmadev.financeflex.ui.login_feature.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enriquepalmadev.financeflex.ui.login_feature.model.LoginResult
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class LoginViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()


    // Auth by Google
    fun onSignInResult(result: LoginResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }


    // Auth by Email
    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onClickButton: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onClickButton()
                        } else {
                            _state.value = SignInState(
                                isSignInSuccessful = false,
                                signInError = "There's an error with your email or password"
                            )
                        }
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is CancellationException) throw e
                LoginResult(
                    data = null,
                    errorMessage = e.message
                )
            }
        }
    }
}

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)