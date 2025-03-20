package com.example.yaroslavhorach.designsystem.theme.components.input_fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.yaroslavhorach.common.utill.TextValidation
import com.example.yaroslavhorach.common.utill.Validation
import com.example.yaroslavhorach.ui.UiText

/**
 * wrapper class for working with text field UI component.
 * @param value - an optional text of the text field.
 * @param validation - the [TextValidation] class which defines validation state and rules for the text field.
 */
data class TextFieldState(
    val value: String? = null,
    override var validation: MutableState<TextValidation> = mutableStateOf(TextValidation())
) : TextState() {

    init {
        value?.let { text = it }
        super.validation = this.validation
    }

    fun updateValidation(vararg textValidation: Pair<Validation.Rule, UiText>) {
        validation.value = TextValidation(rules = arrayOf(*textValidation))
    }
}