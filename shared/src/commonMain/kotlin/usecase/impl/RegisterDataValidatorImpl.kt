package usecase.impl

import usecase.RegisterDataValidator
import usecase.RegisterValidatorResult

class RegisterDataValidatorImpl : RegisterDataValidator {
    override fun checkName(name: String): RegisterValidatorResult {
        return if (isNameValid(name)) RegisterValidatorResult.Success else RegisterValidatorResult.Error(
            "name is not valid"
        )
    }

    override fun checkEmail(email: String): RegisterValidatorResult {
        return if (isEmailValid(email)) RegisterValidatorResult.Success else RegisterValidatorResult.Error(
            "email is not valid"
        )
    }

    override fun checkPassword(password: String): RegisterValidatorResult {
        return when {
            password.length < 5 -> RegisterValidatorResult.Error("Password must be >5 characters")
            password.lowercase() == "password" -> RegisterValidatorResult.Error("Password shouldn't be \"password\"")
            else -> RegisterValidatorResult.Success
        }
    }

    private fun isNameValid(name: String) = name.isNotBlank() && name.isNotEmpty()

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
        return emailRegex.matches(email)
    }
}
