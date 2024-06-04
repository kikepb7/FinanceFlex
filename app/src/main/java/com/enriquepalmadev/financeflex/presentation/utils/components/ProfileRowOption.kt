package com.enriquepalmadev.financeflex.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enriquepalmadev.financeflex.R

data class ProfileRowOption(
    val icon: ImageVector,
    val title: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileRowOption(
    profileRowOption: ProfileRowOption,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        onClick = { onCardClick() }
    ) {
        Row {
            Image(
                imageVector = profileRowOption.icon,
                contentDescription = "Profile option icon"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = profileRowOption.title,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.breeserif))
            )
        }
    }
}