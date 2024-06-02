package com.enriquepalmadev.financeflex.ui.stock_feature.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.domain.stock_feature.model.CompanyListing
import com.enriquepalmadev.financeflex.ui.stock_feature.model.CompanyListingsState
import com.enriquepalmadev.financeflex.ui.theme.TransparentGreenEnd
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
            .background(TransparentGreenEnd)
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .padding(top = 64.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = stringResource(R.string.search_label))
            },
            maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = onRefresh
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
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

@Preview(showBackground = true)
@Composable
fun CompanyListingScreenPreview() {
    CompanyListingsScreen(
        state = CompanyListingsState(
            companies = listOf(
                CompanyListing("Apple", "AAPL", "EJEMPLO", "hola", "prueba", "prueba", "prueba")
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