package com.example.yaroslavhorach.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yaroslavhorach.common.utill.timeToToHoursMinutes
import com.example.yaroslavhorach.designsystem.theme.PomoTheme
import com.example.yaroslavhorach.designsystem.theme.PomoTypography
import com.example.yaroslavhorach.designsystem.theme.components.CircularProgressBar
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.designsystem.theme.typoPrimary
import com.example.yaroslavhorach.designsystem.theme.typoSecondary
import com.example.yaroslavhorach.domain.task.models.Task
import com.example.yaroslavhorach.home.model.HomeAction
import com.example.yaroslavhorach.home.model.HomeViewState

@Composable
internal fun HomeRoute(
    onTaskClicked: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val homeState by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        screenState = homeState,
        onMessageShown = viewModel::clearMessage,
        actioner = { action ->
            when (action) {
                else -> viewModel.submitAction(action)
            }
        }
    )
}

@Composable
internal fun HomeScreen(
    screenState: HomeViewState,
    onMessageShown: (id: Long) -> Unit,
    actioner: (HomeAction) -> Unit,
) {
    Column {
        UserGreeting(screenState)
        DailyGoalsProgress(screenState)

        if (screenState.tasks.isEmpty() || screenState.areAllTasksCompleted) {
            NoTasks(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 40.dp)
            )
        } else {
            TodayTasksTitle(screenState) {
                //todo
            }
            Spacer(modifier = Modifier.height(16.dp))
            TasksList(screenState)
        }
    }
}

@Composable
private fun TasksList(state: HomeViewState) {
    LazyColumn {
        items(state.tasks) { task ->
            Task(task = task) {
                //todo
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
private fun NoTasks(modifier: Modifier = Modifier) {
    Column(modifier) {
        Image(
            modifier = Modifier.align(CenterHorizontally),
            painter = painterResource(id = PomoIcons.NoTask),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .align(CenterHorizontally),
            text = stringResource(id = R.string.home_no_tasks_title_text),
            color = MaterialTheme.colorScheme.primary,
            style = PomoTypography.h5
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(CenterHorizontally),
            text = stringResource(id = R.string.home_no_tasks_subtitle_text),
            color = MaterialTheme.colorScheme.typoPrimary(),
            style = PomoTypography.body4
        )
    }
}

@Composable
private fun Task(task: Task, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                RoundedCornerShape(14.dp)
            )
            .clickable { onClick() }
    ) {
        Box(
            Modifier
                .padding(start = 14.dp, top = 15.dp, bottom = 15.dp)
                .size(50.dp)
                .background(color = Color(task.color), shape = RoundedCornerShape(14))
        ) {
            Icon(
                modifier = Modifier.align(Center),
                painter = painterResource(id = task.iconId),
                contentDescription = null,
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 14.dp)
                .align(CenterVertically)
        ) {
            Text(
                text = task.name,
                style = PomoTypography.subtitle2,
                color = MaterialTheme.colorScheme.typoPrimary()
            )
            Text(
                text = task.duration.timeToToHoursMinutes(LocalContext.current),
                style = PomoTypography.body4,
                color = MaterialTheme.colorScheme.typoSecondary()
            )
        }

        Image(
            modifier = Modifier
                .align(CenterVertically)
                .padding(end = 20.dp),
            painter = painterResource(id = PomoIcons.PlayTask),
            contentDescription = null,
        )
    }
}

@Composable
private fun TodayTasksTitle(screenState: HomeViewState, onSeeAllClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 22.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(
                id = R.string.home_today_tasks_title_text,
                screenState.tasks.size
            ),
            style = PomoTypography.h5,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        Text(
            modifier = Modifier.clickable { onSeeAllClicked() },
            text = stringResource(id = R.string.home_today_tasks_see_all_text),
            style = PomoTypography.subtitle3,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun DailyGoalsProgress(screenState: HomeViewState) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .background(color = MaterialTheme.colorScheme.surface, RoundedCornerShape(14.dp))
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            CircularProgressBar(
                modifier = Modifier.padding(
                    top = 22.dp,
                    start = 22.dp,
                    bottom = 22.dp
                ),
                percentage = screenState.dailyTasksProgress,
                color = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.onBackground
            )
            Text(
                style = PomoTypography.h5,
                color = MaterialTheme.colorScheme.typoPrimary(),
                modifier = Modifier
                    .align(Center)
                    .padding(start = 22.dp),
                text = (screenState.dailyTasksProgress * 100f).toInt().toString() + "%"
            )
        }

        Column(
            modifier = Modifier
                .align(CenterVertically)
                .padding(horizontal = 22.dp)
        ) {
            // TODO: create different texts for start half and end
            Text(
                text = stringResource(id = screenState.progressTitleRes),
                color = MaterialTheme.colorScheme.typoPrimary(),
                style = PomoTypography.h5
            )
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = stringResource(
                    id = R.string.home_daily_goals_amount_completed_text,
                    screenState.completedAmount,
                    screenState.tasks.size
                ),
                color = MaterialTheme.colorScheme.typoPrimary(),
                style = PomoTypography.body3
            )
        }
    }
}

@Composable
private fun UserGreeting(screenState: HomeViewState) {
    Text(
        modifier = Modifier.padding(top = 20.dp, start = 20.dp),
        text = stringResource(id = R.string.home_user_grating_text, screenState.userName),
        style = PomoTypography.h2,
        color = MaterialTheme.colorScheme.typoPrimary()
    )
}

@Preview
@Composable
private fun BookmarksGridPreview() {
    PomoTheme { HomeScreen(HomeViewState.Preview, {}, {}) }
}

