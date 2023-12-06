package com.task.management.ui.screens.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.task.management.R
import com.task.management.ui.theme.Black
import com.task.management.ui.theme.Blue
import com.task.management.ui.theme.Grey
import com.task.management.ui.theme.GreyLight
import com.task.management.ui.theme.White
import com.task.management.ui.theme.priegoFont
import compose.icons.FeatherIcons
import compose.icons.feathericons.Calendar
import compose.icons.feathericons.Menu
import compose.icons.feathericons.MessageCircle
import compose.icons.feathericons.Paperclip
import compose.icons.feathericons.Share2
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TopBarComponent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 27.dp)
            .fillMaxWidth()
            .height(80.dp)

    ) {
        Icon(
            imageVector = FeatherIcons.Menu,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .alpha(0.8F),
            tint = White
        )
        Image(
            painter = painterResource(id = R.drawable.profile_photo),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(41.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun InlineTitleIconComponent() {
    val myId = "inlineContent"
    val text = buildAnnotatedString {
        append("Manage\nyour tasks")
        // Append a placeholder string "[icon]" and attach an annotation "inlineContent" on it.
        appendInlineContent(myId, "[icon]")
    }

    val inlineContent = mapOf(
        Pair(
            myId,
            InlineTextContent(
                Placeholder(
                    width = 60.sp,
                    height = 60.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {
                Image(painter = painterResource(
                    id = R.drawable.pencil),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 7.dp)
                        .size(50.dp)
                )
            }
        )
    )

    Text(
        text = text,
        color = White,
        fontFamily = priegoFont,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp,
        lineHeight = 48.sp,
        modifier = Modifier.padding(start = 27.dp, end = 5.dp),
        inlineContent = inlineContent
    )
}

@Composable
fun WeekCalenderSection() {
    val currentDate = remember { LocalDate.now() }
    val startDate = remember { currentDate.minusDays(20) }
    val endDate = remember { currentDate.plusDays(20) }
    var selection by remember { mutableStateOf(currentDate) }

    val state = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstVisibleWeekDate = currentDate,
    )

    val tasksList = listOf(
        Task(name = "March Dribble Shots Design.\nPlan for the month", priority = "High", date = currentDate),
        Task(name = "Create the 'Blog' and 'Product'\npages for the FortRoom website", priority = "Medium", date = currentDate),
        Task(name = "Create the 'Blog' and 'Product'\npages for the FortRoom website", priority = "Medium", date = currentDate.plusDays(1))
    )

    Column {
        WeekCalendar(
            modifier = Modifier
                .background(color = Black)
                .padding(horizontal = 20.dp, vertical = 5.dp),
            state = state,
            dayContent = { day ->
                Day(date = day.date, isSelected = selection == day.date) { clicked ->
                    if (selection != clicked) {
                        selection = clicked
                    }
                }
            },
        )

        TasksListSection(tasksList, selection)
    }

}

private val dateFormatter = DateTimeFormatter.ofPattern("dd")

@Composable
fun Day(
    date: LocalDate,
    isSelected: Boolean,
    onClick: (LocalDate) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick(date) }
        ,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .width(45.dp)
                    .height(73.dp)
                    .background(
                        color = if (isSelected) White else Black,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = date.dayOfWeek.toString().substring(0, 3).lowercase()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                        fontSize = 14.sp,
                        color =  if (isSelected) Black else Color.Gray,
                        fontFamily = priegoFont,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = dateFormatter.format(date),
                        fontSize = 14.sp,
                        fontFamily = priegoFont,
                        color = if (isSelected) Black else White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun TasksListSection(
    tasksList: List<Task>,
    selection: LocalDate
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(tasksList.filter { it.priority == "High" }) {task ->
            if (selection == task.date) {
                CardTask(task = task)
            }
        }
        items(tasksList.filter { it.priority == "Medium" }) {task ->
            if (selection == task.date) {
                CardTask(
                    task = task,
                    containerColor = Grey,
                    primaryColor = White,
                    secondaryColor = White,
                    backgroundColor = GreyLight
                )
            }
        }
    }
}

data class Task(val name: String, val priority: String, val date: LocalDate)

@Composable
fun CardTask(
    task: Task,
    containerColor: Color = Blue,
    primaryColor: Color = Black,
    secondaryColor: Color = GreyLight,
    backgroundColor: Color = White
) {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .height(210.dp)
            .padding(horizontal = 25.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(33.dp)
                        .background(backgroundColor, shape = RoundedCornerShape(18.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = task.priority,
                        fontSize = 12.sp,
                        color =  primaryColor,
                        fontFamily = priegoFont,
                        fontWeight = FontWeight.Normal
                    )
                }

                Box(
                    modifier = Modifier
                        .size(33.dp)
                        .background(backgroundColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = FeatherIcons.Share2,
                        contentDescription = null,
                        tint = primaryColor,
                        modifier = Modifier
                            .size(17.dp)
                            .clip(CircleShape)
                    )
                }
            }

            Text(
                text = task.name,
                fontSize = 19.sp,
                color =  primaryColor,
                fontFamily = priegoFont,
                fontWeight = FontWeight.Medium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = FeatherIcons.Calendar,
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = Modifier.size(15.dp)
                )

                Text(
                    text = DateTimeFormatter.ofPattern("dd MMM").format(task.date),
                    fontSize = 13.sp,
                    color =  primaryColor,
                    fontFamily = priegoFont,
                    fontWeight = FontWeight.Normal
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_photo),
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 11.dp,
                        alignment = Alignment.End
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = FeatherIcons.MessageCircle,
                            contentDescription = null,
                            tint = secondaryColor,
                            modifier = Modifier.size(17.dp)
                        )
                        Text(
                            text = "4",
                            fontSize = 12.sp,
                            color = secondaryColor,
                            fontFamily = priegoFont,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = FeatherIcons.Paperclip,
                            contentDescription = null,
                            tint = secondaryColor,
                            modifier = Modifier.size(17.dp)
                        )
                        Text(
                            text = "16",
                            fontSize = 12.sp,
                            color = secondaryColor,
                            fontFamily = priegoFont,
                            fontWeight = FontWeight.Medium
                        )
                    }

                }

            }
        }

    }
}