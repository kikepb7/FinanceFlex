package com.enriquepalmadev.financeflex.ui.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.enriquepalmadev.financeflex.ui.utils.navigation.AppScreens


data class BottomNavigation(
    val title: String,
    val icon: ImageVector
)

val items = listOf(
    BottomNavigation(
        title = "Home",
        icon = Icons.Rounded.Home
    ),

    BottomNavigation(
        title = "Account",
        icon = Icons.Rounded.AccountCircle
    ),

    BottomNavigation(
        title = "Wallet",
        icon = Icons.Rounded.Wallet
    ),

    BottomNavigation(
        title = "Notifications",
        icon = Icons.Rounded.Notifications
    ),
)

@Composable
fun BottomNavigationBar(
    navigation: NavController
) {
    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == 0,
                    onClick = {
                        when (index) {
                            0 -> navigation.navigate(AppScreens.HomeScreen.route)
                            1 -> navigation.navigate(AppScreens.ProfileScreen.route)
//                            1 -> navigation.navigate(AppScreens.WalletScreen.route)
//                            2 -> navigation.navigate(AppScreens.NotificationScreen.route)
//                            3 -> navigation.navigate(AppScreens.AccountScreen.route)
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }
}