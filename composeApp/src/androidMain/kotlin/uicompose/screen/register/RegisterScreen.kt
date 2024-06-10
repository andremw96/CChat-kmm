import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uicompose.screen.register.RegisterViewModel
import uicompose.screen.widget.OutlinedTextFieldWithError

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
) {
    val state by registerViewModel.registerFormState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextFieldWithError(
            value = state.name,
            onValueChange = {
                scope.launch {
                    registerViewModel.registerDataChanged(
                        name = it,
                        email = state.email,
                        password = state.password,
                    )
                }
            },
            error = state.nameError ?: "",
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "username")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = true,
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        OutlinedTextFieldWithError(
            value = state.email,
            onValueChange = {
                scope.launch {
                    registerViewModel.registerDataChanged(
                        name = state.name,
                        email = it,
                        password = state.password,
                    )
                }
            },
            label = { Text("Email") },
            error = state.emailError ?: "",
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        OutlinedTextFieldWithError(
            value = state.password,
            onValueChange = {
                scope.launch {
                    registerViewModel.registerDataChanged(
                        name = state.name,
                        email = state.email,
                        password = it,
                    )
                }
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            error = state.passwordError ?: "",
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "password")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // TODO: Handle registration logic here (e.g., call a ViewModel function)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Register")
        }
    }
}