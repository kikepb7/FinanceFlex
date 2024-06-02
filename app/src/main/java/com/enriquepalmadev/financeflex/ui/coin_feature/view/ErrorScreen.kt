package com.enriquepalmadev.financeflex.ui.coin_feature.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.ui.coin_feature.model.ScreenError

@Composable
fun ErrorScreen(
    screenError: ScreenError
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = screenError.image),
                    contentDescription = stringResource(R.string.image_error_content_description)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = screenError.errorMsg),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        screenError = ScreenError(
            image = R.drawable.error_logo,
            errorMsg = R.string.unexpected_error
        )
    )
}