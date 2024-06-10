package uicompose.screen.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import usecase.RegisterDataValidator
import usecase.RegisterUser
import usecase.RegisterValidatorResult

class RegisterViewModel(
    private val registerUser: RegisterUser,
    private val registerDataValidator: RegisterDataValidator,
) : ViewModel() {
    private var _registerForm: MutableStateFlow<RegisterFormState> = MutableStateFlow(
        value = RegisterFormState()
    )
    val registerFormState: StateFlow<RegisterFormState> = _registerForm.asStateFlow()

    suspend fun registerDataChanged(name: String, email: String, password: String) {
        var nameError: String? = null
        val checkName = registerDataValidator.checkName(name)
        if (checkName is RegisterValidatorResult.Error) {
            nameError = checkName.message
        }

        var emailError: String? = null
        val checkEmail = registerDataValidator.checkEmail(email)
        if (checkEmail is RegisterValidatorResult.Error) {
            emailError = checkEmail.message
        }

        var pwdError: String? = null
        val checkPwd = registerDataValidator.checkPassword(password)
        if (checkPwd is RegisterValidatorResult.Error) {
            pwdError = checkPwd.message
        }

        _registerForm.emit(
            RegisterFormState(
                name = name,
                password = password,
                email = email,
                nameError = nameError,
                passwordError = pwdError,
                emailError = emailError,
            )
        )
    }
}