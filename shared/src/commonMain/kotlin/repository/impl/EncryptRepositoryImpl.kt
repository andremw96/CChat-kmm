package repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.UserKey
import repository.EncryptRepository
import util.encryptAES
import util.generateSecureRandomBytes
import util.generateUserPrivateKey
import util.generateUserPublicKey

class EncryptRepositoryImpl : EncryptRepository {
    override suspend fun generateUserKey(email: String): Flow<UserKey> {
        return flow {
            val random = generateSecureRandomBytes(16)
            val userPrivateKey = generateUserPrivateKey(random)
            val userPrivateKeyStr = bytesToHex(userPrivateKey)

            val userPublicKey = generateUserPublicKey(userPrivateKey)
            val userPublicKeyStr = bytesToHex(userPublicKey)

            val encryptedPrivateKey = encryptAES(userPrivateKeyStr, email)

            emit(
                UserKey(
                    privateKey = encryptedPrivateKey,
                    publicKey = userPublicKeyStr,
                )
            )
        }
    }

    private val hexArray = "0123456789ABCDEF".toCharArray()

    private fun bytesToHex(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = bytes[j].toInt() and 0xFF
            hexChars[j * 2] =
                hexArray[v ushr 4]
            hexChars[j * 2 + 1] =
                hexArray[v and 0x0F]
        }
        return hexChars.concatToString()
    }
}