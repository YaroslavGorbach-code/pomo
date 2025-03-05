package com.example.yaroslavhorach.createTask

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.unit.dp
import com.example.yaroslavhorach.CreateTask.R
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.designsystem.theme.PomoTypography.h4
import com.example.yaroslavhorach.designsystem.theme.divider

@Composable
internal fun IconPickerBottomShitContent(
    icons: List<Int>, actioner: (CreateTaskAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(vertical = 24.dp),
            text = stringResource(id = R.string.create_task_icon_picker_dialog_title_text),
            style = h4.copy(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 20.dp)
                .background(MaterialTheme.colorScheme.divider())
        )
        LazyVerticalGrid(modifier = Modifier.padding(
            start = 20.dp,
            top = 20.dp,
            bottom = 20.dp,
            end = 6.dp
        ),
            columns = GridCells.Adaptive(67.dp),
            content = {
                items(icons) { iconRes ->
                    Box(modifier = Modifier
                        .size(67.dp)
                        .padding(bottom = 14.dp, end = 14.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary, RoundedCornerShape(18.dp)
                        )
                        .clickable { actioner(CreateTaskAction.ChoseIcon(iconRes)) }) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Center),
                            painter = painterResource(id = iconRes),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            })
    }
}
