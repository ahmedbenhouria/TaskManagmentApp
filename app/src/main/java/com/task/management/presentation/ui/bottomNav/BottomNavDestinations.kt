package com.task.management.presentation.ui.bottomNav

import androidx.annotation.StringRes
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.task.management.R
import com.task.management.presentation.ui.screens.destinations.AddScreenDestination
import com.task.management.presentation.ui.screens.destinations.HomeScreenDestination
import com.task.management.presentation.ui.screens.destinations.MessageScreenDestination
import com.task.management.presentation.ui.screens.destinations.ProfileScreenDestination
import com.task.management.presentation.ui.screens.destinations.TasksScreenDestination

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val unselectedIcon: Int,
    val selectedIcon: Int,
    @StringRes val label: Int
) {
    Home(HomeScreenDestination, R.drawable.house_regular, R.drawable.house_solid, R.string.home),
    Tasks(TasksScreenDestination, R.drawable.folder_regular, R.drawable.folder_solid, R.string.tasks),
    Add(AddScreenDestination, R.drawable.plus_icon, R.drawable.plus_icon, R.string.add),
    Message(MessageScreenDestination, R.drawable.comment_regular, R.drawable.comment_solid, R.string.message),
    Profile(ProfileScreenDestination, R.drawable.user_regular, R.drawable.user_solid, R.string.profile)
}