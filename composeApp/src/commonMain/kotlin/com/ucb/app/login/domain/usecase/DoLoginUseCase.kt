package com.ucb.app.login.domain.usecase

import com.ucb.app.login.domain.model.LoginModel
import com.ucb.app.login.domain.model.UserModel
import com.ucb.app.login.domain.repository.AuthenticationRepository

class DoLoginUseCase(
    val repository: AuthenticationRepository
) {
    // Valida antes de llamar al repository
    // Si algo falla lanza una excepción con mensaje claro
    suspend fun invoke(model: LoginModel): UserModel {
        if (model.email.isBlank()) {
            throw IllegalArgumentException("El email no puede estar vacío")
        }
        if (!model.email.contains("@")) {
            throw IllegalArgumentException("El email no tiene un formato válido")
        }
        if (model.password.length < 6) {
            throw IllegalArgumentException("La contraseña debe tener al menos 6 caracteres")
        }
        return repository.login(model)
    }
}