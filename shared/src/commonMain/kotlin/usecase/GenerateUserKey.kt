package usecase

import kotlinx.coroutines.flow.Flow
import model.UserKey

interface GenerateUserKey {
    suspend fun invoke(email: String): Flow<UserKey>
}