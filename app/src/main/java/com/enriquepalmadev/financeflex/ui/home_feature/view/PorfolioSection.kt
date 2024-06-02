package com.enriquepalmadev.financeflex.ui.home_feature.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.data.portfolio_feature.database.TodoEntity
import com.enriquepalmadev.financeflex.data.portfolio_feature.database.addDate
import com.enriquepalmadev.financeflex.ui.home_feature.viemodel.TodoViewModel

@Composable
fun PortfolioSection(
    viewModel: TodoViewModel = hiltViewModel()
) {
    val todos by viewModel.todos.collectAsState()
    val (dialogOpen, setDialogOpen) = remember { mutableStateOf(false) }

    if (dialogOpen) {
        val (invest, setInvest) = remember { mutableStateOf("") }
        val (amount, setAmount) = remember { mutableStateOf("") }
        val (avgPrice, setAveragePrice) = remember { mutableStateOf("") }

        Dialog(onDismissRequest = { setDialogOpen(false) }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = invest,
                    onValueChange = { setInvest(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(R.string.invest_portfolio))
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        focusedLabelColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = avgPrice,
                    onValueChange = { setAveragePrice(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(R.string.average_amount_portfolio))
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        focusedLabelColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = amount,
                    onValueChange = { setAmount(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(R.string.amount_portfolio))
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        focusedLabelColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(18.dp))
                Button(
                    onClick = {
                        if (amount.isNotEmpty() && avgPrice.isNotEmpty() && invest.isNotEmpty()) {
                            viewModel.addTodo(
                                TodoEntity(
                                    avgPrice = amount,
                                    amount = avgPrice,
                                    invest = invest
                                )
                            )
                            setDialogOpen(false)
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = stringResource(R.string.add_invest),
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.breeserif))
                    )
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.secondary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    setDialogOpen(true)
                },
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add todo icon"
                )
            }
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = todos.isEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut(),
            ) {
                Text(
                    text = stringResource(R.string.not_operation_yet),
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.breeserif)),
                    fontSize = 22.sp
                )
            }
            AnimatedVisibility(
                visible = todos.isNotEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = paddings.calculateBottomPadding() + 8.dp,
                            top = 8.dp,
                            end = 8.dp,
                            start = 8.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(todos.sortedBy { it.done }, key = {
                        it.id
                    }) { todo ->
                        TodoItem(
                            todo = todo,
                            onClick = { viewModel.updateTodo(todo.copy(done = !todo.done)) },
                            onDelete = {
                                viewModel.deleteTodo(todo)
                            })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TodoItem(
    todo: TodoEntity,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (todo.done) Color(0xff24d65f) else Color(
            0xffff6363
        ), animationSpec = tween(500)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(color = color)
                .clickable { onClick() }
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        AnimatedVisibility(
                            visible = todo.done,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "Check icon",
                                tint = color
                            )
                        }
                    }
                    Row {
                        AnimatedVisibility(
                            visible = todo.done,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Close icon",
                                tint = color
                            )
                        }
                    }
                }
                Column {
                    Text(
                        text = todo.invest,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.breeserif))
                    )
                    Text(
                        text = todo.amount,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color(0xffebebeb),
                        fontFamily = FontFamily(Font(R.font.breeserif))
                    )
                    Text(
                        text = todo.avgPrice,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color(0xffebebeb),
                        fontFamily = FontFamily(Font(R.font.breeserif))
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = "Delete icon",
                    modifier = Modifier.clickable { onDelete() })
            }
        }

        Text(
            text = todo.addDate,
            modifier = Modifier.padding(4.dp),
            fontFamily = FontFamily(Font(R.font.breeserif)),
            color = Color(0xffebebeb),
            fontSize = 10.sp
        )
    }
}


/*
private fun <T> Iterable<T>.sumOf(selector: (T) -> Float): Float {
    var sum = 0f
    for (element in this) {
        sum += selector(element)
    }
    return sum
}*/
