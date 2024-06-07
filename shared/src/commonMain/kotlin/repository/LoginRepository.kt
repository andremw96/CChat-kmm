package repository

import dev.gitlive.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun register(
        name: String,
        email: String,
        pwd: String,
        publicKey: String,
        encryptedPrivKey: String,
    ): Flow<Result<AuthResult>>
}