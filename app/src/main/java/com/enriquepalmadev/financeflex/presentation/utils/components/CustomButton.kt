package com.enriquepalmadev.financeflex.presentation.utils.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.presentation.theme.BlueStart

@Composable
fun CustomButton(
    onSignClick: () -> Unit
) {
    Button(
        onClick = { onSignClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(BlueStart)
    ) {
        Text(
            text = stringResource(id = R.string.sign_in),
            fontFamily = FontFamily(Font(R.font.breeserif))
        )
    }
}