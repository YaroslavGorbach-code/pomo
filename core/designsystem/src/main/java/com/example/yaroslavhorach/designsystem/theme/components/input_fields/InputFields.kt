package com.example.yaroslavhorach.designsystem.theme.components.input_fields

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.yaroslavhorach.common.utill.ValidationState
import com.example.yaroslavhorach.designsystem.theme.PomoTypography
import com.example.yaroslavhorach.designsystem.theme.typoPrimary
import com.example.yaroslavhorach.designsystem.theme.typoSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomoInputField(
    modifier: Modifier,
    state: TextFieldState,
    title: String,
    hint: String,
    maxLength: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
) {
    val isError = state.validation.value.state is ValidationState.Error
    Column(modifier) {
        Text(
            text = title,
            style = PomoTypography.subtitle3,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        Spacer(Modifier.size(8.dp))
        TextField(modifier = Modifier
            .height(55.dp)
            .fillMaxWidth(),
            value = state.text,
            onValueChange = { text ->
                if (text.length <= maxLength) {
                    state.text = text
                    state.validation.value = state.validation.value.copy(state = ValidationState.Idle)
                }
            },
            shape = RoundedCornerShape(6.dp),
            isError = isError,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground
            ),
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = hint,
                    style = PomoTypography.body2,
                    color = MaterialTheme.colorScheme.typoSecondary()
                )
            },
            keyboardOptions = keyboardOptions
        )
    }
}

//@Composable
//fun ErrorText(textState: TextState) {
//    if (textState.validation.state is ValidationState.Error) {
//        Text(
//            style = PomoTypography.body5,
//            color = Colo.colors.basicBgAlert,
//            text = (textState.validation.state as ValidationState.Error).uiText.asString()
//        )
//    }
//}

@Composable
fun PomoPicker(
    modifier: Modifier,
    title: String,
    hint: String,
    value: String? = null,
    iconRes: Int,
    iconTint: Color,
    onClick: () -> Unit
) {
    Column(modifier.clickable { onClick() }) {
        Text(
            text = title,
            style = PomoTypography.body2,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        Row(
            modifier = Modifier
                .height(55.dp)
                .padding(top = 4.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
        )
        {
            if (value.isNullOrEmpty()) {
                Text(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(start = 14.dp)
                        .weight(1f),
                    text = hint,
                    style = PomoTypography.body2,
                    color = MaterialTheme.colorScheme.typoSecondary()
                )
            } else {
                Text(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(start = 8.dp)
                        .weight(1f),
                    text = value,
                    style = PomoTypography.body2,
                    color = MaterialTheme.colorScheme.typoPrimary()
                )
            }
            Icon(
                modifier = Modifier
                    .padding(end = 14.dp)
                    .size(12.dp)
                    .align(CenterVertically),
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = iconTint
            )
        }
    }
}