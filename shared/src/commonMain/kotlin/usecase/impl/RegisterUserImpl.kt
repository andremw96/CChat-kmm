package usecase.impl

import dev.gitlive.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import repository.LoginRepository
import repository.Result
import usecase.RegisterUser

class RegisterUserImpl(
    private val repository: LoginRepository,
) : RegisterUser {
    override suspend fun invoke(
        name: String,
        email: String,
        password: String,
        publicKey: String,
        encryptedPrivKey: String
    ): Flow<Result<AuthResult>> {
        return repository.register(
            name = name,
            email = email,
            password = password,
            publicKey = publicKey,
            encryptedPrivKey = encryptedPrivKey
        )
    }
}