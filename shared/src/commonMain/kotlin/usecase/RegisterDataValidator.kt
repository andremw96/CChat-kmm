package usecase

sealed class RegisterValidatorResult {
    data object Success : RegisterValidatorResult()
    class Error(val message: String) : RegisterValidatorResult()
}

interface RegisterDataValidator {
    fun checkName(name: String): RegisterValidatorResult
    fun checkEmail(email: String): RegisterValidatorResult
    fun checkPassword(password: String): RegisterValidatorResult
}
