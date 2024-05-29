package com.enriquepalmadev.financeflex.ui.login_feature.model

data class LoginResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val userEmail: String?,
    val profilePictureUrl: String?
)

data class TextFieldData(
    val value: String,
    val onValueChange: (String) -> Unit,
    val label: String,
    val isEmailValid: Boolean,
    val isPasswordField: Boolean = false
)