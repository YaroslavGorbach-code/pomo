package com.example.yaroslavhorach.designsystem.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.yaroslavhorach.designsystem.theme.PomoTypography
import com.example.yaroslavhorach.designsystem.theme.typoPrimary
import com.example.yaroslavhorach.designsystem.theme.typoSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomoInputField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    title: String,
    hint: String
) {
    Column(modifier) {
        Text(
            text = title,
            style = PomoTypography.body2,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        TextField(modifier = Modifier
            .height(55.dp)
            .padding(top = 4.dp)
            .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(6.dp),
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
            }
        )
    }
}

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