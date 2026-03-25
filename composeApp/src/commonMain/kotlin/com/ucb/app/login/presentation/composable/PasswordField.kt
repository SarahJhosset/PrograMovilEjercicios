package com.ucb.app.login.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordField(
    value: String,
    error: String?,
    visible: Boolean,                    // controla si se ven los caracteres
    onValueChange: (String) -> Unit,
    onVisibilityToggle: () -> Unit       // el ojo que muestra/oculta
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Contraseña") },
        isError = error != null,
        supportingText = error?.let { { Text(it) } },
        // Si visible=true muestra el texto, si no lo oculta con puntos
        visualTransformation = if (visible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onVisibilityToggle) {
                Icon(
                    imageVector = if (visible)
                        Icons.Outlined.Visibility
                    else
                        Icons.Outlined.VisibilityOff,
                    contentDescription = if (visible)
                        "Ocultar contraseña"
                    else
                        "Mostrar contraseña"
                )
            }
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}