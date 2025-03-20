package com.example.yaroslavhorach.createTask

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.yaroslavhorach.CreateTask.R
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState
import com.example.yaroslavhorach.designsystem.theme.PomoTypography.body2
import com.example.yaroslavhorach.designsystem.theme.PomoTypography.h4
import com.example.yaroslavhorach.designsystem.theme.components.PrimaryButton
import com.example.yaroslavhorach.designsystem.theme.components.PrimaryVariantButton
import com.example.yaroslavhorach.designsystem.theme.divider
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.designsystem.theme.typoPrimary

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SelectTagsContent(
    state: CreateTaskViewState,
    actioner: (CreateTaskAction) -> Unit
) {
    val showDeletePopUp = remember { mutableStateOf(false) }
    val tagIdToDelete = remember { mutableStateOf<Long?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .navigationBarsPadding()
    ) {
        Row {
            Text(
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(1f)
                    .padding(vertical = 18.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.select_tag_dialog_title_text),
                style = h4.copy(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                color = MaterialTheme.colorScheme.typoPrimary()
            )
            Icon(
                modifier = Modifier
                    .align(CenterVertically)
                    .clickable { actioner(CreateTaskAction.CreateTagClicked) },
                painter = painterResource(PomoIcons.Plus),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.divider())
        )
        LazyColumn {
            items(state.availableTags) { tag ->
                Box {
                    Column {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 18.dp)
                                .combinedClickable(
                                    onClick = { actioner(CreateTaskAction.TagSelected(tag)) },
                                    onLongClick = {
                                        showDeletePopUp.value = true
                                        tagIdToDelete.value = tag.id
                                    }
                                )
                        ) {
                            Icon(
                                modifier = Modifier.align(CenterVertically),
                                painter = painterResource(PomoIcons.Tag),
                                tint = Color(tag.color),
                                contentDescription = null
                            )
                            Spacer(Modifier.size(16.dp))
                            Text(
                                modifier = Modifier
                                    .align(CenterVertically)
                                    .weight(1f),
                                textAlign = TextAlign.Start,
                                text = tag.name,
                                style = body2.copy(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                                color = MaterialTheme.colorScheme.typoPrimary()
                            )
                            if (state.selectedTags.contains(tag)) {
                                Spacer(Modifier.size(16.dp))
                                Icon(
                                    modifier = Modifier.align(CenterVertically),
                                    painter = painterResource(PomoIcons.Check),
                                    tint = MaterialTheme.colorScheme.primary,
                                    contentDescription = null
                                )
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.divider())
                        )
                    }

                    DropdownMenu(
                        expanded = showDeletePopUp.value && tag.id == tagIdToDelete.value,
                        onDismissRequest = {
                            showDeletePopUp.value = false
                            tagIdToDelete.value = null
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    stringResource(
                                        R.string.delete_tag_dialog_pop_up_action_delete_title_text,
                                        tag.name
                                    )
                                )
                            },
                            onClick = {
                                actioner(CreateTaskAction.DeleteTag(tagIdToDelete.value ?: -1))

                                showDeletePopUp.value = false
                                tagIdToDelete.value = null
                            }
                        )
                    }

                }
            }
        }
        Spacer(Modifier.padding(16.dp))
        Row {
            PrimaryVariantButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.select_tag_dialog_negative_btn_text)
            ) { actioner(CreateTaskAction.SelectTagSecondaryBtnClicked) }
            Spacer(Modifier.padding(20.dp))
            PrimaryButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.select_tag_dialog_positive_btn_text)
            ) { actioner(CreateTaskAction.SelectTagPrimaryBtnClicked) }
        }
        Spacer(Modifier.padding(16.dp))
    }
}