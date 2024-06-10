package uicompose.screen.register

/**
 * Data validation state of the login form.
 */
data class RegisterFormState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val nameError: String? = null,
    val passwordError: String? = null,
    val emailError: String? = null,
    val isLoading: Boolean = false,
    val accessToken: String? = null,
    val loginError: String? = null,
    val isLoginSuccess: Boolean = false,
) {
    val isDataValid: Boolean
        get() = nameError == null && passwordError == null && emailError == null && name.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()
}
