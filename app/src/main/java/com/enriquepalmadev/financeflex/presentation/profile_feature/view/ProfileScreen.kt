package com.enriquepalmadev.financeflex.presentation.profile_feature.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.presentation.login_feature.model.UserData
import com.enriquepalmadev.financeflex.presentation.utils.components.AppSignature
import com.enriquepalmadev.financeflex.presentation.utils.components.ProfileRowOption

@Composable
fun ProfileScreen(
    userData: UserData?,
    onCardClick: () -> Unit,
    onSignOut: () -> Unit
) {
    val profilePictureUrl = userData?.profilePictureUrl ?: R.drawable.error_logo

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Profile",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.breeserif))
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        if (userData?.profilePictureUrl != null) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    model = profilePictureUrl,
                    contentDescription = stringResource(R.string.profile_picture_label),
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                if (userData.userName != null) {
                    Column {
                        Text(
                            text = userData.userName,
                            textAlign = TextAlign.Center,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.breeserif))
                        )
                        userData.userEmail?.let {
                            Text(
                                text = it,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.breeserif))
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            ProfileRowOption(
                profileRowOption = ProfileRowOption(
                    icon = Icons.Rounded.AccountCircle,
                    title = stringResource(R.string.my_profile_label)
                ),
                onCardClick = { onCardClick() }
            )
            Spacer(modifier = Modifier.height(32.dp))

            ProfileRowOption(
                profileRowOption = ProfileRowOption(
                    icon = Icons.Rounded.Settings,
                    title = stringResource(R.string.settings_label)
                ),
                onCardClick = { onCardClick() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ProfileRowOption(
                profileRowOption = ProfileRowOption(
                    icon = Icons.Rounded.Notifications,
                    title = stringResource(R.string.notifications_label)
                ),
                onCardClick = { onCardClick() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ProfileRowOption(
                profileRowOption = ProfileRowOption(
                    icon = Icons.Rounded.Info,
                    title = stringResource(R.string.about_us_label)
                ),
                onCardClick = { onCardClick() }
            )

            Spacer(modifier = Modifier.height(128.dp))

            ProfileRowOption(
                profileRowOption = ProfileRowOption(
                    icon = Icons.AutoMirrored.Rounded.Logout,
                    title = stringResource(R.string.logout_label)
                ),
                onCardClick = { onSignOut() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            AppSignature()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        userData = UserData(
            userId = "1",
            userName = "Enrique Palma",
            userEmail = "enrique@example.com",
            profilePictureUrl = R.drawable.error_logo.toString()
        ),
        {},
        {}
    )
}
