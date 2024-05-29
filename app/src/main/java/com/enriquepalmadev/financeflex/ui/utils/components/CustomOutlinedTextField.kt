package com.enriquepalmadev.financeflex.ui.utils.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.ui.login_feature.model.TextFieldData

@Composable
fun CustomOutlinedTextField(
    textFieldData: TextFieldData
) {
    OutlinedTextField(
        value = textFieldData.value,
        onValueChange = textFieldData.onValueChange,
        label = {
            Text(
                text = textFieldData.label,
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.breeserif))
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(60.dp),
        shape = CircleShape,
        keyboardOptions =
        if (textFieldData.isPasswordField) KeyboardOptions(keyboardType = KeyboardType.Password)
        else KeyboardOptions(keyboardType = KeyboardType.Email),
        visualTransformation =
        if (textFieldData.isPasswordField) PasswordVisualTransformation()
        else VisualTransformation.None
    )
}

