package com.enriquepalmadev.financeflex.presentation.stock_feature.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.domain.stock_feature.model.CompanyListing
import com.enriquepalmadev.financeflex.presentation.stock_feature.model.CompanyListingsState
import com.enriquepalmadev.financeflex.presentation.theme.Blue50
import com.enriquepalmadev.financeflex.presentation.theme.BlueEnd
import com.enriquepalmadev.financeflex.presentation.theme.Green20
import com.enriquepalmadev.financeflex.presentation.theme.TransparentBlueEnd
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CompanyListingsScreen(
    state: CompanyListingsState,
    navController: NavController,
    onSearchQueryChange: (String) -> Unit,
    onRefresh: () -> Unit,
    onCompanyClick: (String) -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isRefreshing
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Blue50)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp),
                text = "Stock Market",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.breeserif))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column {
                TextField(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Blue50, BlueEnd)),
                            shape = CutCornerShape(0.dp, 35.dp, 35.dp, 0.dp)
                        )
                        .align(Alignment.CenterHorizontally),
                    value = state.searchQuery,
                    onValueChange = onSearchQueryChange,
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = 12.sp, fontFamily = FontFamily(
                            Font(R.font.breeserif)
                        )
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            modifier = Modifier.padding(end = 10.dp),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    )
                )
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = onRefresh
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp)
                    ) {
                        items(state.companies.size) { i ->
                            val company = state.companies[i]
                            CompanyItem(
                                company = company,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                onCompanyClick = { onCompanyClick(company.symbol) }
                            )
                            if (i < state.companies.size) {
                                Divider(
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompanyListingScreenPreview() {
    CompanyListingsScreen(
        state = CompanyListingsState(
            companies = listOf(
                CompanyListing("Apple", "APPL", "EJEMPLO", "hola", "prueba", "prueba", "prueba"),
                CompanyListing("Apple", "APPL", "EJEMPLO", "hola", "prueba", "prueba", "prueba"),
                CompanyListing("Apple", "APPL", "EJEMPLO", "hola", "prueba", "prueba", "prueba"),
                CompanyListing("Apple", "APPL", "EJEMPLO", "hola", "prueba", "prueba", "prueba"),
            ),
            isLoading = false,
            isRefreshing = false,
            searchQuery = ""
        ),
        navController = rememberNavController(),
        onSearchQueryChange = {},
        onRefresh = {},
        onCompanyClick = {}
    )
}