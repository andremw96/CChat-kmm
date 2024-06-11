package usecase.impl

import kotlinx.coroutines.flow.Flow
import model.UserKey
import repository.EncryptRepository
import usecase.GenerateUserKey

class GenerateUserKeyImpl(
    private val encryptRepository: EncryptRepository,
) : GenerateUserKey {
    override suspend fun invoke(email: String): Flow<UserKey> {
        return encryptRepository.generateUserKey(email)
    }
}