package com.ucb.app.login.data.repository

import com.ucb.app.login.domain.model.LoginModel
import com.ucb.app.login.domain.model.UserModel
import com.ucb.app.login.domain.repository.AuthenticationRepository
import kotlinx.coroutines.delay

class LoginRepositoryImpl : AuthenticationRepository {

    override suspend fun login(model: LoginModel): UserModel {
        // Simulamos la demora de una llamada de red real
        delay(1500)

        // Credenciales hardcodeadas para practicar
        // En un proyecto real aquí iría Ktor llamando a tu API
        if (model.email == "test@ucb.edu" && model.password == "123456") {
            return UserModel(
                id    = "usr_001",
                name  = "Estudiante UCB",
                email = model.email,
                token = "token_abc_xyz_123"
            )
        } else {
            // Lanzamos excepción con mensaje que llegará como Effect a la UI
            throw Exception("Credenciales incorrectas. Usa test@ucb.edu / 123456")
        }
    }
}