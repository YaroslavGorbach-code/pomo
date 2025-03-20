package com.example.yaroslavhorach.designsystem.theme.components.input_fields

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.yaroslavhorach.common.utill.TextValidation
import com.example.yaroslavhorach.common.utill.Validation
import com.example.yaroslavhorach.common.utill.ValidationState

/**
 * base class for handling input validation and persistence of state, used with [TextFieldState].
 */
open class TextState {

    var text: String by mutableStateOf("")

    open var validation = mutableStateOf(TextValidation())

    fun validate(): Boolean {
        validation.value = validation.value.copy(state = validateAndGetNewState())

        return validation.value.state is ValidationState.Success
    }

    private fun validateAndGetNewState(): ValidationState {
        val (isValid, errorMsg) = Validation.validate(validation.value, text)

        if (!isValid) {
            return ValidationState.Error(errorMsg)
        }
        return ValidationState.Success
    }
}
