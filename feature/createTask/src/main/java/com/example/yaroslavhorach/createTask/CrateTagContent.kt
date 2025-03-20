package com.example.yaroslavhorach.createTask

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.yaroslavhorach.CreateTask.R
import com.example.yaroslavhorach.common.utill.TextValidation
import com.example.yaroslavhorach.common.utill.Validation
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState
import com.example.yaroslavhorach.designsystem.theme.PomoTypography.h4
import com.example.yaroslavhorach.designsystem.theme.White
import com.example.yaroslavhorach.designsystem.theme.components.PrimaryButton
import com.example.yaroslavhorach.designsystem.theme.components.PrimaryVariantButton
import com.example.yaroslavhorach.designsystem.theme.components.input_fields.PomoInputField
import com.example.yaroslavhorach.designsystem.theme.components.input_fields.TextFieldState
import com.example.yaroslavhorach.designsystem.theme.divider
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.designsystem.theme.typoPrimary
import com.example.yaroslavhorach.ui.UiText

@Composable
internal fun CreateTagContent(
    state: CreateTaskViewState,
    actioner: (CreateTaskAction) -> Unit
) {
    val tagTitle: TextFieldState = remember {
        TextFieldState(
            validation = mutableStateOf(TextValidation(rules = arrayOf(Validation.Rule.RequiredField to UiText.Empty)))
        )
    }
    val selectedColor = remember { mutableStateOf(state.colorPickerColors.first()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .navigationBarsPadding()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.create_tag_dialog_title_text),
            style = h4.copy(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.divider())
        )
        PomoInputField(
            modifier = Modifier.fillMaxWidth(),
            title = "",
            hint = stringResource(id = R.string.create_tag_text_field_placeholder_text),
            state = tagTitle,
            maxLength = CreateTaskViewState.TAG_MAX_LENGTH
        )
        Spacer(Modifier.padding(16.dp))
        LazyRow {
            itemsIndexed(state.colorPickerColors) { index, color ->
                if (index > 0) {
                    Spacer(Modifier.size(16.dp))
                }
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { selectedColor.value = color }
                        .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
                        .padding(6.dp)
                        .background(color, RoundedCornerShape(6.dp))
                ) {
                    if (selectedColor.value == color) {
                        Icon(
                            modifier = Modifier
                                .align(Center),
                            tint = White,
                            painter = painterResource(id = PomoIcons.Check),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
        Spacer(Modifier.padding(16.dp))
        Row {
            PrimaryVariantButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.create_tag_dialog_negative_btn_text)
            ) { actioner(CreateTaskAction.CreateTagSecondaryBtnClicked) }
            Spacer(Modifier.padding(20.dp))
            PrimaryButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.create_tag_dialog_positive_btn_text)
            ) {
                if (tagTitle.validate()) {
                    actioner(CreateTaskAction.CreatePrimaryBtnClicked(tagTitle.text, selectedColor.value))
                }
            }
        }
        Spacer(Modifier.padding(16.dp))
    }
}
