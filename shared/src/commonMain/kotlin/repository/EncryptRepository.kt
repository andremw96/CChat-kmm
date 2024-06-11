package repository

import kotlinx.coroutines.flow.Flow
import model.UserKey

interface EncryptRepository {
    suspend fun generateUserKey(email: String): Flow<UserKey>
}