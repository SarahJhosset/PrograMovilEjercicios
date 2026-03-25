package com.ucb.app.login.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EmailField(
    value: String,
    error: String?,           // null = sin error, String = muestra el mensaje
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Correo electrónico") },
        placeholder = { Text("ejemplo@ucb.edu") },
        isError = error != null,
        // Solo muestra el texto de error si hay uno
        supportingText = error?.let { { Text(it) } },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}