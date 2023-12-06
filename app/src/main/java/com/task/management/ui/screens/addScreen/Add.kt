package com.task.management.ui.screens.addScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.task.management.presentation.TaskFormEvent
import com.task.management.presentation.TaskViewModel
import com.task.management.ui.screens.destinations.HomeDestination
import com.task.management.ui.theme.Black
import com.task.management.ui.theme.White
import com.task.management.ui.theme.priegoFont
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun Add(
    destinationsNavigator: DestinationsNavigator,
    viewModel: TaskViewModel = viewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )

        var isSheetOpen by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = Unit, block = {
            delay(80L)
            isSheetOpen = true
        })

        val scope = rememberCoroutineScope()

        if (isSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    isSheetOpen = false
                    destinationsNavigator.navigate(HomeDestination)
                },
                containerColor = Black,
                modifier = Modifier.padding(top = 7.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 17.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 16.sp,
                            color = Color(0xFF40629B),
                            fontFamily = priegoFont,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable {
                                scope.launch {
                                    sheetState.hide()
                                }
                                viewModel.resetTaskState()
                                destinationsNavigator.navigate(HomeDestination)
                            }
                        )
                        Text(
                            text = "New Task",
                            fontSize = 16.sp,
                            color =  White,
                            fontFamily = priegoFont,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(
                            text = "Done",
                            fontSize = 16.sp,
                            color = Color(0xFF40629B),
                            fontFamily = priegoFont,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(TaskFormEvent.Submit)
                            }
                        )
                    }

                    CreateTaskContent(viewModel)
                }
            }
        }
    }
}