/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.yaroslavhorach.designsystem.theme.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.designsystem.theme.secondaryIcon

@Composable
fun RowScope.PomoNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = PomoNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = PomoNavigationDefaults.navigationContentColor(),
            selectedTextColor = PomoNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = PomoNavigationDefaults.navigationContentColor(),
            indicatorColor = PomoNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun RowScope.AddTaskPomoNavigationBarItem(onClick: () -> Unit) {
    NavigationBarItem(
        selected = false,
        onClick = onClick,
        icon = {
            Icon(
                modifier = Modifier.size(45.dp),
                painter = painterResource(id = PomoIcons.AddTask),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        alwaysShowLabel = false,
    )
}

@Composable
fun PomoNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = PomoNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}

object PomoNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.secondaryIcon()

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}
