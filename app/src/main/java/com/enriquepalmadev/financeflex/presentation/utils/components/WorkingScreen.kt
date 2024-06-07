package com.enriquepalmadev.financeflex.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.presentation.theme.Blue50

@Composable
fun WorkingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue50),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.working_on_it),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.android_working_screen),
            contentDescription = stringResource(R.string.working_screen_description),
            modifier = Modifier
                .size(256.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.sorry_working_screen),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WorkingScreenPreview() {
    WorkingScreen()
}