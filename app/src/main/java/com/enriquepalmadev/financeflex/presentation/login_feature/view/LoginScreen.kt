package com.enriquepalmadev.financeflex.presentation.login_feature.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.presentation.utils.components.CustomButton
import com.enriquepalmadev.financeflex.presentation.utils.components.CustomOutlinedTextField
import com.enriquepalmadev.financeflex.presentation.login_feature.model.TextFieldData
import com.enriquepalmadev.financeflex.presentation.login_feature.viewmodel.SignInState
import com.enriquepalmadev.financeflex.presentation.utils.navigation.AppScreens

@Composable
fun LoginScreen(
    state: SignInState,
    navController: NavController,
    onEmailSignInClick: (String, String) -> Unit,
    onGoogleSignInClick: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LogoAndTitle(stringResource(id = R.string.app_name))
        WelcomeText(
            title = stringResource(R.string.login_welcome_back),
            text = stringResource(R.string.login_enter_your_details)
        )
        SignInOrSignUp(
            signIn = stringResource(R.string.sign_in),
            signUp = stringResource(R.string.sign_up),
            navController = navController
        )
        CustomOutlinedTextField(
            textFieldData = TextFieldData(
                value = email,
                onValueChange = { email = it },
                label = stringResource(R.string.email_label),
                isEmailValid = true,
                isPasswordField = false
            )
        )
        CustomOutlinedTextField(
            textFieldData = TextFieldData(
                value = password,
                onValueChange = { password = it },
                label = stringResource(R.string.password_label),
                isEmailValid = false,
                isPasswordField = true
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton { onEmailSignInClick(email, password) }
        SocialSignIn(
            text = stringResource(R.string.login_continue_with),
            onSignInClick = { onGoogleSignInClick() }
        )
        Spacer(modifier = Modifier.height(32.dp))
        InformativeText(text = stringResource(R.string.login_informative_text))
    }
}

@Composable
fun LogoAndTitle(
    appName: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = appName,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.merienda)),
            color = Color.Black
        )
    }
}

@Composable
fun WelcomeText(
    title: String,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.breeserif))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = text,
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.breeserif))
        )
    }
}

@Composable
fun SignInOrSignUp(
    signIn: String,
    signUp: String,
    navController: NavController
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(onClick = { }) {
            Text(
                text = signIn,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.breeserif))
            )
        }
        TextButton(
            onClick = { navController.navigate(AppScreens.SignupScreen.route) }
        ) {
            Text(
                text = signUp,
                color = Color.Gray,
                fontFamily = FontFamily(Font(R.font.breeserif))
            )
        }
    }
}

@Composable
fun SocialSignIn(
    text: String,
    onSignInClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Gray,
            fontFamily = FontFamily(Font(R.font.breeserif))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            ) {
                IconButton(onClick = { onSignInClick() }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun InformativeText(
    text: String
) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color.Gray,
        modifier = Modifier.padding(16.dp),
        textAlign = TextAlign.Center,
        fontFamily = FontFamily(Font(R.font.breeserif))
    )
}