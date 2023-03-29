package com.example.yaroslavhorach.designsystem.theme.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yaroslavhorach.designsystem.theme.*
import com.example.yaroslavhorach.designsystem.theme.AppTypography.popinsMedium

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primaryInactive()
        ),
        shape = RoundedCornerShape(24)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = popinsMedium,
            color = MaterialTheme.colorScheme.controlPrimaryTypo()
        )
    }
}

@Composable
fun PrimaryVariantButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryVariant(),
            disabledContainerColor = MaterialTheme.colorScheme.primaryVariantInactive()
        ),
        shape = RoundedCornerShape(24)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = popinsMedium,
            color = MaterialTheme.colorScheme.controlSecondaryTypo()
        )
    }
}