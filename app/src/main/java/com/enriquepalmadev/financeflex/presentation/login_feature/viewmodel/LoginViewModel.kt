package com.enriquepalmadev.financeflex.presentation.login_feature.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enriquepalmadev.financeflex.presentation.login_feature.model.LoginResult
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class LoginViewModel : ViewModel() {

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

    fun createUser(
        email: String,
        password: String,
        home: () -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userName = task.result.user?.email?.split("@")?.get(0)
                        createUserDatabase(userName)
                        home()
                    } else {
                        _state.value = SignInState(
                            isSignInSuccessful = false,
                            signInError = "There's an error with your email or password"
                        )
                    }
                    _loading.value = false
                }
        }
    }

    private fun createUserDatabase(userName: String?) {
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()

        user["user_id"] = userId.toString()
        user["name"] = userName.toString()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {

            }
            .addOnFailureListener { }
    }
}

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)