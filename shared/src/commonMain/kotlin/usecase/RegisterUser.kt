package usecase

import dev.gitlive.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import repository.Result

interface RegisterUser {
    suspend fun invoke(
        name: String,
        email: String,
        password: String,
        publicKey: String,
        encryptedPrivKey: String,
    ): Flow<Result<AuthResult>>
}